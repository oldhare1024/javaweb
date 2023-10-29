package com.example.javaweb.dao.impl;

import com.example.javaweb.dao.FruitDAO;
import com.example.javaweb.dao.base.BaseDAO;
import com.example.javaweb.pojo.Fruit;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {
    @Override
    public List<Fruit> getFruitList(String keyword,Integer pageNo) {
        return super.executeQuery("SELECT * FROM fruitdb  where fname like ? or remark like ? LIMIT ?,5;","%"+keyword+"%","%"+keyword+"%",5*(pageNo-1));
    }
    public Fruit getFruitByFid(Integer fid){
        return super.load("select * from fruitdb where fid = ?",fid);
    }

    @Override
    public void addFruit(Fruit fruit) {
        String sql = "insert into fruitdb values(0,?,?,?,?)";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark()) ;
        //insert语句返回的是自增列的值，而不是影响行数
        //System.out.println(count);
    }

    @Override
    public void updateFruit(Fruit fruit) {
        String sql = "update fruitdb set fname = ? ,price = ?,fcount = ?,remark = ? where fid = ? " ;
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        return super.load("select * from fruitdb where fname like ? ",fname);
    }

    @Override
    public void delFruit(Integer fid) {
        String sql = "delete from fruitdb where fid = ? " ;
        super.executeUpdate(sql,fid);
    }
    public int getFruitCount(String keyword){
        return ((Long)super.executeComplexQuery("select count(*) from fruitdb where fname like ? or remark like ?","%"+keyword+"%","%"+keyword+"%")[0]).intValue();
    }
}