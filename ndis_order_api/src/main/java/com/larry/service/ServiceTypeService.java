package com.larry.service;
import com.larry.pojo.ServiceType;
import com.larry.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceTypeService {

    private final ServiceTypeRepository serviceTypeRepository;

    @Autowired
    public ServiceTypeService(ServiceTypeRepository serviceTypeRepository){
        this.serviceTypeRepository = serviceTypeRepository;
    }
    public List<ServiceType> getAllServiceTypes() {
        return serviceTypeRepository.findAll();
    }
}
