package com.example.dao;

import com.example.model.Participant;
import java.sql.SQLException;
import java.util.List;

public interface ParticipantDAO {
    void addParticipant(Participant participant) throws SQLException;
    List<Participant> getAllParticipants() throws SQLException;
    void deleteParticipant(int id) throws SQLException;
    boolean updateParticipant(Participant participant) throws SQLException;
    Participant getParticipant(int id) throws SQLException;
    List<Participant> getParticipantByBatch(int batchId) throws SQLException;
}
