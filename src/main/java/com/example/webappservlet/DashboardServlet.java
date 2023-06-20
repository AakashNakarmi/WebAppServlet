package com.example.webappservlet;


import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "DashboardServlet", value = "/Dashboard-servlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println("<html><body><h1>Welcome "+request.getSession(false).getAttribute("username")+"</h1></body></html>");
    }

    public void destroy() {
    }
}