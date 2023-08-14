package com.anhvt.aptechmanagement;

import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.DAO.SroDAO;
import org.junit.jupiter.api.Test;


class MainApplicationTest {
    @Test
     void testDAO() {
        System.out.println(SroDAO.getInstance().findAll().get(1).getCode());
    }

}