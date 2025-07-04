<%@ page import="java.util.List, com.example.model.Batch" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>All Batches</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }
    table {
      border-collapse: collapse;
      width: 100%;
    }
    table, th, td {
      border: 1px solid #333;
    }
    th, td {
      padding: 10px;
      text-align: left;
    }
    th {
      background-color: #f2f2f2;
    }
    a {
      text-decoration: none;
      color: #007BFF;
    }
    a:hover {
      text-decoration: underline;
    }
    .add-batch {
      margin-top: 15px;
      display: inline-block;
    }
  </style>
</head>
<body>

  <h3>Batch List</h3>

  <table>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Description</th>
      <th>Action</th>
    </tr>
    <%
      List<Batch> list = (List<Batch>) request.getAttribute("listBatch");
      if (list != null) {
        for (Batch b : list) {
    %>
    <tr>
      <td><%= b.getId() %></td>
      <td><%= b.getName() %></td>
      <td><%= b.getDescription() %></td>
      <td>
        <a href="batches?action=edit&id=<%= b.getId() %>">Edit</a>
         |
        <a href="batches?action=delete&id=<%= b.getId() %>" onclick="return confirm('Delete this batch?');">
          Delete
        </a>
      </td>
    </tr>
    <%
        }
      } else {
    %>
    <tr>
      <td colspan="4">No batches found.</td>
    </tr>
    <%
      }
    %>
  </table>

  <p class="add-batch">
    <a href="addBatch.jsp">Add New Batch →</a>
  </p>

  <p>
    <a href="participants">← Back to Participants</a>
  </p>

</body>
</html>