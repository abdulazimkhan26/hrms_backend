package com.hrms.admin.service;

import com.hrms.admin.dto.RolesRequest;
import com.hrms.admin.dto.RolesResponse;
import com.hrms.admin.entity.Roles;
import com.hrms.admin.repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RolesService {
    private final RolesRepository rolesRepository;
    public RolesService(RolesRepository rolesRepository){
        this.rolesRepository = rolesRepository;
    }

    public List<String> allRoles(){return rolesRepository.findAllNames();}

    public RolesResponse createRoles(RolesRequest request){
        if(rolesRepository.existsByName(request.getName())){
            return new RolesResponse("Role: " + request.getName() + " already exists!", request.getName(), request.getDescription(), false);
        }

        Roles role = new Roles();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setCreated_at(OffsetDateTime.now());

        rolesRepository.save(role);
        return new RolesResponse("Role: " + request.getName() + " successfully added.", request.getName(), request.getDescription(), true);
    }

    public RolesResponse updateRoles(String name, RolesRequest request){
        Roles role = rolesRepository.findByName(name).orElseThrow(() -> new RuntimeException(name + "not found!"));

        role.setName(request.getName());
        role.setDescription(request.getDescription());

        rolesRepository.save(role);

        return new RolesResponse("Role: " + request.getName() + " successfully updated.", request.getName(), request.getDescription(), true);
    }

    public RolesResponse deleteRoles(String name){
        Roles role = rolesRepository.findByName(name).orElseThrow(() -> new RuntimeException(name +" not found!"));

        rolesRepository.delete(role);

        return new RolesResponse("Role: " + name + " successfully deleted.", "", "", true);
    }
}
