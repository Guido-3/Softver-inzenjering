package com.citypass.services;

import com.citypass.models.Admin;
import com.citypass.repositories.AdminRepository;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository _adminRepository) {
        this.adminRepository = _adminRepository;
    }

    public List<Admin> getAdmins() {
        return this.adminRepository.getAdmins();
    }

    public Admin getAdminByUsername(String username) {
        return this.adminRepository.getAdminByUsername(username);
    }

    public DBOperationResponse addAdmin(Admin a) {
        return this.adminRepository.addAdmin(a);
    }

    public DBOperationResponse editAdmin(String username, Admin a) {
        return this.adminRepository.editAdmin(username, a);
    }

    public DBOperationResponse deleteAdmin(String username) {
        return this.adminRepository.deleteAdmin(username);
    }
}
