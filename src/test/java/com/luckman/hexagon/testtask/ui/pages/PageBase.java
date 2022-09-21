package com.luckman.hexagon.testtask.ui.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class PageBase {
    private final Duration explicitDuration = Duration.ofSeconds(60);

    @Getter
    private String basePath;

    protected WebDriver driver;

    PageBase(String basePath, WebDriver driver) {
        this.basePath = basePath;
        this.driver = driver;
    }

    protected void waitAndClick(String xpath) {
        new WebDriverWait(driver, explicitDuration)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(basePath + xpath)))
                .click();
    }

    protected void waitForVisible(String xpath) {
        new WebDriverWait(driver, explicitDuration)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(basePath + xpath)));
    }

    protected void waitNonVisible(String xpath) {
        new WebDriverWait(driver, explicitDuration)
                .until(ExpectedConditions.not(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath(basePath + xpath))
                ));
    }

    protected String extractText(String xpath) {
        return driver.findElement(By.xpath(basePath + xpath)).getText();
    }

}
