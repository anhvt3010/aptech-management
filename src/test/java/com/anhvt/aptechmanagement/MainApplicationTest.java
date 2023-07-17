package com.anhvt.aptechmanagement;

import com.anhvt.aptechmanagement.DAO.ClassDAO;
import com.anhvt.aptechmanagement.DAO.CourseDAO;
import com.anhvt.aptechmanagement.Model.Classes;
import com.anhvt.aptechmanagement.Model.Course;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;


class MainApplicationTest {
    @Test
    void testDAO(){
        System.out.println(CourseDAO.getIntance().findAll().size());
        ListIterator<Classes> iterator = ClassDAO.getIntance().findAll().listIterator();
        System.out.println("Các phần tử có trong list là: ");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}