package com.anhvt.aptechmanagement.DAO;

import java.util.ArrayList;

public interface IDAO<T> {
    int insert(T t);
    int update(T t);
    int remove(T t);
    ArrayList<T> findAll();
    T selectById(T t);
    ArrayList<T> selectByCondition(String condition);

}
