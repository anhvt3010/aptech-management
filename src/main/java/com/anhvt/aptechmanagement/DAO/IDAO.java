package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Course;

import java.util.ArrayList;

public interface IDAO<T> {
    int insert(T t);
    int update(T t);
    int remove(T t);
    ArrayList<T> findAll();
    T selectById(int id);

    ArrayList<T> selectByCondition(String condition);

}
