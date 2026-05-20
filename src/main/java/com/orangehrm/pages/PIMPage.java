package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.interfaces.IPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates:
 *   - TABLE handling (employee list grid)
 *   - PAGINATION (page-number buttons + Next/Prev)
 */
public class PIMPage extends BasePage implements IPage {

    private final By searchEmployeeName =
            By.cssSelector("input[placeholder='Type for hints...']");

    private final By searchButton =
            By.xpath("//button[normalize-space()='Search']");

    private final By tableRows =
            By.cssSelector(".oxd-table-body .oxd-table-row");

    private final By tableHeaderCells =
            By.cssSelector(".oxd-table-header-cell");

    private final By rowCells =
            By.cssSelector(".oxd-table-cell");

    private final By paginationButtons =
            By.cssSelector(".oxd-pagination__page-item");

    private final By nextPageBtn =
            By.xpath("//button[contains(.,'Next')]");

    private final By totalRecordsLabel =
            By.cssSelector(".orangehrm-horizontal-padding span");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public void searchByEmployeeName(String name) {

        type(searchEmployeeName, name);
        click(searchButton);
    }

    // ============= TABLE READING =============

    public List<List<String>> getEmployeeTableData() {

        List<List<String>> table = new ArrayList<>();

        List<WebElement> rows = getElements(tableRows);

        for (WebElement row : rows) {

            List<WebElement> cells = row.findElements(rowCells);

            List<String> rowData = new ArrayList<>();

            // Skip checkbox column (0)
            // Skip actions column (last)

            for (int i = 1; i < cells.size() - 1; i++) {

                String text = cells.get(i).getText().trim();

                rowData.add(text);
            }

            table.add(rowData);
        }

        return table;
    }

    public List<String> getTableHeaders() {

        List<String> headers = new ArrayList<>();

        for (WebElement h : getElements(tableHeaderCells)) {

            headers.add(h.getText().trim());
        }

        return headers;
    }

    // ============= SEARCH EMPLOYEE =============

    public boolean isEmployeeInTable(String empId) {

        List<WebElement> rows = getElements(tableRows);

        for (WebElement row : rows) {

            List<WebElement> cells =
                    row.findElements(rowCells);

            // Employee ID is usually at index 1
            // index 0 = checkbox

            if (cells.size() > 1) {

                String employeeId =
                        cells.get(1).getText().trim();

                System.out.println("Checking Employee ID: "
                        + employeeId);

                if (employeeId.equalsIgnoreCase(empId)) {

                    System.out.println("Employee Found: "
                            + empId);

                    return true;
                }
            }
        }

        return false;
    }

    // ============= PAGINATION =============

    public int getTotalPages() {

        List<WebElement> btns =
                driver.findElements(paginationButtons);

        return Math.max(1, btns.size());
    }

    public void goToPage(int pageNumber) {

        List<WebElement> btns =
                driver.findElements(paginationButtons);

        for (WebElement btn : btns) {

            if (btn.getText().trim()
                    .equals(String.valueOf(pageNumber))) {

                scrollIntoView(btn);

                btn.click();

                return;
            }
        }

        throw new IllegalArgumentException(
                "Page " + pageNumber +
                        " not visible in pagination.");
    }

    public void clickNext() {

        click(nextPageBtn);
    }

    /**
     * Iterate through all pages
     * and find employee by exact ID.
     */
    public boolean findEmployeeAcrossAllPages(String empId) {

        while (true) {

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(tableRows)
            );

            // Search current page
            if (isEmployeeInTable(empId)) {

                return true;
            }

            // Find Next button
            List<WebElement> nextButtons =
                    driver.findElements(nextPageBtn);

            // No next button
            if (nextButtons.isEmpty()) {

                break;
            }

            WebElement next = nextButtons.get(0);

            // Check disabled state
            String disabled =
                    next.getAttribute("disabled");

            if (disabled != null ||
                    !next.isEnabled()) {

                break;
            }

            // Store current first row
            List<WebElement> currentRows =
                    driver.findElements(tableRows);

            // Go next page
            scrollIntoView(next);
            next.click();

            // Wait for page refresh
            wait.until(
                    ExpectedConditions.stalenessOf(currentRows.get(0))
            );
        }

        return false;
    }

    public String getTotalRecordsText() {

        return getText(totalRecordsLabel);
    }

    @Override
    public boolean isPageLoaded() {

        return isDisplayed(searchButton);
    }

    @Override
    public String getPageTitle() {

        return driver.getTitle();
    }

    @Override
    public String getPageUrl() {

        return driver.getCurrentUrl();
    }

    @Override
    public boolean isAt() {

        return getPageUrl().contains("/pim");
    }
}