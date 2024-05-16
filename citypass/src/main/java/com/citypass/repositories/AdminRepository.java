package com.citypass.repositories;

import com.citypass.DButil;
import com.citypass.models.Admin;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepository {

    public List<Admin> getAdmins() {
        Connection con = null;
        List<Admin> result = null;

        try {
            con = DButil.open();
            result = new ArrayList<>();
            String sql = "SELECT * FROM admin";

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("sifra"));
                result.add(admin);
            }

            ps.close();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
        return result;
    }

    public Admin getAdminByUsername(String username) {
        Connection con = null;
        Admin result = null;

        try {
            con = DButil.open();
            String sql = "SELECT * FROM admin WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new Admin();
                result.setUsername(rs.getString("username"));
                result.setPassword(rs.getString("sifra"));
            }

            ps.close();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        return result;
    }

    public DBOperationResponse addAdmin(Admin admin) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "INSERT INTO admin (sifra, username) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, admin.getPassword());
            ps.setString(2, admin.getUsername());


            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Failed to add admin!");
            }

            result = new DBOperationResponse(true, -1, "Admin successfully added!");
        } catch (Exception ex) {
            result = new DBOperationResponse(false, -1, ex.getMessage());
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        return result;
    }

    public DBOperationResponse editAdmin(String username, Admin admin) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "UPDATE admin SET sifra = ? WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, admin.getPassword());
            ps.setString(2, username);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Failed to edit admin!");
            }

            result = new DBOperationResponse(true, -1, "Admin successfully edited!");
        } catch (Exception ex) {
            result = new DBOperationResponse(false, -1, ex.getMessage());
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        return result;
    }

    public DBOperationResponse deleteAdmin(String username) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "DELETE FROM admin WHERE username = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Failed to delete admin!");
            }

            result = new DBOperationResponse(true, -1, "Admin successfully deleted!");
        } catch (Exception ex) {
            result = new DBOperationResponse(false, -1, ex.getMessage());
            System.out.println(ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        return result;
    }
}
