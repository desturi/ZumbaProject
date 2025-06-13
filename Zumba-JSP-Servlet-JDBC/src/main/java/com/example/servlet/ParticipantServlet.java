package com.example.servlet;
import com.example.dao.ParticipantDAO;
import com.example.dao.ParticipantDAOImpl;
import com.example.dao.BatchDAO;
import com.example.dao.BatchDAOImpl;
import com.example.model.Participant;
import com.example.model.Batch;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/participants")
public class ParticipantServlet extends HttpServlet{
    private final ParticipantDAO participantDAO = new ParticipantDAOImpl();
    private final BatchDAO batchDAO = new BatchDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                participantDAO.deleteParticipant(id);
                resp.sendRedirect(req.getContextPath() + "/participants");
                return;
            }
            else if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Participant participant = participantDAO.getParticipant(id);
                List<Batch> allBatches = batchDAO.getAllBatches();
                req.setAttribute("participant", participant);
                req.setAttribute("allBatches", allBatches);
                req.getRequestDispatcher("/addParticipant.jsp")
                        .forward(req, resp);
                return;
            }
            else if ("add".equals(action)) {
                List<Batch> allBatches = batchDAO.getAllBatches();
                req.setAttribute("allBatches", allBatches);
                req.getRequestDispatcher("/addParticipant.jsp")
                        .forward(req, resp);
                return;
            }

            // default: list all participants
            List<Participant> participants = participantDAO.getAllParticipant();
            req.setAttribute("listParticipant", participants);
            req.getRequestDispatcher("/listParticipants.jsp")
                    .forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Database error in ParticipantServlet", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String email = req.getParameter("email");
            int batch_id = Integer.parseInt(req.getParameter("batch_id"));
            //String batchId = req.getParameter("batch");

            if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Participant participant = new Participant(id, name, phone, email, batch_id);
                if (batch_id != null && !batch_id) {
                    Batch batch = batchDAO.getBatch(Integer.parseInt(String.valueOf(batchId)));
                    participant.setBatchesName(batch.getName());
                }
                participantDAO.updateParticipant(participant);
            } else {
                // covers both "insert" and (if missing) any other case
                Participant participant = new Participant(name, phone, email, batch_id);
                if (batchid != null && !batchid.isEmpty()) {
                    Batch batch = batchDAO.getBatch(Integer.parseInt(String.valueOf(batch_id)));
                    participant.setBatch_id(batch.getBatch_id());
                }
                participantDAO.addParticipant(participant);
            }
            resp.sendRedirect(req.getContextPath() + "/participant");
        }
        catch (SQLException e) {
            throw new ServletException("Database error in ParticipantServlet", e);
        }
    }

}
