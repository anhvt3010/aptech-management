package com.anhvt.aptechmanagement;

import com.anhvt.aptechmanagement.DAO.*;
import com.anhvt.aptechmanagement.Model.*;
import com.anhvt.aptechmanagement.Utils.GetAddressFromAPI;
import com.anhvt.aptechmanagement.Utils.Passwordefault;
import com.anhvt.aptechmanagement.Validation.EmailValidator;
import com.anhvt.aptechmanagement.Validation.InputTextValidator;
import com.anhvt.aptechmanagement.Validation.PhoneNumberValidator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;


class MainApplicationTest {
    @Test
     void testDAO(){
        System.out.println(ScoreDAO.getInstance().isScoreExists(1,1,1));
    }

}