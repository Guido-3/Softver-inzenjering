package com.citypass.controllers;

import com.citypass.models.Admin;
import com.citypass.response.DBOperationResponse;
import com.citypass.services.AdminService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/citypass-api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<Admin> getAdmins() {
        return this.adminService.getAdmins();
    }

    @GetMapping(value = "/{username}")
    public Admin getAdminByUsername(@PathVariable("username") String username) {
        return this.adminService.getAdminByUsername(username);
    }

    @PostMapping
    public DBOperationResponse addAdmin(@RequestBody Admin a) {
        return this.adminService.addAdmin(a);
    }

    @PutMapping(value = "/{username}")
    public DBOperationResponse editAdmin(@PathVariable("username") String username, @RequestBody Admin a) {
        return this.adminService.editAdmin(username, a);
    }

    @DeleteMapping(value = "/{username}")
    public DBOperationResponse deleteAdmin(@PathVariable("username") String username) {
        return this.adminService.deleteAdmin(username);
    }
}
