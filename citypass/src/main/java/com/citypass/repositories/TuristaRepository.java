package com.citypass.repositories;

import com.citypass.DButil;
import com.citypass.models.Turista;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TuristaRepository {
    public List<Turista> getTurista() {
        Connection con = null;
        List<Turista> result = null;

        try {
            con = DButil.open();
            result = new ArrayList<>();
            String sql = "SELECT * FROM turista";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Turista t = new Turista(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("drzava_ime")
                );
                result.add(t);
            }

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

    public Turista getTuristaById(int turistaId) {
        Connection con = null;
        Turista result = null;

        try {
            con = DButil.open();
            String sql = "SELECT * FROM turista WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, turistaId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new Turista(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("drzava_ime")
                );
            }

            ps.close();
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

    public DBOperationResponse addTurista(Turista t) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "INSERT INTO turista (ime, prezime, drzava_ime) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, t.getIme());
            ps.setString(2, t.getPrezime());
            ps.setString(3, t.getDrzava_ime());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Neuspješno unijet turista!");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int turistaId = generatedKeys.getInt(1);
                result = new DBOperationResponse(true, turistaId, "Uspješno kreiran turista!");
            } else {
                throw new SQLException("Nije bilo moguće dobiti ID za turista!");
            }

            ps.close();
            con.close();
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

    public DBOperationResponse editTurista(int turistaId, Turista t) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "UPDATE turista SET ime = ?, prezime = ?, drzava_ime = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, t.getIme());
            ps.setString(2, t.getPrezime());
            ps.setString(3, t.getDrzava_ime());
            ps.setInt(4, turistaId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nije moguće izmijeniti turistu!");
            }

            result = new DBOperationResponse(true, turistaId, "Uspješno izmijenjen turista!");
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

    public DBOperationResponse deleteTurista(int turistaId) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "DELETE FROM turista WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, turistaId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nije moguće izbrisati turistu!");
            }

            result = new DBOperationResponse(true, -1, "Uspješno izbrisan turista!");
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

