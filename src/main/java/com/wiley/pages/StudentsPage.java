package com.wiley.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vladimir on 17.09.16.
 */
public class StudentsPage {
    private WebDriver webDriver;

    @FindBy(id = "topnav")
    WebElement header;
    @FindBy(className = "autonavLevel1")
    WebElement resources_for;
    @FindBy(className = "autonavLevel2")
    WebElement sub_resources_for;
    @FindBy(css = "li.active.autonavItem.parent")
    WebElement student_button;


    public StudentsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    //отображается ли хедер check3b
    public boolean isHeader(){
       return header.isDisplayed();
    }

    //отображается ли 8 элементов бокового меню check4
    public boolean isResourcesFor(){
       //тест не пройдет, по ТЗ в боковом меню 8 - у меня только 7
        //в if вычитаем из всех li ( их 9 вместе с открытыми доп. вкладками Students(2)) - доп. вкладки Students(2)
       if(resources_for.findElements(By.tagName("li")).size()
               - sub_resources_for.findElements(By.tagName("li")).size() ==  8){
        return resources_for.isDisplayed();}
        else {
           return false;}
    }

    public String[] getTextResourcesFor(){

        String menuAll = resources_for.getText();
        String[] menuItems = menuAll.split("\n");

        System.out.print(menuAll);
        return menuItems;
    }

    //кликаем по кнопке домой ( и проверяем в тесте точно ли мы на главной стр.) check6
    public String fromStudentsToMain(){
        webDriver.findElement(By.linkText("Home")).click();
        return  webDriver.getCurrentUrl();
    }

    public void isDifferentStyle(){
        System.out.println("Present");
          System.out.print(student_button.getCssValue("display"));
        System.out.print(student_button.getAttribute("style"));

    }


}
