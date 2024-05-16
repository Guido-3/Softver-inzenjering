package com.citypass.repositories;

import com.citypass.DButil;
import com.citypass.models.Znamenitost;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ZnamenitostRepository {
    // SQL UPITI
    public List<Znamenitost> getZnamenitost() {
        Connection con = null;
        List<Znamenitost> result = null;

        try {
            con = DButil.open();
            result = new ArrayList<Znamenitost>();
            String sql = "select * from znamenitost";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //napravi znamenitost (objekat tipa znamenitost) i dodaj ga u listu
                Znamenitost z = new Znamenitost(rs.getInt("id"), rs.getString("ime"), rs.getString("opis"), rs.getString("slika"), rs.getString("admin"));
                result.add(z);
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

    public Znamenitost getZnamenitostById(int znamenitostId){
        Connection con = null;
        Znamenitost result = null;

        try {
            con= DButil.open();
            String sql = "select * from znamenitost where id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, znamenitostId);

            ResultSet rs = ps.executeQuery();
            rs.next();

            result = new Znamenitost(rs.getInt("id"), rs.getString("ime"), rs.getString("opis"), rs.getString("slika"), rs.getString("admin"));
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

    public DBOperationResponse addZnamenitost(Znamenitost z){
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con= DButil.open();
            String sql = "insert into znamenitost (ime, opis, slika, admin) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, z.getIme());
            ps.setString(2, z.getOpis());
            ps.setString(3, z.getSlika());
            ps.setString(4, z.getAdmin());

            int affectedRows = ps.executeUpdate();

            if(affectedRows == 0){
                throw new SQLException("Neuspjesno unijeta znamenitost!");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                int znamenitostId = generatedKeys.getInt(1);
                result = new DBOperationResponse(true, znamenitostId, "Uspjesno kreirana znamenitost!");
            } else {
                throw new SQLException("Nije bilo moguce dobiti ID za znamenitost!");
            }

            ps.close();
            con.close();
        }  catch (Exception ex){
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

    public DBOperationResponse editZnamenitost(int znamenitostId, Znamenitost z){
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "UPDATE znamenitost SET ime = ?, opis = ?, slika = ?, admin = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, z.getIme());
            ps.setString(2, z.getOpis());
            ps.setString(3, z.getSlika());
            ps.setString(4, z.getAdmin());
            ps.setInt(5, znamenitostId);

            int affectedRows = ps.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Nije moguce azurirati znamenitost!");
            }

            result = new DBOperationResponse(true, znamenitostId, "Uspjesno izmjenjena znamenitost!");
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

    public DBOperationResponse deleteZnamenitost(int znamenitostId){
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "delete from znamenitost where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, znamenitostId);

            int affectedRows = ps.executeUpdate();
            if(affectedRows == 0){
                throw new SQLException("Nije moguce izbrisati znamenitost!");
            }

            result = new DBOperationResponse(true, -1, "Uspjesno izbrisana znamenitost!");
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
