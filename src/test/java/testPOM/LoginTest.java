package testPOM;

import base.TestUtil;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class LoginTest extends TestUtil {

    @Test
    public void successfulLogin(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage prPage = loginPage.login("standard_user", "secret_sauce");

    }

}
