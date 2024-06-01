package com.citypass.repositories;

import com.citypass.DButil;
import com.citypass.models.DailyPass;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DailyPassRepository {

    public List<DailyPass> getDailyPasses() {
        Connection con = null;
        List<DailyPass> result = null;

        try {
            con = DButil.open();
            result = new ArrayList<>();
            String sql = "SELECT * FROM daily_pass";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DailyPass dp = new DailyPass(
                        rs.getInt("id"),
                        rs.getDouble("cijena")
                );
                result.add(dp);
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

    public DailyPass getDailyPassById(int dailyPassId) {
        Connection con = null;
        DailyPass result = null;

        try {
            con = DButil.open();
            String sql = "SELECT * FROM daily_pass WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dailyPassId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new DailyPass(
                        rs.getInt("id"),
                        rs.getDouble("cijena")
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

    public Double getCijenaDailyPassById(int dailyPassId) {
        Connection con = null;
        Double result = null;

        try {
            con = DButil.open();
            String sql = "SELECT cijena FROM daily_pass WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dailyPassId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getDouble("cijena");
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

    public DBOperationResponse addDailyPass(DailyPass dailyPass) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "INSERT INTO daily_pass (cijena) VALUES (?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setDouble(1, dailyPass.getCijena());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Neuspješno unijet daily pass!");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int dailyPassId = generatedKeys.getInt(1);
                result = new DBOperationResponse(true, dailyPassId, "Uspješno kreiran daily pass!");
            } else {
                throw new SQLException("Nije bilo moguće dobiti ID za daily pass!");
            }

            ps.close();
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

    public DBOperationResponse editDailyPass(int dailyPassId, DailyPass dailyPass) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "UPDATE daily_pass SET cijena = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, dailyPass.getCijena());
            ps.setInt(2, dailyPassId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nije moguće izmijeniti daily pass!");
            }

            result = new DBOperationResponse(true, dailyPassId, "Uspješno izmijenjen daily pass!");
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

    public DBOperationResponse deleteDailyPass(int dailyPassId) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "DELETE FROM daily_pass WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, dailyPassId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nije moguće izbrisati daily pass!");
            }

            result = new DBOperationResponse(true, -1, "Uspješno izbrisan daily pass!");
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
