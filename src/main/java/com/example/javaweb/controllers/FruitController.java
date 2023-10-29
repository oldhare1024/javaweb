package com.example.javaweb.controllers;

import com.example.javaweb.dao.FruitDAO;
import com.example.javaweb.dao.impl.FruitDAOImpl;
import com.example.javaweb.pojo.Fruit;
import com.example.javaweb.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FruitController {
    private FruitDAO fruitDao=new FruitDAOImpl();
    private String index(String oper,String keyword,Integer pageNo,HttpServletRequest request) {
        HttpSession session=request.getSession();
        if(pageNo==null){
            pageNo=1;
        }
        if(StringUtil.isNotEmpty(oper)&&"search".equals(oper)){//说明是查询请求
            pageNo=1;
            if(StringUtil.isEmpty(keyword)){
                keyword="";
            }
            session.setAttribute("keyword",keyword);
        }else {
            Object keywordObj=session.getAttribute("keyword");
            if(keywordObj!=null){
                keyword=(String)keywordObj;
            }else{
                keyword="";
            }
        }
        session.setAttribute("pageNo",pageNo);
        FruitDAO fruitDAO =new FruitDAOImpl();
        List<Fruit> fruitList=fruitDAO.getFruitList(keyword,pageNo);
        session.setAttribute("fruitList",fruitList);
        int fruitCount= fruitDAO.getFruitCount(keyword);
        int pageCount=(fruitCount+5-1)/5;
        session.setAttribute("pageCount",pageCount);
        return "index";
    }
    private String add(String fname,Integer price,Integer fcount,String remark) {
        fruitDao.addFruit(new Fruit(0,fname,price,fcount,remark));
        return "redirect:fruit.do";
    }
    private String del(Integer fid) {
        if(fid!=null){
            fruitDao.delFruit(fid);
            return "redirect:fruit.do";
        }
        return null;
    }
    private String edit(Integer fid,HttpServletRequest request) {
        if(fid!=null){
            Fruit fruit=fruitDao.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
            return "edit";
        }
        return null;
    }
    private String update(Integer fid,String fname,Integer price,Integer fcount,String remark) {
        fruitDao.updateFruit(new Fruit(fid,fname,price,fcount,remark));
        return "redirect:fruit.do";
    }
}
