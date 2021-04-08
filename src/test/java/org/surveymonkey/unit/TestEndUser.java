package org.surveymonkey.unit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.surveymonkey.models.*;

import java.util.ArrayList;
import java.util.List;

public class TestEndUser {
    EndUser user;
    Survey survey;
    Survey survey2;

    public TestEndUser(){

    }

    @Before
    public void buildUp() {
        user = new EndUser();
        survey = new Survey();
        survey2 = new Survey();
    }

    @After
    public void tearDown() {
        user = null;
        survey = null;
        survey2 = null;
    }


}
