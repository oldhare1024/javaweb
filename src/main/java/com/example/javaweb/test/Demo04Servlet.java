package com.example.javaweb;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Demo04Servlet", value = "/Demo04Servlet")
public class Demo04Servlet extends HttpServlet {
    protected void service(HttpServletRequest req,HttpServletResponse resp){
        req.getSession().setAttribute("uname","linux");
    }
}