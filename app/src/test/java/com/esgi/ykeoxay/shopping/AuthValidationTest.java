package com.esgi.ykeoxay.shopping;

import com.esgi.ykeoxay.shopping.Validation.AuthValidation;
import com.esgi.ykeoxay.shopping.Validation.ProductValidation;
import com.esgi.ykeoxay.shopping.Validation.ShoppingListValidation;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ykeox on 07/02/2017.
 */

public class AuthValidationTest {

    @Test
    public void AuthValidation_CorrectName_ReturnsTrue() throws Exception {
        String name ="patrick";
        assertThat(AuthValidation.checkFirstName(name), is(true));
    }

    @Test
    public void ShoppingListValidation_IncorrectName_ReturnsFalse() throws Exception {
        String name ="}";
        assertThat(AuthValidation.checkFirstName(name), is(false));
    }

    @Test
    public void AuthValidation_CorrectPassword_ReturnsTrue() throws Exception {
        String password ="monKeyFC";
        assertThat(AuthValidation.checkPassword(password), is(true));
    }

    @Test
    public void AuthValidation_InCorrectPassword_ReturnsFalse() throws Exception {
        String password ="#";
        assertThat(AuthValidation.checkPassword(password), is(false));
    }

    @Test
    public void AuthValidation_CorrectEmail_ReturnsTrue() throws Exception {
        String email  ="monKeyFC@gmail.com";
        assertThat(AuthValidation.checkEmail(email), is(true));
    }


    @Test
    public void AuthValidation_InCorrectEmail_ReturnsFalse() throws Exception {
        String email  ="monKeyFC.com";
        assertThat(AuthValidation.checkEmail(email), is(false));
    }
}
