package com.example.javaweb;

import org.apache.commons.dbutils.QueryRunner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Arrays;

public class AddServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String fname = req.getParameter("fname");
        String priceStr = req.getParameter("fprice");
        Integer fprice = Integer.parseInt(priceStr);
        String fcoutStr = req.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcoutStr);
        String remark = req.getParameter("remark");
        String []sort = req.getParameterValues("sort");
        String sort1 = Arrays.toString(sort);
        sort1=sort1.substring(1, sort1.length()-1);

        QueryRunner qr = new QueryRunner(druit.getDataSource());
        String sql = "insert into fruit(fname,fprice,fcount,remark,sort)" + " values(?,?,?,?,?)";

        int count;
        try {
            count = qr.update(sql, fname, fprice, fcount, remark, sort1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(count);
    }
}
