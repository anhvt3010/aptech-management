package com.anhvt.aptechmanagement;

import com.anhvt.aptechmanagement.DAO.ScoreDAO;
import org.junit.jupiter.api.Test;


class MainApplicationTest {
    @Test
     void testDAO(){
        System.out.println(ScoreDAO.getInstance()
                .selectByIdStudentAndIdSubjectTH(23, 1).toString());
    }

}