package com.nocommerce.demo.testsuite;

import com.nocommerce.demo.customelisteners.CustomListeners;
import com.nocommerce.demo.pages.BuildYourOwnComputerPage;
import com.nocommerce.demo.pages.ComputersPage;
import com.nocommerce.demo.pages.DesktopPage;
import com.nocommerce.demo.pages.HomePage;
import com.nocommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import resources.testdata.TestData;

@Listeners(CustomListeners.class)
public class ComputerTestPage extends BaseTest {

    HomePage homePage;
    ComputersPage computersPage;
    DesktopPage desktopPage;
    BuildYourOwnComputerPage buildYourOwnComputerPage;

    @BeforeMethod (alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computersPage = new ComputersPage();
        desktopPage = new DesktopPage();
        buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldNavigateToComputerPageSuccessfully(){
        homePage.clickOnComputersMenu();
        String expectedMessage = "Computers";
        String actualMessage = computersPage.getWelcomeText();
        Assert.assertEquals(actualMessage, expectedMessage, "Computers page not displayed");
    }

   @Test(groups = {"smoke", "regression"})
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        homePage.clickOnComputersMenu();
        computersPage.clickOnDesktopsLink();
        String expectedMessage = "Desktops";
        String actualMessage = desktopPage.getWelcomeText();
        Assert.assertEquals(actualMessage, expectedMessage, "Desktops page not displayed");
    }

   @Test(dataProvider = "builtYourOwnComputer", dataProviderClass = TestData.class, groups = {"regression"})
    public void verifyThatUserShouldBuildYouOwnComputerAndAddThemToCartSuccessfully(String processor, String ram, String hdd, String os, String software){
        homePage.clickOnComputersMenu();
        computersPage.clickOnDesktopsLink();
        desktopPage.clickOnBuildYourOwnComputerLink();
        buildYourOwnComputerPage.optionsToBuiltYourOwnComputer(processor, ram, hdd, os, software);
        buildYourOwnComputerPage.clickOnAddToCart();
        String expectedMessage = "The product has been added to your shopping cart";
        String actualMessage = buildYourOwnComputerPage.getAddToCartSuccessfulMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Product is not added successfully");
    }
}
