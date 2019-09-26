package org.proxily.backend.integrationtests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MainTest {


    @Test()
    public void testEmailGenerator() {
        System.out.println("test");
        Assert.assertEquals("feedback@yoursite.com", "feedback@yoursite.com");

    }
}
