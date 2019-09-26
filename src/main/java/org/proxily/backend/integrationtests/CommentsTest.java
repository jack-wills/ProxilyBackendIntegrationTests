package org.proxily.backend.integrationtests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CommentsTest {
    @Test
    public void postComment() {
        System.out.println("post comment");
        Assert.assertEquals("feedback@yoursite.com", "feedback@yoursite.com");

    }
    @Test
    public void voteComment() {
        System.out.println(" voteComment");
        Assert.assertEquals("feedback@yoursite.com", "feedback@yoursite.com");

    }
    @Test
    public void getComment() {
        System.out.println("getComment");
        Assert.assertEquals("feedback@yoursite.com", "feedback@yoursite.com");

    }
}
