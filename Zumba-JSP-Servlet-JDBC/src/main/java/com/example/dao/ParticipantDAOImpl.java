package com.example.dao;

import com.example.model.Participant;
import com.example.model.Batch;
import java.sql.*;
import java.util.*;

public class ParticipantDAOImpl implements ParticipantDAO{
    // JDBC URL pointing at the file you created above:
    private final String jdbcURL = "jdbc:sqlite:/Users/deepa/BackendCapstoneProjects/zumba.db";
    private final BatchDAO batchDAO = new BatchDAOImpl();

    //addParticipant function
    @Override
    public void addParticipant(Participant participant) throws SQLException {
        String sql = "INSERT INTO participant(name, phone, email, batch_id) VALUES(?,?,?,?)";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, participant.getName());
            ps.setString(2, participant.getPhone());
            ps.setString(3, participant.getEmail());
            ps.setInt(4, participant.getBatch_id());
            ps.executeUpdate();

             String participantBatch = participant.getName();
                Integer batchId = null;
                if (participantBatch != null && !participantBatch.isEmpty()) {
                    List<Batch> batches = batchDAO.getAllBatches();
                    for (Batch cat : batches) {
                        if (cat.getName().equals(participantBatch)) {
                            batchId = cat.getId();
                            break;
                        }
                    }
                }
                ps.setObject(3, batchId);
                ps.executeUpdate();
            }
       }
    }

    //getAllParticipant function
    @Override
    public List<Participant> getAllParticipants() throws SQLException {
        System.out.println(">>> getAllParticipants() called");
        List<Participant> list = new ArrayList<>();
        String sql = "SELECT id,name,phone,email,batch_id FROM Participant";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Participant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getInt("batch_id")
                ));
            }
        }
        for (Participant b : list) {
            System.out.println(b.getName() + " by " + b.getPhone() + " by " + b.getEmail() + " by " + b.getBatch_id());
        }
        return list;
    }

    //deleteParticipant function
    @Override
    public void deleteParticipant(int id) throws SQLException {
        String sql = "DELETE FROM participants WHERE id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    //updateParticipant function
    @Override
    public boolean updateParticipant(Participant participant) throws SQLException {
        String sql = "UPDATE books SET title = ?, author = ? WHERE id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement stmt = c.prepareStatement(sql)) {
            stmt.setString(1, participant.getName());
            stmt.setString(2, participant.getPhone());
            stmt.setString(3, participant.getEmail());
            stmt.setInt(4, participant.getBatch_id());

            return stmt.executeUpdate() > 0;
        }
    }

    //getParticipant function
    @Override
    public Participant getParticipant(int id) throws SQLException {
        String sql = "SELECT id, name, phone, email, bath_id FROM books WHERE id = ?";
        try (Connection c = DriverManager.getConnection(jdbcURL);
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Participant(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getInt("batch_id")
                    );
                }
                return null;
            }
        }
    }



}
