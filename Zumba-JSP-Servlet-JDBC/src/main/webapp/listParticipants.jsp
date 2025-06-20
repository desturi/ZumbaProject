<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Participant" %>
<!DOCTYPE html>
<html>
<head>
    <title>Participant List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f5f5f5;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f8f9fa;
            color: #333;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .action-links a {
            margin-right: 10px;
            text-decoration: none;
            color: #007bff;
        }
        .action-links a:hover {
            text-decoration: underline;
        }
        .add-button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .add-button:hover {
            background-color: #218838;
        }
        .category-tag {
            display: inline-block;
            padding: 4px 8px;
            background-color: #e9ecef;
            border-radius: 4px;
            font-size: 0.9em;
            color: #495057;
        }
        .no-category {
            color: #6c757d;
            font-style: italic;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Participant List</h1>
        <a href="participants?action=add" class="add-button">Add New Participant</a>
        <table>
            <tr>
                <th>Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Batch</th>
                <th>Actions</th>
            </tr>
            <%
                List<Participant> participants = (List<Participant>) request.getAttribute("listParticipant");
                if (participants != null) {
                    for (Participant participant : participants) {
            %>
                <tr>
                    <td><%= participant.getName() %></td>
                    <td><%= participant.getPhone() %></td>
                    <td><%= participant.getEmail() %></td>
                    <td><%= participant.getBatch() %></td>
                    <td>
                        <% if (participant.getBatch() != null) { %>
                            <span class="batch-tag"><%= participant.getBatch().getName() %></span>
                        <% } else { %>
                            <span class="no-batch">No batch</span>
                        <% } %>
                    </td>
                    <td class="action-links">
                        <a href="participants?action=edit&id=<%= participant.getId() %>">Edit</a>
                        <a href="participants?action=delete&id=<%= participant.getId() %>" onclick="return confirm('Are you sure you want to delete this participant?')">Delete</a>
                    </td>
                </tr>
            <%
                    }
                }
            %>
        </table>
    </div>

    <p>
        <a href="batches">← Back to Batches</a>
      </p>
</body>
</html>