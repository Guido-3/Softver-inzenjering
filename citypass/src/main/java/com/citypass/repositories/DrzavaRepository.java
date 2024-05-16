package com.citypass.repositories;

import com.citypass.DButil;
import com.citypass.models.Drzava;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DrzavaRepository {
    // SQL UPITI
    public List<Drzava> getDrzava() {
        Connection con = null;
        List<Drzava> result = null;

        try {
            con = DButil.open();
            result = new ArrayList<Drzava>();
            String sql = "select * from drzava";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Drzava d = new Drzava(rs.getString("ime"));
                result.add(d);
            }

            ps.close();
            con.close();
        } catch (Exception ex){
            result = null;
            System.out.println(ex);
        }
        finally {
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

    public Drzava getDrzavaByIme(String drzavaIme){
        Connection con = null;
        Drzava result = null;

        try {
            con= DButil.open();
            String sql = "select * from drzava where ime=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, drzavaIme);

            ResultSet rs = ps.executeQuery();
            rs.next();

            result = new Drzava(rs.getString("ime"));
            ps.close();
            con.close();
        } catch (Exception ex) {
            result = null;
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

    public DBOperationResponse addDrzava(Drzava d){
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con= DButil.open();
            String sql = "insert into drzava (ime) values(?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, d.getIme());

            int affectedRows = ps.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Neuspjesno unijeta drzava!");
            }
            result = new DBOperationResponse(true, -1, "Uspjesno kreirana drzava!");
        }
        catch(Exception ex){
            result = new DBOperationResponse(false, -1, ex.getMessage());
            System.out.println(ex);
        }
        finally {
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

    public DBOperationResponse editDrzava(String drzavaIme, Drzava d){
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "UPDATE drzava SET ime = ? WHERE ime = ?";
            PreparedStatement ps = con.prepareStatement(sql);


            ps.setString(1, d.getIme());
            ps.setString(2, drzavaIme);


            int affectedRows = ps.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Nije moguce azurirati drzavu!");
            }

            result = new DBOperationResponse(true, -1, "Uspješno izmijenjena država!");
        }catch (Exception ex){
            result = new DBOperationResponse(false, -1, ex.getMessage());
            System.out.println(ex);
        }
        finally {
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

    public DBOperationResponse deleteDrzava(String drzavaIme){
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "DELETE FROM drzava WHERE ime = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, drzavaIme);

            int affectedRows = ps.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Nije moguce izbrisati drzavu!");
            }

            result = new DBOperationResponse(true, -1, "Uspjesno izbrisana drzava!");
        } catch (Exception ex){
            result = new DBOperationResponse(false, -1, ex.getMessage());
            System.out.println(ex);
        }
        finally {
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
