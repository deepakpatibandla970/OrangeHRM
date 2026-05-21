package com.orangehrm.tests;

import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMPage;
import com.orangehrm.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
//import org.testng.SkipException;

public class PIMTest extends BaseTest {

    private PIMPage pim;

    @BeforeMethod(dependsOnMethods = "setUp")
    public void loginAndNavigate() {
        DashboardPage dash = new LoginPage(driver)
                .loginAs(ConfigReader.get("username"), ConfigReader.get("password"));
        pim = dash.navigateToPIM();
    }

    @Test(description = "TABLE - read headers and validate columns")
    public void testEmployeeTableHeaders() {
        List<String> headers = pim.getTableHeaders();
        Assert.assertTrue(headers.contains("First (& Middle) Name"),
                "Expected header missing. Got: " + headers);
    }

    @Test(description = "TABLE - read all rows on current page")
    public void testReadEmployeeRows() {
        List<List<String>> data = pim.getEmployeeTableData();
        Assert.assertFalse(data.isEmpty(), "Employee table is empty");
        Assert.assertTrue(data.get(0).size() >= 3);
    }


    @Test(description = "PAGINATION - navigate page 2 if available")
    public void testPaginationToPage2() {

        int pages = pim.getTotalPages();

        if (pages >= 2) {

            pim.goToPage(2);

            Assert.assertFalse(
                    pim.getEmployeeTableData().isEmpty()
            );

        } else {

            Assert.assertFalse(pim.getEmployeeTableData().isEmpty(), "Single page still contains employee records");
        }
    }

   @Test(description = "PAGINATION - verify employee table has records")
   public void testFindEmployeeAcrossPages() {

       List<List<String>> data = pim.getEmployeeTableData();

       Assert.assertFalse(
               data.isEmpty(),
               "Employee table should contain records"
       );

       String firstEmpId = data.get(0).get(0);

       boolean found = pim.findEmployeeAcrossAllPages(firstEmpId);

       Assert.assertTrue(
               found,
               "Employee should be findable across pages"
       );
   }


}