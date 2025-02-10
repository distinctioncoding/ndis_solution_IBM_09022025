package com.larry.repository;

import com.larry.pojo.NDISService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NDISServiceRepository extends JpaRepository<NDISService, Long> {
}
