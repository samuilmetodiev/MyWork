package com.saucedemo;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FirstSeleniumTest extends TestUtil {


    @DataProvider(name = "correctUsers")
    public Object[][] readUsersFromCsv() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctUsers.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object [][] csvDataObj = new Object[csvData.size()][2];

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObj[i] = csvData.get(i);
            }
            return csvDataObj;
        } catch (IOException e) {
            System.out.println("Not Possible to find the file!");
            return null;
        } catch (CsvException e){
            return null;
        }
    }

    @Test(dataProvider = "correctUsers")
    public void SuccessfulLoginSauceDemo(String username, String password) throws InterruptedException {

        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//input[@name='login-button']"));
        loginBtn.click();

        //Todo validation for successful login
        WebElement productsPageTitle = driver.findElement(By.className("title"));
        Assert.assertTrue(productsPageTitle.isDisplayed());

    }

    @DataProvider(name = "wrongUsers")
    public Object[][] getUsers(){
        return new Object[][]{
                {"WrongUsername", "secret_sauce"},
                {"standard_user", "WrongPassword"},
                {"WrongName", "WrongPassword"}
        };
    }

    @Test(dataProvider = "wrongUsers")
    public void unsuccessfulLogin(String username, String password){
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//input[@name='login-button']"));
        loginBtn.click();

        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.isDisplayed());

    }




}
