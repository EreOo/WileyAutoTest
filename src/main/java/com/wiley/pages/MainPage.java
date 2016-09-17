package com.wiley.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Vladimir on 17.09.16.
 */
public class MainPage {
    private WebDriver webDriver;

    @FindBy(id = "links-site")
    WebElement navigation_menu;
    @FindBy(id = "homepage-links")
    WebElement sub_header_menu;
    @FindBy(className = "resource-students")
    WebElement button_student;
    @FindBy(id = "EmailAddress")
    WebElement email_adress;
    @FindBy(id = "id31")
    WebElement email_button;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    //возвращает массив из названий кнопок меню Check1
    public String[] getTextNavigator_Menu(){
       if(navigation_menu.isDisplayed() == true){
        String menuAll = navigation_menu.getText();
        String[] menuItems = menuAll.split("\n");
        return  menuItems;}
        else
        return null;
    }

    //возвращает колво элементов в меню Check2a
    public Integer getSizeSubHeader(){
        int countLi = sub_header_menu.findElements(By.tagName("li")).size();
        return countLi;
    }

    //возвращает массив из названий кнопок меню Check2b
    public String[] getTextSubHeader(){
        String subMenuAll = sub_header_menu.getText();
        String[] subMenuItems = subMenuAll.split("\n");
        return subMenuItems;
    }


    //кликаем по значку студенты и копируем url на который перешли
    public String clickStudentAndUrl(){
        button_student.click();
        String url = webDriver.getCurrentUrl();
        return url;

    }

    //нажимаем на кнопку отправки email и проверяем текст в окошке алерт
    public String clickEmailButton(){
        email_button.click();
        return webDriver.switchTo().alert().getText();
    }

    //пишем адресс без @ и проверяем тест алерта
    public String setInvalidEmail(){
        email_adress.sendKeys("inavalidEmail.com");
        email_button.click();
        return webDriver.switchTo().alert().getText();
    }


}
