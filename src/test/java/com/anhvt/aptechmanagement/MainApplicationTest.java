package com.anhvt.aptechmanagement;

import com.anhvt.aptechmanagement.DAO.ClassDAO;
import org.junit.jupiter.api.Test;


class MainApplicationTest {
    @Test
     void testDAO() {
        System.out.println(ClassDAO.getIntance().getClassWithLastestCreatedTime());
    }

}