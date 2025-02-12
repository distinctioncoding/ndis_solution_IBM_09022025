package com.larry.controller;
import com.larry.pojo.dto.ServiceTypeResponseDto;
import com.larry.pojo.http.ResponseMessage;
import com.larry.service.ServiceTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-types")
@CrossOrigin(origins = "*")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    public ServiceTypeController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    @GetMapping
    public ResponseEntity<ResponseMessage<List<ServiceTypeResponseDto>>> getServiceTypeList() {
        ResponseMessage<List<ServiceTypeResponseDto>> response = ResponseMessage.success(serviceTypeService.getAllServiceTypes());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
