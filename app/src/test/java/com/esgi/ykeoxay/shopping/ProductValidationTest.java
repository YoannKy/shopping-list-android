package com.esgi.ykeoxay.shopping;

import com.esgi.ykeoxay.shopping.Validation.ProductValidation;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProductValidationTest {

    @Test
    public void ProductValidation_CorrectName_ReturnsTrue() throws Exception {
        String name ="patrick";
        assertThat(ProductValidation.checkName(name), is(true));
    }

    @Test
    public void ProductValidation_IncorrectName_ReturnsFalse() throws Exception {
        String name ="}";
        assertThat(ProductValidation.checkName(name), is(false));
    }

    @Test
    public void ProductValidation_CorrectQuantity_ReturnsTrue() throws Exception {
        String quantity ="5";
        assertThat(ProductValidation.checkQuantity(quantity), is(true));
    }

    @Test
    public void ProductValidation_IncorrectQuantity_ReturnsFalse() throws Exception {
        String quantity ="s}";
        assertThat(ProductValidation.checkQuantity(quantity), is(false));
    }

    @Test
    public void ProductValidation_CorrectPrice_ReturnsTrue() throws Exception {
        String price ="1";
        assertThat(ProductValidation.checkPrice(price), is(true));
    }

    @Test
    public void ProductValidation_IncorrectPrice_ReturnsFalse() throws Exception {
        String price ="111111111}";
        assertThat(ProductValidation.checkName(price), is(false));
    }

}
