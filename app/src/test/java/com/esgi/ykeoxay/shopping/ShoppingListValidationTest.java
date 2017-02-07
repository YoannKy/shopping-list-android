package com.esgi.ykeoxay.shopping;

import com.esgi.ykeoxay.shopping.Validation.ShoppingListValidation;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ykeox on 07/02/2017.
 */

public class ShoppingListValidationTest {

    @Test
    public void ShoppingListValidation_CorrectName_ReturnsTrue() throws Exception {
        String name ="patrick";
        assertThat(ShoppingListValidation.checkName(name), is(true));
    }

    @Test
    public void ShoppingListValidation_IncorrectName_ReturnsFalse() throws Exception {
        String name ="}";
        assertThat(ShoppingListValidation.checkName(name), is(false));
    }
}
