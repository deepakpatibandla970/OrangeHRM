package com.orangehrm.tests;

import com.orangehrm.pages.LeavePage;
import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMPage;
import com.orangehrm.utils.ConfigReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class LeaveTest extends BaseTest {
    private LeavePage leave;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void loginAndNavigate() {
        DashboardPage dash = new LoginPage(driver)
                .loginAs(ConfigReader.get("username"), ConfigReader.get("password"));
        leave = dash.navigateToLeave();
    }

    @Test(description = "search user")
        public void searchUser() throws InterruptedException {
        Thread.sleep(2000);
        leave.from_date("2026-05-21");
        Thread.sleep(2000);
        leave.to_date("2026-05-25");
        Thread.sleep(2000);
        leave.set_leave_with_status("Pending Approval");
        Thread.sleep(2000);
        leave.set_leave_type("CAN - Vacation");
        Thread.sleep(2000);
        leave.set_employ_name("Paul Collings");
        Thread.sleep(2000);
        leave.set_sub_Unit("Administration");

        leave.include_Past_Employees();

        leave.search_Btn();

    }
}