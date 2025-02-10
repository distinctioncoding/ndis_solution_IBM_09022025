package com.larry.repository;

import com.larry.pojo.NDISService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<NDISService,Long> {
        boolean existsByServiceName(String serviceName);
}
