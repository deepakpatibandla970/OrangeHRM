package com.orangehrm.pages;

import com.orangehrm.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

public class LeavePage extends BasePage {

    private static final By APPLY_LEAVE_HEADER =
            By.xpath("//h6[contains(normalize-space(),'Apply Leave')]");
    private static final By LEAVE_LIST_TABLE =
            By.cssSelector(".oxd-table");

    private static By topNavTab(String tab) {
        return By.xpath("//a[contains(@class,'oxd-topbar-body-nav-tab-item') "
                + "and normalize-space()='" + tab + "']");
    }

    public boolean isLoaded() {
        return WaitUtils.urlContains("leave");
    }

    @Step("Open the Leave sub-tab '{tab}'")
    public void openTab(String tab) {
        click(topNavTab(tab));
        waitForLoaderToDisappear();
        // Wait for Apply page to fully render
        if (tab.equalsIgnoreCase("Apply"))
        {

            WaitUtils.visible(APPLY_LEAVE_HEADER);
        }
    }

    @Step("Confirm the Apply Leave page is displayed")
    public boolean isApplyLeavePageDisplayed() {
        return isDisplayed(APPLY_LEAVE_HEADER);
    }

    @Step("Read the available Leave Type options")
    public List<String> getLeaveTypeOptions() throws InterruptedException {
        return getDropdownOptions("Leave Type");
    }

    @Step("Confirm a leave list table is rendered")
    public boolean isLeaveListDisplayed() {
        return isDisplayed(LEAVE_LIST_TABLE);
    }
}
