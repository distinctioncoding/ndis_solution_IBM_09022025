package com.larry.service;
import com.larry.pojo.NDISService;
import com.larry.pojo.ServiceType;
import com.larry.pojo.User;
import com.larry.pojo.dto.CreateServiceRequestDto;
import com.larry.pojo.dto.ServiceResponseDto;
import com.larry.pojo.exception.ResourceNotFoundException;
import com.larry.pojo.notification.Notification;
import com.larry.pojo.notification.NotificationFactory;
import com.larry.repository.NDISServiceRepository;
import com.larry.repository.ServiceTypeRepository;
import com.larry.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class NDISServiceService {
    private ServiceTypeRepository serviceTypeRepository;
    private UserRepository userRepository;
    private NDISServiceRepository ndisServiceRepository;

    @Autowired
    public NDISServiceService(ServiceTypeRepository serviceTypeRepository, UserRepository userRepository, NDISServiceRepository ndisServiceRepository) {
        this.serviceTypeRepository = serviceTypeRepository;
        this.userRepository = userRepository;
        this.ndisServiceRepository = ndisServiceRepository;
    }


    public ServiceResponseDto createNDISService(CreateServiceRequestDto createServiceRequestDto)
    {
        NDISService ndisService = new NDISService();

        //get ServiceType and assign to NDISService entity
        ServiceType serviceType = serviceTypeRepository.findById(createServiceRequestDto.getServiceTypeId()).orElseThrow(()-> new ResourceNotFoundException("Service Type Not Found"));
        User user = userRepository.findById(createServiceRequestDto.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User not found"));

        ndisService.setServiceName(createServiceRequestDto.getServiceName());
        ndisService.setServiceType(serviceType);
        ndisService.setDescription(createServiceRequestDto.getDescription());
        ndisService.setPrice(createServiceRequestDto.getPrice());
        ndisService.setProvider(user);

        NDISService newService = ndisServiceRepository.save(ndisService);

        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();

        serviceResponseDto.setServiceId(newService.getServiceId());
        serviceResponseDto.setServiceName(newService.getServiceName());
        serviceResponseDto.setDescription(newService.getDescription());
        serviceResponseDto.setPrice(newService.getPrice());
        serviceResponseDto.setServiceTypeName(serviceType.getServiceTypeName());
        serviceResponseDto.setProviderName(newService.getProvider().getFullName());

        Notification notification = NotificationFactory.createNotification("SMS");
        notification.send("test@exmaple.com", "NEW Service");

        return serviceResponseDto;
    }
}
