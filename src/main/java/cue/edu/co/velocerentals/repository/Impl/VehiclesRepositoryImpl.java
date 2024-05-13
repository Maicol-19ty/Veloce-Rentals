package cue.edu.co.velocerentals.repository.Impl;

import cue.edu.co.velocerentals.annotations.MySqlConn;
import cue.edu.co.velocerentals.database.DataBaseConnection;
import cue.edu.co.velocerentals.enums.VehicleStatus;
import cue.edu.co.velocerentals.enums.VehicleType;
import cue.edu.co.velocerentals.exceptions.ServiceJdbcException;
import cue.edu.co.velocerentals.models.Vehicles;
import cue.edu.co.velocerentals.repository.VehiclesRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class VehiclesRepositoryImpl implements VehiclesRepository<Vehicles> {

    @Inject
    @MySqlConn
    private Connection conn;

    private Vehicles mapToVehicle(ResultSet rs) throws SQLException {
        return new Vehicles(
                rs.getInt("vehicle_id"),
                VehicleType.fromString(rs.getString("type")),
                rs.getString("make"),
                rs.getString("model"),
                Year.of(rs.getInt("year")),
                rs.getDouble("price_per_day"),
                VehicleStatus.fromString(rs.getString("status"))
        );
    }

    @Override
    public List<Vehicles> listVehicles() {

        PreparedStatement stmt = null;

        List<Vehicles> vehicles = new ArrayList<>();

        try {

            conn = DataBaseConnection.getInstance();
            conn.setAutoCommit(false);

            String sqlListVehicles = "SELECT * FROM vehicles";
            stmt = conn.prepareStatement(sqlListVehicles);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }
        } catch (SQLException | ServiceJdbcException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ignore) {}
        }
        return vehicles;
    }

    @Override
    public List<Vehicles> listVehiclesStatus(VehicleStatus status) {

        PreparedStatement stmt = null;

        List<Vehicles> vehicles = new ArrayList<>();

        try {

            conn = DataBaseConnection.getInstance();
            conn.setAutoCommit(false);

            String sqlListVehicles = "SELECT * FROM vehicles WHERE LOWER(status) = LOWER(?)";
            stmt = conn.prepareStatement(sqlListVehicles);

            stmt.setString(1, status.name());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }
        } catch (SQLException | ServiceJdbcException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ignore) {}
        }
        return vehicles;
    }

    @Override
    public List<Vehicles> listVehiclesType(VehicleType type) {
        PreparedStatement stmt = null;

        List<Vehicles> vehicles = new ArrayList<>();

        try {

            conn = DataBaseConnection.getInstance();
            conn.setAutoCommit(false);

            String sqlListVehicles = "SELECT * FROM vehicles WHERE LOWER(type) = LOWER(?)";
            stmt = conn.prepareStatement(sqlListVehicles);

            stmt.setString(1, type.name());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }

        } catch (SQLException | ServiceJdbcException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ignore) {}
        }

        return vehicles;
    }

    @Override
    public List<Vehicles> listVehiclesPriceAsc() {
        PreparedStatement stmt = null;

        List<Vehicles> vehicles = new ArrayList<>();

        try {

            conn = DataBaseConnection.getInstance();
            conn.setAutoCommit(false);

            String sqlListVehicles = "SELECT * FROM vehicles ORDER BY price_per_day ASC";
            stmt = conn.prepareStatement(sqlListVehicles);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }

        } catch (SQLException | ServiceJdbcException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ignore) {}
        }

        return vehicles;
    }

    @Override
    public List<Vehicles> listVehiclesPriceDesc() {
        PreparedStatement stmt = null;

        List<Vehicles> vehicles = new ArrayList<>();

        try {

            conn = DataBaseConnection.getInstance();
            conn.setAutoCommit(false);

            String sqlListVehicles = "SELECT * FROM vehicles ORDER BY price_per_day DESC";
            stmt = conn.prepareStatement(sqlListVehicles);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapToVehicle(rs));
            }

        } catch (SQLException | ServiceJdbcException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (stmt != null) try { stmt.close(); } catch (SQLException ignore) {}
            if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ignore) {}
        }

        return vehicles;
    }
}
