package vdb.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.stereotype.Repository;

import vdb.app.entity.Vector19;

@Repository
public interface Vector19Repository extends JpaRepository<Vector19, String>, JpaSpecificationExecutor<Vector19> {

	
}
