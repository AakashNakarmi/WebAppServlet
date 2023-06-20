package com.example.webappservlet;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "SignupServlet", value = "/Signup-servlet")
public class SignupServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        BufferedReader credentialReader= Files.newBufferedReader(Paths.get("C:\\Users\\Aakash Nakarmi\\IdeaProjects\\WebAppServlet\\Data\\credentials.csv"));
        String line;
        List<Credential> credentialList=new ArrayList<>();

        while((line= credentialReader.readLine())!=null){
            String[] credentialData=line.split(",");
            credentialList.add(new Credential(credentialData[0], credentialData[1]));
        }

        credentialList.add(new Credential(username,password));

        BufferedWriter credentialWriter=Files.newBufferedWriter(Paths.get("C:\\Users\\Aakash Nakarmi\\IdeaProjects\\WebAppServlet\\Data\\credentials.csv"));
        for(Credential credential:credentialList){
            credentialWriter.write(credential.getUsername()+","+credential.getPassword());
            credentialWriter.newLine();
        }
        credentialWriter.flush();

    }

    public void destroy() {
    }
}