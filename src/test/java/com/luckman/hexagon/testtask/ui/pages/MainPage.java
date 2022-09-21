package com.luckman.hexagon.testtask.ui.pages;

import org.openqa.selenium.WebDriver;

public class MainPage extends PageBase {

    private String filterButton = "//span[@data-test-id='filter-panel-nav-button']";
    private String acceptCookies = "//*[@id='onetrust-accept-btn-handler']";

    private FiltersPanel filtersPanel;

    public MainPage(WebDriver driver) {
        super("", driver);
        filtersPanel = new FiltersPanel("//div[@data-test-id='filter-panel']", driver);
    }

    public MainPage open() {
        driver.get("https://hxdr.app/catalog");
        waitAndClick(acceptCookies);
        return this;
    }

    public FiltersPanel goToFiltersMenu() {
        waitAndClick(filterButton);
        waitForVisible(filtersPanel.getBasePath());
        return filtersPanel;
    }


}
