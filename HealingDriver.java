package com.healthproof.qa;

import org.openqa.selenium.*;

public class HealingDriver {

    WebDriver driver;

    public HealingDriver(WebDriver driver){

        this.driver=driver;

    }

    public WebElement findElement(String objectName){

        LocatorData data=
                ExcelUtil.getLocator(objectName);

        try{

            System.out.println(

                    "Using locator: "

                    +data.type+"="+data.value);

            return driver.findElement(

                    getBy(data));

        }

        catch(Exception e){

            System.out.println(

                    "Locator failed. Healing...");

            String dom=

                    driver.findElement(By.tagName("body"))

                            .getAttribute("outerHTML");

            String healed=

                    GeminiHealing.heal(

                            dom,

                            objectName,

                            data.type,

                            data.value);

            ExcelUtil.writeHealedLocator(

                    data,

                    healed);

            data.value=healed;

            return driver.findElement(

                    getBy(data));

        }

    }

    By getBy(LocatorData data){

        switch(data.type){

            case "id":

                return By.id(data.value);

            case "name":

                return By.name(data.value);

            case "linkText":

                return By.linkText(data.value);

            case "partialLinkText":

                return By.partialLinkText(data.value);

            case "cssSelector":

                return By.cssSelector(data.value);

            default:

                return By.xpath(data.value);

        }

    }

}
