package com.example.servlet;

import com.example.dao.BatchDAO;
import com.example.dao.BatchDAOImpl;
import com.example.model.Batch;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/batches")
public class BatchServlet extends HttpServlet{
    private final BatchDAO dao = new BatchDAOImpl();

    //doGet function
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.deleteBatch(id);
                resp.sendRedirect(req.getContextPath() + "/batches");
                return;
            }
            else if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Batch batch = dao.getBatch(id);
                req.setAttribute("batch", batch);
                req.getRequestDispatcher("/addBatch.jsp")
                        .forward(req, resp);
                return;
            }

            // default: list all Batches
            List<Batch> batches = dao.getAllBatches();
            req.setAttribute("listBatch", batches);
            req.getRequestDispatcher("/listBatches.jsp")
                    .forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Database error in BatchServlet", e);
        }
    }

    //doPost function
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            if ("update".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                dao.updateBatch(new Batch(id, name, description));
            } else {
                // covers both "insert" and (if missing) any other case
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                dao.addBatch(new Batch(name, description));
            }
            resp.sendRedirect(req.getContextPath() + "/batches");
        } catch (SQLException e) {
            throw new ServletException("Database error in BatchServlet", e);
        }
    }




}
