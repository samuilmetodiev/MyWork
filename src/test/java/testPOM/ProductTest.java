package testPOM;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductPage;

import java.lang.annotation.ElementType;

public class ProductTest extends TestUtil {

    @Test
    public void addItemToTheCart(){
        LoginPage loginTest = new LoginPage(driver);
        ProductPage productPage = loginTest.login("standard_user", "secret_sauce");

        productPage.addItemToTheCart("bike-light");
        Assert.assertEquals(productPage.getItemsCart(), 1);



    }

    @Test
    public void addItemsToTheCart(){
        LoginPage loginTest = new LoginPage(driver);
        ProductPage productPage = loginTest.login("standard_user", "secret_sauce");

        SoftAssert softAssert = new SoftAssert();


        productPage.addItemToTheCart("bolt-t-shirt");
        softAssert.assertEquals(productPage.getItemsCart(), 1, "Element wan no added in to cart");

        productPage.addItemToTheCart("bike-light");
        softAssert.assertEquals(productPage.getItemsCart(), 2, "Element wan no added in to cart");

        softAssert.assertAll();


    }

}
