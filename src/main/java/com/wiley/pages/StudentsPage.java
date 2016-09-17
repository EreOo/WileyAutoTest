package com.wiley.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        return menuItems;
    }

    //кликаем по кнопке домой ( и проверяем в тесте - точно ли мы на главной стр.) check6
    public String fromStudentsToMain(){
        webDriver.findElement(By.linkText("Home")).click();
        return  webDriver.getCurrentUrl();
    }

    //сравниваем сss по background-color -> цвета отличаются возвращаем false check5a
    public boolean isLikeStyle(){

          return webDriver.findElement(By.tagName("span")).getCssValue("background-color").
                equals(webDriver.findElement(By.className("autonavItem")).getCssValue("background-color"));

    }

    //попытка клика, если объект  not clikable -> ловим ошибку возвращаем false check5b
    public boolean isСlickable(){
        try {
            webDriver.findElement(By.className("autonavLevel1")).findElement(By.tagName("span")).click();
            return true;
        }
        catch (WebDriverException e){
            return false;
        }

    }


}
