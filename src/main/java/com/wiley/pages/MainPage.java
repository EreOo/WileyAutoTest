package com.wiley.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;

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
    @FindBy(id = "query")
    WebElement search;
    @FindBy(css = "input[type='submit']")
    WebElement search_button;
    @FindBy(className = "resource-institutions")
    WebElement button_institutions;

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

    //возвращает кол-во элементов в меню Check2a
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


    //кликаем по значку студенты и копируем url на который перешли check3a
    public String clickStudentAndUrl(){
        button_student.click();
        String url = webDriver.getCurrentUrl();
        return url;

    }

    //нажимаем на кнопку отправки email и получаем текст из алерта check7
    public String clickEmailButton(){
        email_button.click();
        return webDriver.switchTo().alert().getText();
    }

    //пишем адресс без @ и проверяем текст алерта check8
    public String setInvalidEmail(){
        email_adress.sendKeys("inavalidEmail.com");
        email_button.click();
        return webDriver.switchTo().alert().getText();
    }

    //вбиваем в поисковик данные и проверяем отображается ли на странице результат поиска check9
    public boolean isSearch(){
        search.clear();
        search.sendKeys("for dummies");
        search_button.click();
        return webDriver.findElement(By.id("search-results")).isDisplayed();

    }

    //ввбиваем в поисковик данные и кликаем на первую книгу check10
    public void searchAndClickBook(){
        search.clear();
        search.sendKeys("for dummies");
        search_button.click();
        webDriver.findElement(By.id("search-results")).findElement(By.tagName("a")).click();


    }

    //check 11 - 12 продолжаются там где остановился check 10
    //переходи на главную и кликаем по кнопке institutions
    public void toHomeAndtoInstitutions(){
       searchAndClickBook();
        webDriver.findElement(By.linkText("Home")).click();
        button_institutions.click();

      for(String tab : webDriver.getWindowHandles()){
          webDriver.switchTo().window(tab);
      }
        System.out.print(webDriver.getCurrentUrl());

    }




}
