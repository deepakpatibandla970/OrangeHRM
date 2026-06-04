package com.orangehrm.pages;

import com.orangehrm.utils.WaitUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DashboardPage extends BasePage {

    private static final By BREADCRUMB_HEADER =
            By.cssSelector(".oxd-topbar-header-breadcrumb h6");
    private static final By USER_DROPDOWN_TAB =
            By.cssSelector(".oxd-userdropdown-tab");
    private static final By USER_DROPDOWN_LINKS =
            By.cssSelector("ul.oxd-dropdown-menu li a.oxd-userdropdown-link");
    private static final By DASHBOARD_WIDGETS =
            By.cssSelector(".orangehrm-dashboard-widget");
    private static final By DASHBOARD_WIDGET_TITLES =
            By.cssSelector(".orangehrm-dashboard-widget-name p");
    private static final By QUICK_LAUNCH_ITEMS =
            By.cssSelector(".orangehrm-quick-launch-card");

    private static By menuItem(String module) {
        return By.xpath("//a[contains(@class,'oxd-main-menu-item')]"
                + "[.//span[normalize-space()='" + module + "']]");
    }

    private static By dropdownLink() {
        return By.xpath("//a[contains(@class,'oxd-userdropdown-link') "
                + "and normalize-space()='" + "Logout" + "']");
    }

    public boolean isLoaded() {
        return WaitUtils.urlContains("dashboard")
                && getBreadcrumbHeader().equalsIgnoreCase("Dashboard");
    }

    @Step("Read the page breadcrumb header")
    public String getBreadcrumbHeader() {
        return getText(BREADCRUMB_HEADER);
    }

    @Step("Navigate to the '{module}' module")
    public void navigateTo(String module) {
        click(menuItem(module));
        waitForLoaderToDisappear();
    }

    @Step("Confirm the current page URL contains '{fragment}'")
    public boolean isOnUrl(String fragment) {
        return WaitUtils.urlContains(fragment);
    }

    @Step("Open the user account dropdown")
    public void openUserDropdown() {
        click(USER_DROPDOWN_TAB);
        WaitUtils.visible(USER_DROPDOWN_LINKS);
    }

    @Step("Read the user dropdown options")
    public List<String> getUserDropdownOptions() {
        openUserDropdown();
        return WaitUtils.allVisible(USER_DROPDOWN_LINKS).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    @Step("Log out of OrangeHRM")
    public void logout() {
        openUserDropdown();
        click(dropdownLink());
        WaitUtils.urlContains("auth/login");
        new LoginPage();
    }

    @Step("Count the dashboard widgets")
    public int getWidgetCount() {
        WaitUtils.allVisible(DASHBOARD_WIDGETS);
        return count(DASHBOARD_WIDGETS);
    }

    @Step("Read the dashboard widget titles")
    public List<String> getWidgetTitles() {
        return WaitUtils.allVisible(DASHBOARD_WIDGET_TITLES).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    private static final By QUICK_LAUNCH =
            By.cssSelector(".orangehrm-quick-launch-card");

    public int getQuickLaunchCount() {
        return WaitUtils.allVisible(QUICK_LAUNCH).size();
    }
}
