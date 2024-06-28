package vdb.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vdb.app.entity.Vector19;
import vdb.app.repo.Vector19Repository;

import java.util.List;

@Service
public class Vector19Service {

    @Autowired
    private Vector19Repository vector19Repository;

    @Autowired
    private ExclusionService exclusionService;

    // @Cacheable(value = "vector19", key = "{#spec.hashCode(), #pageable.pageNumber, #pageable.pageSize}")
    public Page<Vector19> search(Specification<Vector19> spec, Pageable pageable) {
        return vector19Repository.findAll(spec, pageable);
    }

    // @Cacheable(value = "vector19Count", key = "#tableName")
    public long countWithExclusions(String tableName) {
        if (tableName != null && !tableName.isEmpty()) {
            List<String> excludePids = exclusionService.getPidsFromTable(tableName);
            if (!excludePids.isEmpty()) {
                return vector19Repository.count((root, query, criteriaBuilder) ->
                        criteriaBuilder.not(root.get("pid").in(excludePids))
                );
            }
        }
        return vector19Repository.count();
    }
}
