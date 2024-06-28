package vdb.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.criteria.Predicate;
import vdb.app.entity.Vector19;
import vdb.app.service.ExclusionService;
import vdb.app.service.Vector19Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.0.5:4200"})
public class Vector19Controller {

	 private static final Logger logger = LoggerFactory.getLogger(Vector19Service.class);

	 @Autowired
	    private Vector19Service vector19Service;

	    @Autowired
	    private ExclusionService exclusionService;

	    @GetMapping("/search")
	    public ResponseEntity<Map<String, Object>> search(
	            @RequestParam Map<String, String> allParams,
	            @RequestParam(required = false) String tableName,
	            @RequestParam(defaultValue = "1") int page,
	            @RequestParam(defaultValue = "50") int size
	    ) {
	        logger.info("Received search request with params: {}, page: {}, size: {}", allParams, page, size);

	        // Ensure zero-based index for pagination
//	        int zeroBasedPage = page > 0 ? page - 1 : 0;

	        // Build specification and handle query based on valid parameters
	        Map<String, String> validParams = filterValidParams(allParams);
	        Specification<Vector19> spec = buildSpecification(validParams, tableName);
	        Pageable pageable = PageRequest.of(page, size);

	        logger.info("Pageable details - page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());

	        // Perform search with repository method
	        Page<Vector19> resultPage = vector19Service.search(spec, pageable);

	        logger.info("Returning paginated results for page: {}, size: {}", resultPage.getNumber(), resultPage.getSize());

	        // Prepare response with paginated data
	        Map<String, Object> response = new HashMap<>();
	        response.put("net_new_data", resultPage.getContent());
	        response.put("pagination_total", generatePaginationInfo(resultPage));
	        response.put("net_new_count", resultPage.getTotalElements());

	        return ResponseEntity.ok(response);
	    }


	    private Map<String, String> filterValidParams(Map<String, String> allParams) {
	        Set<String> entityFields = Arrays.stream(Vector19.class.getDeclaredFields())
	                                         .map(Field::getName)
	                                         .collect(Collectors.toSet());

	        Map<String, String> validParams = new HashMap<>();
	        allParams.forEach((key, value) -> {
	            if ((key.startsWith("include_") || key.startsWith("exclude_")) && !value.isEmpty() &&
	                entityFields.contains(key.replace("include_", "").replace("exclude_", ""))) {
	                validParams.put(key, value);
	            }
	        });
	        return validParams;
	    }

	    private Specification<Vector19> buildSpecification(Map<String, String> validParams, String tableName) {
	        return (root, query, criteriaBuilder) -> {
	            List<Predicate> predicates = new ArrayList<>();
	            Set<String> entityFields = Arrays.stream(Vector19.class.getDeclaredFields())
	                                             .map(Field::getName)
	                                             .collect(Collectors.toSet());

	            validParams.forEach((key, value) -> {
	                if (value != null && !value.trim().isEmpty()) {
	                    if (key.startsWith("include_")) {
	                        String actualKey = key.substring(8);
	                        if ("employee_size".equals(actualKey)) {
	                            String[] sizeValues = value.split(",");
	                            List<Predicate> sizePredicates = new ArrayList<>();
	                            for (String size : sizeValues) {
	                                sizePredicates.add(criteriaBuilder.like(root.get(actualKey), "%" + size.trim() + "%"));
	                            }
	                            predicates.add(criteriaBuilder.or(sizePredicates.toArray(new Predicate[0])));
	                        } else {
	                            String[] values = value.split(",");
	                            List<Predicate> valuePredicates = new ArrayList<>();
	                            for (String v : values) {
	                                valuePredicates.add(criteriaBuilder.equal(root.get(actualKey), v.trim()));
	                            }
	                            predicates.add(criteriaBuilder.or(valuePredicates.toArray(new Predicate[0])));
	                        }
	                    } else if (key.startsWith("exclude_")) {
	                        String actualKey = key.substring(8);
	                        String[] values = value.split(",");
	                        List<Predicate> valuePredicates = new ArrayList<>();
	                        for (String v : values) {
	                            valuePredicates.add(criteriaBuilder.notEqual(root.get(actualKey), v.trim()));
	                        }
	                        predicates.add(criteriaBuilder.and(valuePredicates.toArray(new Predicate[0])));
	                    } else {
	                        predicates.add(criteriaBuilder.equal(root.get(key), value.trim()));
	                    }
	                }
	            });

	            if (tableName != null && !tableName.isEmpty()) {
	                List<String> excludePids = exclusionService.getPidsFromTable(tableName);
	                if (!excludePids.isEmpty()) {
	                    predicates.add(criteriaBuilder.not(root.get("pid").in(excludePids)));
	                }
	            }

	            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	        };
	    }

	    private Map<String, String> generatePaginationInfo(Page<Vector19> resultPage) {
	        Map<String, String> paginationInfo = new HashMap<>();
	        paginationInfo.put("current_page_total", String.valueOf(resultPage.getNumber() + 1));
	        paginationInfo.put("pagination_total", String.valueOf(resultPage.getTotalPages()));
	        paginationInfo.put("records_per_page_total", String.valueOf(resultPage.getSize()));
	        return paginationInfo;
	    }
	}