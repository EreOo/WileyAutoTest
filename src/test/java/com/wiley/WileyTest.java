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
    //после students добавлено 2 li которых нет в ТЗ ( но они не влияют на смысл теста)
    final String[] correctCheck4b ={"Authors","Librarians","Booksellers","Instructors","Students","Purchase Your WileyPLUS Registration Code","Getting Started with WileyPLUS and Blackboard",",”Government Employees”,","Societies","Corporate Partners"};
    final String correctCheck11 = "https://edservices.wiley.com/";


    @Before
    public  void setUpWebDriver(){
        chromeWebDriver = new ChromeDriver();
        mainPage = new MainPage(chromeWebDriver);
        chromeWebDriver.get("http://www.wiley.com/WileyCDA/");
        correctCheck4b[3] = "dsdsd";
    }

    @After
    public void tearDown(){
        if(chromeWebDriver != null){
            chromeWebDriver.quit();
        }
    }



//    Check the following links displayed in top navigation menu: Home, Subjects, About Wiley, Contact Us, Help
    @Test
    public void check1(){
        Assert.assertArrayEquals(correctCheck1,mainPage.getTextNavigator_Menu());
    }


//    There are 9 items under resources sub-header
    @Test
    public void check2a(){
      Assert.assertTrue(mainPage.getSizeSubHeader() == 9);
    }

//  Titles are “Students”, “Authors”, “Instructors”, “Librarians”,
// “Societies”, “Conferences”, “Booksellers”, “Corporations”, “Institutions”
    @Test
    public void check2b(){
        Assert.assertArrayEquals(correctCheck2b,mainPage.getTextSubHeader());
    }

//    Check that http://www.wiley.com/WileyCDA/Section/id-404702.html url is opened
    @Test
    public void check3(){
        Assert.assertTrue(correctCheck3.equals(mainPage.clickStudentAndUrl()));
    }

//    Check that “Students” header is displayed
    @Test
    public void check3b(){
        studentsPage = new StudentsPage(chromeWebDriver);
        chromeWebDriver.get("http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        Assert.assertTrue(studentsPage.isHeader() == true);
    }

//  8 items are displayed in the menu
    //check4 не пройдет - по ТЗ 8 элементов li, у меня только 7 на сайте
    @Test
    public void check4(){
        studentsPage = new StudentsPage(chromeWebDriver);
        chromeWebDriver.get("http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        Assert.assertTrue(studentsPage.isResourcesFor() == true);
    }


//    Items are “Authorts”, “Librarians”, “Booksellers”, “Instructors”, “Students” ,”Government Employees”, “Societies”, “Corporate Partners”
    //тест не пройдет - нет ”Government Employees” на сайте
    @Test
    public void check4b(){
        studentsPage = new StudentsPage(chromeWebDriver);
        chromeWebDriver.get("http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        Assert.assertArrayEquals(correctCheck4b, studentsPage.getTextResourcesFor());
    }


//  “Students” item has different style
    @Test
    public void check5a(){
        studentsPage = new StudentsPage(chromeWebDriver);
        chromeWebDriver.get("http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        Assert.assertTrue(studentsPage.isLikeStyle() == false);
    }

//    “Students” item is not clickable
    @Test
    public void check5b(){
        studentsPage = new StudentsPage(chromeWebDriver);
        chromeWebDriver.get("http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        Assert.assertTrue(studentsPage.isСlickable() != false);
    }


//    Click “Home” link at the top navigation menu
//    student_page -> main_page (check url)
    @Test
    public void check6(){
        studentsPage = new StudentsPage(chromeWebDriver);
        chromeWebDriver.get("http://eu.wiley.com/WileyCDA/Section/id-404702.html");
        Assert.assertTrue(studentsPage.fromStudentsToMain().equals("http://eu.wiley.com/WileyCDA/"));
    }

//    Find “Sign up to receive Wiley updates” line and input field next to it. Do not enter
//    anything and click arrow button. Check that alert appeared. Check that alert text is “Please enter email address”
    @Test
    public void check7(){
        Assert.assertTrue(mainPage.clickEmailButton().equals("Please enter email address"));
    }

//    Enter invalid email (for example without @). Check that alert text is “Invalid email address.”
    @Test
    public void check8(){
        Assert.assertTrue(mainPage.setInvalidEmail().equals("Invalid email address."));
    }

//  Find search input in the top of the page. Enter “for dummies” to the input field and press
//    search icon next to the input field.  Check that list of items appeared
    @Test
    public void check9(){
        Assert.assertTrue(mainPage.isSearch());
    }

//    Click random item link (link with book title). Check that page with header equal to the title you clicked is displayed
    @Test
    public void check10(){
        mainPage.searchAndClickBook();
        Assert.assertTrue(chromeWebDriver.findElement(By.tagName("h1")).getText().equals("Self-sufficiency For Dummies Collection - Growing Your Own Fruit & Veg For Dummies/Keeping Chickens For Dummies UK Edition"));
    }

//    11. Click “Home” link at the top navigation menu
//    12. Click “Institutions” icon under Resources sub-header. Check http://wileyedsolutions.com/ is opened in new window (or tab)
    //в ТЗ указан url http://wileyedsolutions.com/
    // но по этой ссылке переходит на https://edservices.wiley.com/ по этому в тесте прописан второй url
    @Test
    public void check11_12(){
        mainPage.toHomeAndtoInstitutions();
        Assert.assertTrue(chromeWebDriver.getCurrentUrl().equals(correctCheck11));
    }

    }


