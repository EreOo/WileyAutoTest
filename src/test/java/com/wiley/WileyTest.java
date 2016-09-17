package com.wiley;

import com.wiley.pages.MainPage;
import com.wiley.pages.StudentsPage;
import org.junit.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Vladimir on 17.09.16.
 */
public class WileyTest  {
    WebDriver chromeWebDriver;
    MainPage mainPage;
    StudentsPage studentsPage;

    final String[] correctCheck1 = {"Home","Subjects","About Wiley","Contact Us","Help"};
    final String[] correctCheck2b = {"Students","Authors","Instructors","Librarians","Societies","Conferences","Booksellers","Corporations","Institutions"};
    final String correctCheck3 = "http://eu.wiley.com/WileyCDA/Section/id-404702.html";
    //после students добавлено 2 li которых нет в ТЗ
    final String[] correctCheck4b ={"Authors","Librarians","Booksellers","Instructors","Students","Purchase Your WileyPLUS Registration Code","Getting Started with WileyPLUS and Blackboard",",”Government Employees”,","Societies","Corporate Partners"};


    @Before
    public  void setUpWebDriver(){
        chromeWebDriver = new ChromeDriver();
        mainPage = new MainPage(chromeWebDriver);
        chromeWebDriver.get("http://www.wiley.com/WileyCDA/");
    }

    @After
    public void tearDown(){
        if(chromeWebDriver != null){
            chromeWebDriver.quit();
        }
    }

    @Test
    public void check1(){

        Assert.assertArrayEquals(correctCheck1,mainPage.getTextNavigator_Menu());

    }

    @Test
    public void check2a(){

      Assert.assertTrue(mainPage.getSizeSubHeader() == 9);

    }

    @Test
    public void check2b(){

        Assert.assertArrayEquals(correctCheck2b,mainPage.getTextSubHeader());
    }

    @Test
    public void check3(){

        Assert.assertTrue(correctCheck3.equals(mainPage.clickStudentAndUrl()));
    }

    @Test
    public void check3b(){
        studentsPage = new StudentsPage(chromeWebDriver);
        chromeWebDriver.get("http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        Assert.assertTrue(studentsPage.isHeader() == true);
    }


    //check4 не пройдет - по ТЗ 8 элементов li, у меня только 7
    @Test
    public void check4(){
        studentsPage = new StudentsPage(chromeWebDriver);
        chromeWebDriver.get("http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        Assert.assertTrue(studentsPage.isResourcesFor() == true);
    }

    //тест не пройдет - нет ”Government Employees” на сайте
    @Test
    public void check4b(){
        studentsPage = new StudentsPage(chromeWebDriver);
        chromeWebDriver.get("http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        Assert.assertArrayEquals(correctCheck4b, studentsPage.getTextResourcesFor());
    }


    @Test
    public void check6(){
        studentsPage = new StudentsPage(chromeWebDriver);
        chromeWebDriver.get("http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        Assert.assertTrue(studentsPage.fromStudentsToMain().equals("http://eu.wiley.com/WileyCDA/"));
    }

    @Test
    public void check7(){
        Assert.assertTrue(mainPage.clickEmailButton().equals("Please enter email address"));
    }

    @Test
    public void check8(){
        Assert.assertTrue(mainPage.setInvalidEmail().equals("Invalid email address."));
    }

    @Test



    }


