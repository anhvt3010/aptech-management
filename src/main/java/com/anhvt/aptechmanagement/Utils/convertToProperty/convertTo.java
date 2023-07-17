package com.anhvt.aptechmanagement.Utils.convertToProperty;


import com.anhvt.aptechmanagement.Model.Course;
import com.anhvt.aptechmanagement.Property.CourseProperty;

import java.util.ArrayList;
import java.util.ListIterator;

public class convertTo {
    public static CourseProperty convertToCourseProperty(Course course){
        CourseProperty cp = new CourseProperty();
        cp.setId(course.getId());
        cp.setName(course.getName());
        cp.setSemester(course.getSemester());
        switch (course.getStatus()){
            case 1:
                cp.setStatus("LOCK");
                break;
            case 2:
                cp.setStatus("UNLOCK");
                break;
        }
        return cp;
    }
    public static ArrayList<CourseProperty> convertToListCourseProperty(ArrayList<Course> listCourse){
        ArrayList<CourseProperty> cousreProperties = new ArrayList<>();
        ListIterator<Course> iterator = listCourse.listIterator();
        while (iterator.hasNext()) {
            cousreProperties.add(convertToCourseProperty(iterator.next()));
        }
        return cousreProperties;
    }
}
