package com.citypass.repositories;

import com.citypass.DButil;
import com.citypass.models.Kupovina;
import com.citypass.response.DBOperationResponse;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class KupovinaRepository {
    // SQL UPITI
    public List<Kupovina> getKupovina() {
        Connection con = null;
        List<Kupovina> result = null;

        try {
            con = DButil.open();
            result = new ArrayList<>();
            String sql = "SELECT * FROM kupovina";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Kupovina kupovina = new Kupovina(
                        rs.getInt("id"),
                        rs.getString("datum"),
                        rs.getInt("korisnik_id")
                );
                result.add(kupovina);
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

    public Kupovina getKupovinaById(int kupovinaId) {
        Connection con = null;
        Kupovina result = null;

        try {
            con = DButil.open();
            String sql = "SELECT * FROM kupovina WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, kupovinaId);

            ResultSet rs = ps.executeQuery();
            rs.next();

            result = new Kupovina(
                    rs.getInt("id"),
                    rs.getString("datum"),
                    rs.getInt("korisnik_id")
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

    public DBOperationResponse addKupovina(Kupovina kupovina) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "INSERT INTO kupovina (datum, korisnik_id) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, kupovina.getDatum());
            ps.setInt(2, kupovina.getKorisnik_id());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Neuspjesno unijeti podaci o kupovini!");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int kupovinaId = generatedKeys.getInt(1);
                result = new DBOperationResponse(true, kupovinaId, "Uspjesno kreirana kupovina!");
            } else {
                throw new SQLException("Nije bilo moguce dobiti ID za kupovinu!");
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

    public DBOperationResponse editKupovina(int kupovinaId, Kupovina kupovina) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "UPDATE kupovina SET datum = ?, korisnik_id = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, kupovina.getDatum());
            ps.setInt(2, kupovina.getKorisnik_id());
            ps.setInt(3, kupovinaId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nije moguce azurirati kupovinu!");
            }

            result = new DBOperationResponse(true, kupovinaId, "Uspjesno izmjenjena kupovina!");
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
    public DBOperationResponse deleteKupovina(int kupovinaId) {
        Connection con = null;
        DBOperationResponse result = null;

        try {
            con = DButil.open();
            String sql = "DELETE FROM kupovina WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, kupovinaId);

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nije moguce izbrisati kupovinu!");
            }

            result = new DBOperationResponse(true, -1, "Uspjesno izbrisana kupovina!");
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
