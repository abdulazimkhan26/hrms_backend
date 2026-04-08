package com.hrms.admin.controllers;

import com.hrms.admin.dto.RolesRequest;
import com.hrms.admin.dto.RolesResponse;
import com.hrms.admin.entity.Roles;
import com.hrms.admin.service.RolesService;
import jakarta.ws.rs.GET;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class RolesController {
    private RolesService rolesService;

    public RolesController(RolesService rolesService){
        this.rolesService = rolesService;
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<String>> all_Roles(){
        return ResponseEntity.status(200).body(rolesService.allRoles());
    }

    @PostMapping("/create_roles")
    public ResponseEntity<RolesResponse> create_Roles(@RequestBody RolesRequest request){
        return ResponseEntity.status(200).body(rolesService.createRoles(request));
    }

    @PostMapping("/update_Roles/{name}")
    public ResponseEntity<RolesResponse> update_Roles(@PathVariable String name, @RequestBody RolesRequest request){
        return ResponseEntity.status(200).body(rolesService.updateRoles(name, request));
    }

    @PostMapping("/delete_Roles/{name}")
    public ResponseEntity<RolesResponse> delete_Roles(@PathVariable String name){
        return ResponseEntity.status(200).body(rolesService.deleteRoles(name));
    }
}
