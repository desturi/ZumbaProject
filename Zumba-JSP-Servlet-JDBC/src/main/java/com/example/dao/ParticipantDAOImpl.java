package com.example.dao;

import com.example.model.Participant;
import com.example.model.Batch;
import java.sql.*;
import java.util.*;

public class ParticipantDAOImpl implements ParticipantDAO{
    // JDBC URL pointing at the file you created above:
    private final String jdbcURL = "jdbc:sqlite:zumba.db";
    private final BatchDAO batchDAO = new BatchDAOImpl();

    @Override
    public void addParticipant(Participant participant) throws SQLException {
        String sql = "INSERT INTO participants(name, phone, email, batch_id) VALUES(?,?,?,?)";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, participant.getName());
            ps.setString(2, participant.getPhone());
            ps.setString(3, participant.getEmail());
            ps.setObject(4, participant.getBatch() != null ? participant.getBatch().getId() : null);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Participant> getAllParticipants() throws SQLException {
        System.out.println(">>> getAllParticipants() called");
        List<Participant> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.phone, p.email, p.batch_id, b.name as batch_name, b.description as batch_description " +
                "FROM participants p " +
                "LEFT JOIN batches b ON p.batch_id = b.id";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Batch batch = null;
                if (rs.getObject("batch_id") != null) {
                    batch = new Batch(
                            rs.getInt("batch_id"),
                            rs.getString("batch_name"),
                            rs.getString("batch_description")
                    );
                }
                Participant participant = new Participant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        batch == null ? 0 : batch.getId()
                );
                participant.setBatch(batch);
                list.add(participant);
            }
        }
        for (Participant p : list) {
            System.out.println(p.getName() + " by " + p.getPhone() + " by " + p.getEmail());
        }
        return list;
    }

    @Override
    public void deleteParticipant(int id) throws SQLException {
        String sql = "DELETE FROM participants WHERE id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    @Override
    public boolean updateParticipant(Participant participant) throws SQLException {
        String sql = "UPDATE participants SET name = ?, phone = ?, email = ?, batch_id = ? WHERE id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, participant.getName());
            stmt.setString(2, participant.getPhone());
            stmt.setString(3, participant.getEmail());
            stmt.setObject(4, participant.getBatch() != null ? participant.getBatch().getId() : null);
            stmt.setInt(5, participant.getId());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Participant getParticipant(int id) throws SQLException {
        String sql = "SELECT p.id, p.name, p.phone, p.email, p.batch_id, b.name as batch_name, b.description as batch_description " +
                "FROM participants p " +
                "LEFT JOIN batches b ON p.batch_id = b.id " +
                "WHERE p.id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Batch batch = null;
                    if (rs.getObject("batch_id") != null) {
                        batch = new Batch(
                                rs.getInt("batch_id"),
                                rs.getString("batch_name"),
                                rs.getString("batch_description")
                        );
                    }
                    Participant participant = new Participant(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            batch == null ? 0 : batch.getId()
                    );
                    participant.setBatch(batch);
                    return participant;
                }
                return null;
            }
        }
    }

    @Override
    public List<Participant> getParticipantByBatch(int batchId) throws SQLException {
        List<Participant> list = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.phone, p.email, p.batch_id, b.name as batch_name, b.description as batch_description " +
                "FROM participants p " +
                "LEFT JOIN batches b ON p.batch_id = b.id " +
                "WHERE p.batch_id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, batchId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Batch batch = new Batch(
                            rs.getInt("batch_id"),
                            rs.getString("batch_name"),
                            rs.getString("batch_description")
                    );
                    Participant participant = new Participant(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            batch.getId()
                    );
                    participant.setBatch(batch);
                    list.add(participant);
                }
            }
        }
        return list;
    }
}
