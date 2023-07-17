package com.anhvt.aptechmanagement.DAO;

import com.anhvt.aptechmanagement.Model.Schedule;

import java.util.ArrayList;

public class ScheduleDAO implements IDAO<Schedule>{
    @Override
    public int insert(Schedule schedule) {
        return 0;
    }

    @Override
    public int update(Schedule schedule) {
        return 0;
    }

    @Override
    public int remove(Schedule schedule) {
        return 0;
    }

    @Override
    public ArrayList<Schedule> findAll() {
        return null;
    }

    @Override
    public Schedule selectById(Schedule schedule) {
        return null;
    }

    @Override
    public ArrayList<Schedule> selectByCondition(String condition) {
        return null;
    }
}
