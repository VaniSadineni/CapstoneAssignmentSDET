package com.capstone.tests;

import com.capstone.base.BaseTest;
import com.capstone.pages.LoginPage;
import com.capstone.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        String filePath = "src/test/java/com/capstone/data/TestData.xlsx";
        String sheetName = "LoginData";
        return ExcelUtils.readExcel(filePath, sheetName);
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedTitle, String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        if (expectedTitle.equals("Swag Labs")) {
            Assert.assertEquals(loginPage.getPageTitle(), expectedTitle);
        } else {
            Assert.assertEquals(loginPage.getErrorMessage(), expectedErrorMessage);
        }
    }
}
