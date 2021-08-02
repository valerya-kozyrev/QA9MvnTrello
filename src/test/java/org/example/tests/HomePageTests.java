package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{

    @Test(groups = {"smoke", "regression", "system"})
    public  void verifyApplTest(){
        log4j.startTestCase("verifyApplTest");
        Assert.assertTrue(homePage.isCorrectPage());
        log4j.endTestCase("verifyApplTest");
    }
}
