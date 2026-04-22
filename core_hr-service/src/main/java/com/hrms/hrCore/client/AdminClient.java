package com.hrms.hrCore.client;

import com.hrms.hrCore.dtos.responses.LookupValueResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

// client/AdminClient.java
@FeignClient(name = "admin-service", url = "${services.admin.url}")
public interface AdminClient {

    @GetMapping("/lookupvalue/{code}")
    LookupValueResponse getLookupValueByCode(@PathVariable String code);

}