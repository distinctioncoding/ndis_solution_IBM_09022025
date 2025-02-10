package com.larry.controller;

import com.larry.pojo.ServiceType;
import com.larry.service.ServiceTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-types")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping
    public List<ServiceType> getServiceTypeList() {
        return serviceTypeService.getAllServiceTypes();
    }
}
