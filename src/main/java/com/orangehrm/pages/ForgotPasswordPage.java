package com.orangehrm.pages;

import com.orangehrm.utils.WaitUtils;
import org.openqa.selenium.By;

public class ForgotPasswordPage {

    private final By resetPasswordHeader =
            By.xpath("//h6[@class='oxd-text oxd-text--h6 orangehrm-forgot-password-title']");

    public boolean isResetPasswordPageDisplayed() {
        return WaitUtils.isVisible(resetPasswordHeader);
    }
}