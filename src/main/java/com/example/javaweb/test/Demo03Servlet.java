package com.example.javaweb;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Demo03Sservlet", value = "/Demo03Sservlet")
public class Demo03Servlet extends HttpServlet {
    protected void service(HttpServletRequest req,HttpServletResponse resp){
        HttpSession session=req.getSession();
        System.out.println("SessionID为:"+session.getId());
    }
}