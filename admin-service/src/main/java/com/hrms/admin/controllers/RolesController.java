package com.hrms.admin.controllers;

import com.hrms.admin.dto.RolesRequest;
import com.hrms.admin.dto.RolesResponse;
import com.hrms.admin.entity.Roles;
import com.hrms.admin.projection.RoleProjection;
import com.hrms.admin.service.RolesService;
import jakarta.ws.rs.GET;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class RolesController {
    private RolesService rolesService;

    public RolesController(RolesService rolesService){
        this.rolesService = rolesService;
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<RoleProjection>> all_Roles(){
        return ResponseEntity.status(200).body(rolesService.allRoles());
    }

    @PostMapping("/create_roles")
    public ResponseEntity<RolesResponse> create_Roles(@RequestBody RolesRequest request){
        return ResponseEntity.status(200).body(rolesService.createRoles(request));
    }

    @PutMapping("/update_Roles/{id}")
    public ResponseEntity<RolesResponse> update_Roles(@PathVariable UUID id, @RequestBody RolesRequest request){
        return ResponseEntity.status(200).body(rolesService.updateRoles(id, request));
    }

    @DeleteMapping("/delete_Roles/{id}")
    public ResponseEntity<RolesResponse> delete_Roles(@PathVariable UUID id){
        return ResponseEntity.status(200).body(rolesService.deleteRoles(id));
    }
}
