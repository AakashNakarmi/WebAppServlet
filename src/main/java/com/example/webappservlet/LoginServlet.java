package com.example.webappservlet;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.util.Optional;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "LoginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        BufferedReader credentialReader= Files.newBufferedReader(Paths.get("C:\\Users\\Aakash Nakarmi\\IdeaProjects\\demo1\\Data\\credentials.csv"));
        String line;
        List<Credential> credentialList=new ArrayList<>();

        while((line= credentialReader.readLine())!=null){
            String[] credentialData=line.split(",");
            credentialList.add(new Credential(credentialData[0], credentialData[1]));
        }

        Optional<Credential> matchedCredential=credentialList.stream().filter(credential ->credential.getUsername().equals(username)).findAny();
        PrintWriter writer=response.getWriter();

        if(matchedCredential.isPresent()){

            if(matchedCredential.get().getPassword().equals(password)){
                request.getSession(true).setAttribute("username",username);
                writer.println("<html><body><h1>Welcome to the World!"+username+"</h1></body></html>");
            }

            else{
                writer.println(("<html><body><h1>Incorrect Password</h1></body></html>"));
            }
        }
    }
    public void destroy() {
    }
}