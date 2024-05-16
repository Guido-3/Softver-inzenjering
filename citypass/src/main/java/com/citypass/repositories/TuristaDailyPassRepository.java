package com.citypass.repositories;

import com.citypass.DButil;
import com.citypass.models.Korisnik;
import com.citypass.models.TuristaDailyPass;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TuristaDailyPassRepository {
    // SQL UPITI

    public List<TuristaDailyPass> getTuristaDailyPass() {
        Connection con = null;
        List<TuristaDailyPass> result = null;

        try {
            con = DButil.open();
            result = new ArrayList<>();
            String sql = "SELECT * FROM turista_daily_pass";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TuristaDailyPass turistaDailyPass = new TuristaDailyPass(
                        rs.getInt("turista_id"),
                        rs.getInt("daily_pass_id"),
                        rs.getDate("datum_od"),
                        rs.getDate("datum_do"),
                        rs.getDouble("ukupna_cijena"),
                        rs.getInt("kupovina_id")
                );
                result.add(turistaDailyPass);
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

    public TuristaDailyPass getTuristaDailyPassById(int turistaId, int dailyPassId) {
        Connection con = null;
        TuristaDailyPass result = null;

        try {
            con = DButil.open();
            String sql = "SELECT * FROM TURISTA_DAILY_PASS WHERE turista_id=? AND daily_pass_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, turistaId);
            ps.setInt(2, dailyPassId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            result = new TuristaDailyPass(
                rs.getInt("turista_id"),
                rs.getInt("daily_pass_id"),
                rs.getDate("datum_od"),
                rs.getDate("datum_do"),
                rs.getDouble("ukupna_cijena"),
                rs.getInt("kupovina_id")
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


    public DBOperationResponse editTuristaDailyPass(int turistaId, int dailyPassId, TuristaDailyPass turistaDailyPass) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "UPDATE turista_daily_pass SET datum_od = ?, datum_do = ?, ukupna_cijena = ?, kupovina_id = ? WHERE turista_id = ? AND daily_pass_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            // POGLEDATI OVO ISPOD!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            ps.setDate(1, new java.sql.Date(turistaDailyPass.getDatumOd().getTime()));
            ps.setDate(2, new java.sql.Date(turistaDailyPass.getDatumDo().getTime()));
            ps.setDouble(3, turistaDailyPass.getUkupnaCijena());
            ps.setInt(4, turistaDailyPass.getKupovinaId());
            ps.setInt(5, turistaId);
            ps.setInt(6, dailyPassId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nije moguce azurirati podatke o turista_daily_pass!");
            }

            result = new DBOperationResponse(true, -1, "Uspješno izmijenjeni podaci o turista_daily_pass!");
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

    public DBOperationResponse addTuristaDailyPass(TuristaDailyPass turistaDailyPass) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "INSERT INTO TURISTA_DAILY_PASS (turista_id, daily_pass_id, datum_od, datum_do, ukupna_cijena, kupovina_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, turistaDailyPass.getTuristaId());
            ps.setInt(2, turistaDailyPass.getDailyPassId());
            ps.setDate(3, new java.sql.Date(turistaDailyPass.getDatumOd().getTime()));
            ps.setDate(4, new java.sql.Date(turistaDailyPass.getDatumDo().getTime()));
            ps.setDouble(5, turistaDailyPass.getUkupnaCijena());
            ps.setInt(6, turistaDailyPass.getKupovinaId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Neuspjesno unijeti podaci o turista_daily_pass!");
            }

            result = new DBOperationResponse(true, -1, "Uspjesno kreiran turista_daily_pass!");

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


    public DBOperationResponse deleteTuristaDailyPass(int turistaId, int dailyPassId) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "DELETE FROM turista_daily_pass WHERE turista_id = ? AND daily_pass_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, turistaId);
            ps.setInt(2, dailyPassId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nije moguće izbrisati turista_daily_pass!");
            }

            result = new DBOperationResponse(true, -1, "Uspješno izbrisana turista_daily_pass!");

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
