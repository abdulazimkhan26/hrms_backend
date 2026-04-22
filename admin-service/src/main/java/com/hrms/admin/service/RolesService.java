package com.hrms.admin.service;

import com.hrms.admin.dto.RolesRequest;
import com.hrms.admin.dto.RolesResponse;
import com.hrms.admin.entity.Roles;
import com.hrms.admin.projection.RoleProjection;
import com.hrms.admin.repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RolesService {
    private final RolesRepository rolesRepository;
    public RolesService(RolesRepository rolesRepository){
        this.rolesRepository = rolesRepository;
    }

    public List<RoleProjection> allRoles(){return rolesRepository.findAllNames();}

    public RolesResponse createRoles(RolesRequest request){
        if(rolesRepository.existsByName(request.getName())){
            return new RolesResponse("Role: " + request.getName() + " already exists!", request.getName(), request.getDescription(), false);
        }

        Roles role = new Roles();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setUpdatedBy(request.getUpdatedBy());
        role.setCreated_at(LocalDateTime.now());
        role.setUpdated_at(LocalDateTime.now());

        rolesRepository.save(role);
        return new RolesResponse("Role: " + request.getName() + " successfully added.", request.getName(), request.getDescription(), true);
    }

    public RolesResponse updateRoles(UUID id, RolesRequest request){
        Roles role = rolesRepository.findById(id).orElseThrow(() -> new RuntimeException(id + "not found!"));

        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setUpdatedBy(request.getUpdatedBy());
        role.setUpdated_at(LocalDateTime.now());

        rolesRepository.save(role);

        return new RolesResponse("Role: " + request.getName() + " successfully updated.", role.getName(), role.getDescription(), true);
    }


    public RolesResponse deleteRoles(UUID id){
        Roles role = rolesRepository.findById(id).orElseThrow(() -> new RuntimeException(id +" not found!"));

        rolesRepository.delete(role);
        return new RolesResponse("Role: " + role.getName() + " successfully deleted.", "", "", true);
    }
} 
