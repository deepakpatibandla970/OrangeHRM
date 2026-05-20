package com.orangehrm.tests;

import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LeavePage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.ConfigReader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LeaveTest extends BaseTest {

    private LeavePage leave;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void loginAndNavigate() {

        DashboardPage dash = new LoginPage(driver)
                .loginAs(
                        ConfigReader.get("username"),
                        ConfigReader.get("password"));

        leave = dash.navigateToLeave();
    }

    @Test(description = "search user")
    public void searchUser() {

        leave.from_date("2026-05-21");

        leave.to_date("2026-05-25");

        leave.set_leave_with_status("Pending Approval");

        leave.set_leave_type("CAN - Vacation");

        leave.set_employ_name("Peter Mac Anderson");

        leave.set_sub_Unit("Administration");

        leave.include_Past_Employees();

        leave.search_Btn();
    }
}