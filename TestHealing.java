package com.healthproof.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestHealing {

    public static void main(String[] args) {

        // Optional: set chromedriver path if not set in system
        // System.setProperty("webdriver.chrome.driver",
        // "C:\\drivers\\chromedriver.exe");

        WebDriver originalDriver = new ChromeDriver();

        // maximize browser
        originalDriver.manage().window().maximize();

        // create healing driver
        HealingDriver driver =
                new HealingDriver(originalDriver);

        try {

            // open petstore site
            originalDriver.get(
                    "https://petstore.octoperf.com/"
            );

            System.out.println("Website opened");

            Thread.sleep(2000);


            // Click Sign In
            WebElement signIn =
                    driver.findElement(
                            "homepage_signin_link"
                    );

            signIn.click();

            System.out.println("Clicked Sign In");

            Thread.sleep(2000);


            // Enter Username
            WebElement username =
                    driver.findElement(
                            "signin_username"
                    );

            username.clear();

            username.sendKeys("j2ee");

            System.out.println("Entered Username");


            // Enter Password
            WebElement password =
                    driver.findElement(
                            "signin_password"
                    );

            password.clear();

            password.sendKeys("j2ee");

            System.out.println("Entered Password");


            // Click Login
            WebElement login =
                    driver.findElement(
                            "signin_login_button"
                    );

            login.click();

            System.out.println("Clicked Login");


            Thread.sleep(3000);


            // Click Fish Category
            WebElement fish =
                    driver.findElement(
                            "catalog_fish_link"
                    );

            fish.click();

            System.out.println("Clicked Fish");


            Thread.sleep(3000);


            System.out.println(
                    "Test completed successfully"
            );

        }

        catch (Exception e) {

            System.out.println(
                    "Test failed: " + e.getMessage()
            );

            e.printStackTrace();

        }

        finally {

            // close browser after 5 seconds
            try {

                Thread.sleep(5000);

            }

            catch (Exception ignored) {}

            originalDriver.quit();

            System.out.println("Browser closed");

        }

    }

}
