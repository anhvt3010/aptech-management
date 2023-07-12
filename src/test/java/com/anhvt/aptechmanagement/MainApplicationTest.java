package com.anhvt.aptechmanagement;

import com.anhvt.aptechmanagement.DAO.CourseDAO;
import org.junit.jupiter.api.Test;


class MainApplicationTest {
    @Test
    void testDAO(){
        System.out.println(CourseDAO.getIntance().findAll().size());
    }
}