/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/11.0.6
 * Generated at: 2025-06-20 05:45:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;
import java.util.List;
import com.example.model.Batch;

public final class listBatches_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(3);
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("com.example.model.Batch");
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("  <title>All Batches</title>\n");
      out.write("  <style>\n");
      out.write("    body {\n");
      out.write("      font-family: Arial, sans-serif;\n");
      out.write("      margin: 20px;\n");
      out.write("    }\n");
      out.write("    table {\n");
      out.write("      border-collapse: collapse;\n");
      out.write("      width: 100%;\n");
      out.write("    }\n");
      out.write("    table, th, td {\n");
      out.write("      border: 1px solid #333;\n");
      out.write("    }\n");
      out.write("    th, td {\n");
      out.write("      padding: 10px;\n");
      out.write("      text-align: left;\n");
      out.write("    }\n");
      out.write("    th {\n");
      out.write("      background-color: #f2f2f2;\n");
      out.write("    }\n");
      out.write("    a {\n");
      out.write("      text-decoration: none;\n");
      out.write("      color: #007BFF;\n");
      out.write("    }\n");
      out.write("    a:hover {\n");
      out.write("      text-decoration: underline;\n");
      out.write("    }\n");
      out.write("    .add-batch {\n");
      out.write("      margin-top: 15px;\n");
      out.write("      display: inline-block;\n");
      out.write("    }\n");
      out.write("  </style>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\n");
      out.write("  <h3>Batch List</h3>\n");
      out.write("\n");
      out.write("  <table>\n");
      out.write("    <tr>\n");
      out.write("      <th>ID</th>\n");
      out.write("      <th>Name</th>\n");
      out.write("      <th>Description</th>\n");
      out.write("      <th>Action</th>\n");
      out.write("    </tr>\n");
      out.write("    ");

      List<Batch> list = (List<Batch>) request.getAttribute("listBatch");
      if (list != null) {
        for (Batch b : list) {
    
      out.write("\n");
      out.write("    <tr>\n");
      out.write("      <td>");
      out.print( b.getId() );
      out.write("</td>\n");
      out.write("      <td>");
      out.print( b.getName() );
      out.write("</td>\n");
      out.write("      <td>");
      out.print( b.getDescription() );
      out.write("</td>\n");
      out.write("      <td>\n");
      out.write("        <a href=\"batches?action=edit&id=");
      out.print( b.getId() );
      out.write("\">Edit</a>\n");
      out.write("         |\n");
      out.write("        <a href=\"batches?action=delete&id=");
      out.print( b.getId() );
      out.write("\" onclick=\"return confirm('Delete this batch?');\">\n");
      out.write("          Delete\n");
      out.write("        </a>\n");
      out.write("      </td>\n");
      out.write("    </tr>\n");
      out.write("    ");

        }
      } else {
    
      out.write("\n");
      out.write("    <tr>\n");
      out.write("      <td colspan=\"4\">No batches found.</td>\n");
      out.write("    </tr>\n");
      out.write("    ");

      }
    
      out.write("\n");
      out.write("  </table>\n");
      out.write("\n");
      out.write("  <p class=\"add-batch\">\n");
      out.write("    <a href=\"addBatch.jsp\">Add New Batch →</a>\n");
      out.write("  </p>\n");
      out.write("\n");
      out.write("  <p>\n");
      out.write("    <a href=\"participants\">← Back to Participants</a>\n");
      out.write("  </p>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
