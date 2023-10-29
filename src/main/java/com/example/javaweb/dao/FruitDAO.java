package com.example.javaweb.dao;

import com.example.javaweb.pojo.Fruit;

import java.util.List;

public interface FruitDAO {
    //获取指定页码上的库存列表信息/每页5条
    List<Fruit> getFruitList(String keyword,Integer pageNo);

    //新增库存
    void addFruit(Fruit fruit);

    //修改库存
    void updateFruit(Fruit fruit);

    //根据名称查询特定库存
    Fruit getFruitByFname(String fname);
    Fruit getFruitByFid(Integer fid);
    //删除特定库存记录
    void delFruit(Integer fid);
    int getFruitCount(String keyword);
}
