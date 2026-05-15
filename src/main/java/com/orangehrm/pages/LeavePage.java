package com.orangehrm.pages;

import com.orangehrm.base.BasePage;
import com.orangehrm.interfaces.IPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.tracing.Status;

import java.util.Date;

public class LeavePage extends BasePage implements IPage {

    private final By from_date= By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    private final By to_date= By.xpath ("(//input[@class='oxd-input oxd-input--active'])[3]");
    private final By showLeaveWithStatus =By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]");
    private final By leaveTye =By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]");
    private final By employeeName =By.xpath("//input[@placeholder='Type for hints...']");
    private final By subUnit =By.xpath("(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[3]");
    private final By includePastEmployees =By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']");
    private final By searchBtn =By.xpath("//button[text()=' Search ']");



}
