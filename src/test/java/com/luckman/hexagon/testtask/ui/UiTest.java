package com.luckman.hexagon.testtask.ui;

import com.luckman.hexagon.testtask.ui.pages.FiltersPanel;
import com.luckman.hexagon.testtask.ui.pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;


public class UiTest {
    private WebDriver driver = new ChromeDriver();  // will be moved into the separate context with multiple UI test-classes

    @BeforeClass
    public void before() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void after() {
        driver.close();
    }

    @Test
    public void testUI() {
        MainPage mainPage = new MainPage(driver);
        FiltersPanel filtersPanel = mainPage.open()
                .goToFiltersMenu()
                .selectYear(2021);

        int amount3dMeshes = filtersPanel.getFilteredAmount("3D Realistic Meshes");
        int amount2dImagery = filtersPanel.getFilteredAmount("2D Aerial imagery");

        assertEquals(amount3dMeshes, 8, "Wrong number of 3D Realistic Meshes");
        assertEquals(amount2dImagery, 36, "Wrong number of 2D Aerial imagery");
    }

}
