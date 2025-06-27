package com.bodemer.myarmo.database;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class Abfrage {
    private static final Logger logger = LoggerFactory.getLogger(Abfrage.class);

    private String dbUrl = "jdbc:postgresql://127.0.0.1:5432/e-softworks";

    private String dbUser = "bodi";

    private String dbPassword = "BodiSteinbergOlga#2021";

    private Properties setProperties() {
        Properties props = new Properties();
        props.setProperty("user", dbUser);
        props.setProperty("password", dbPassword);
        props.setProperty("charSet", "UTF8");
        props.setProperty("sslmode", "disable");
        return props;
    }

    public ArrayList<ArrayList> getAbfrageErgebnis(String sqlBefehl, String website) {
        ArrayList<ArrayList> ergebnisliste = new ArrayList<>();
        try (Connection con = getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sqlBefehl)) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int gesamtspalten = rsmd.getColumnCount();
            while (rs.next()) {
                ArrayList neueZeile = new ArrayList();
                for (int spaltennummer = 1; spaltennummer <= gesamtspalten; spaltennummer++) {
                    neueZeile.add(rs.getObject(spaltennummer));
                }
                ergebnisliste.add(neueZeile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ergebnisliste;
    }

    public void insertSql(String sqlBefehl) {
        try (Connection con = getConnection(); Statement st = con.createStatement()) {
            st.executeUpdate(sqlBefehl);
        } catch (SQLException e) {
            if (!e.getMessage().contains("No results")) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, setProperties());
    }

    public ArrayList<ArrayList> getAbfrageErgebnis(String sql, Object... params) {
        ArrayList<ArrayList> ergebnisliste = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int gesamtspalten = rsmd.getColumnCount();
                while (rs.next()) {
                    ArrayList<Object> neueZeile = new ArrayList<>();
                    for (int spaltennummer = 1; spaltennummer <= gesamtspalten; spaltennummer++) {
                        neueZeile.add(rs.getObject(spaltennummer));
                    }
                    ergebnisliste.add(neueZeile);
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing query: {}", sql, e);
        }
        return ergebnisliste;
    }

    public void insertSql(String sql, Object... params) {
        try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error executing insert: {}", sql, e);
        }
    }
}
