package com.luckman.hexagon.testtask.ui.pages;

import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

public class FiltersPanel extends PageBase {
    private String currentYear = "All years";
    private String yearsFilterPattern = "//button//div[@title='%s']";
    private String yearPattern = "//li[text()='%s']";
    private String amountPattern = "//h6[text()='%s']/../label";
    private String spinner = "//title[@text()='Loading...']";

    FiltersPanel(String basePath, WebDriver driver) {
        super(basePath, driver);
    }

    public FiltersPanel selectYear(int year) {
        waitAndClick(format(yearsFilterPattern, currentYear));
        currentYear = Integer.toString(year);
        waitAndClick(format(yearPattern, year));
        waitNonVisible(spinner);
        return this;
    }

    public int getFilteredAmount(String type) {
        String text = extractText(format(amountPattern, type));
        return Integer.parseInt(text
                .replace("(", "")
                .replace(")", "")
                .trim());
    }
}
