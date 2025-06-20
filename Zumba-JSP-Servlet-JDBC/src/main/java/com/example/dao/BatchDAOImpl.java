package com.example.dao;

import com.example.model.Batch;
import java.sql.*;
import java.util.*;

public class BatchDAOImpl implements BatchDAO{
    private final String jdbcURL = "jdbc:sqlite:zumba.db";

    //addBatch
    @Override
    public void addBatch(Batch batch) throws SQLException {
        String sql = "INSERT INTO batches(name, description) VALUES(?,?)";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, batch.getName());
            ps.setString(2, batch.getDescription());
            ps.executeUpdate();
        }
    }

    //addAllBathes
    public List<Batch> getAllBatches() throws SQLException {
        List<Batch> list = new ArrayList<>();
        String sql = "SELECT id, name, description FROM batches";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Batch(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        }
        return list;
    }

    //deleteBatch
    @Override
    public void deleteBatch(int id) throws SQLException {
        // First update all participant that reference this batch to set batch_id to null
        String updateParticipants = "UPDATE participants SET batch_id = NULL WHERE batch_id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(updateParticipants)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }

        // Then delete the batch
        String sql = "DELETE FROM batches WHERE id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    //updateBatch
    @Override
    public boolean updateBatch(Batch batch) throws SQLException {
        String sql = "UPDATE batches SET name = ?, description = ? WHERE id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, batch.getName());
            stmt.setString(2, batch.getDescription());
            stmt.setInt(3, batch.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    //getBatch
    @Override
    public Batch getBatch(int id) throws SQLException {
        String sql = "SELECT id, name, description FROM batches WHERE id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Batch(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description")
                    );
                }
                return null;
            }
        }
    }
}
