package com.citypass.repositories;

import com.citypass.DButil;
import com.citypass.models.Korisnik;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class KorisnikRepository {
    // SQL UPITI
    public List<Korisnik> getKorisnik() {
        Connection con = null;
        List<Korisnik> result = null;

        try {
            con = DButil.open();
            result = new ArrayList<>();
            String sql = "SELECT * FROM korisnik";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Korisnik korisnik = new Korisnik(
                        rs.getInt("id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("email"),
                        rs.getString("br_telefona"),
                        rs.getString("drzava_ime"),
                        rs.getString("admin")
                );
                result.add(korisnik);
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

    public Korisnik getKorisnikById(int korisnikId) {
        Connection con = null;
        Korisnik result = null;

        try {
            con = DButil.open();
            String sql = "SELECT * FROM korisnik WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, korisnikId);

            ResultSet rs = ps.executeQuery();
            rs.next();

            result = new Korisnik(
                    rs.getInt("id"),
                    rs.getString("ime"),
                    rs.getString("prezime"),
                    rs.getString("email"),
                    rs.getString("br_telefona"),
                    rs.getString("drzava_ime"),
                    rs.getString("admin")
            );
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

    public DBOperationResponse addKorisnik(Korisnik korisnik) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "INSERT INTO korisnik (ime, prezime, email, br_telefona, drzava_ime, admin) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, korisnik.getIme());
            ps.setString(2, korisnik.getPrezime());
            ps.setString(3, korisnik.getEmail());
            ps.setString(4, korisnik.getBr_telefona());
            ps.setString(5, korisnik.getDrzava_ime());
            ps.setString(6, korisnik.getAdmin());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Neuspjesno unijeti podaci o korisniku!");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int korisnikId = generatedKeys.getInt(1);
                result = new DBOperationResponse(true, korisnikId, "Uspjesno kreiran korisnik!");
            } else {
                throw new SQLException("Nije bilo moguce dobiti ID za korisnika!");
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

    public DBOperationResponse editKorisnik(int korisnikId, Korisnik korisnik) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "UPDATE korisnik SET ime = ?, prezime = ?, email = ?, br_telefona = ?, drzava_ime = ?, admin = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, korisnik.getIme());
            ps.setString(2, korisnik.getPrezime());
            ps.setString(3, korisnik.getEmail());
            ps.setString(4, korisnik.getBr_telefona());
            ps.setString(5, korisnik.getDrzava_ime());
            ps.setString(6, korisnik.getAdmin());
            ps.setInt(7, korisnikId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nije moguce azurirati podatke o korisniku!");
            }

            result = new DBOperationResponse(true, korisnikId, "Uspjesno izmjenjeni podaci o korisniku!");
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

    public DBOperationResponse deleteKorisnik(int korisnikId) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "DELETE FROM korisnik WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, korisnikId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nije moguce izbrisati korisnika!");
            }

            result = new DBOperationResponse(true, -1, "Uspjesno izbrisan korisnik!");

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
