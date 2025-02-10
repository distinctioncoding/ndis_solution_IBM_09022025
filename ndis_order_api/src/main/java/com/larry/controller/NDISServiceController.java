package com.larry.controller;

import com.larry.CustomLogger;
import com.larry.pojo.dto.CreateServiceRequestDto;
import com.larry.pojo.dto.ServiceResponseDto;
import com.larry.pojo.http.ResponseMessage;
import com.larry.service.NDISServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ndis-service")
public class NDISServiceController {
    private final NDISServiceService ndisServiceService;

    @Autowired
    public NDISServiceController(NDISServiceService ndisServiceService) {
        this.ndisServiceService = ndisServiceService;
    }

    @PostMapping
    public ResponseEntity<ResponseMessage<ServiceResponseDto>> createNDISService(@RequestBody @Valid CreateServiceRequestDto createServiceRequestDto)
    {
        CustomLogger logger = CustomLogger.getInstance();
        logger.info("Initialized Create NDIS Service request");
        ServiceResponseDto serviceResponseDto = ndisServiceService.createNDISService(createServiceRequestDto);
        ResponseMessage<ServiceResponseDto> responseMessage = ResponseMessage.created(serviceResponseDto);

        logger.info("NDIS Service created");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
}
