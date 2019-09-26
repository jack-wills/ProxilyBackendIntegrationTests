package org.proxily.backend.integrationtests;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTest {

    private String alphaNumeric = "ABCDEFGHIJKLMNOPQURTUVWXYZ1234567890";

    private String generateEmail() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(alphaNumeric.charAt((int) Math.round(Math.random() * 35)));
        }
        stringBuilder.append("@proxily.com");
        String email =  stringBuilder.toString();
        Common.setEmail(email);
        return email;
    }

    @Test(priority = -2)
    public void signInTest() {
        try {
            JSONObject request = new JSONObject();
            request.put("email", Common.getEmail());
            request.put("password", "test123");
            JSONObject response = Common.postToUrl("auth/signin", request);
            Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
            Assert.assertTrue(response.has("token"), "Response didn't contain token. response = " + response.toString());
            Common.setToken(response.getString("token"));
        } catch (JSONException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Test(priority = -2)
    public void signInTestWrongPassword() {
        try {
            JSONObject request = new JSONObject();
            request.put("email", Common.getEmail());
            request.put("password", "wrong");
            JSONObject response = Common.postToUrl("auth/signin", request);
            Assert.assertTrue(response.has("error"), "Response didn't contain an error. response = " + response.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Test(priority = -2)
    public void signInTestWrongEmail() {
        try {
            JSONObject request = new JSONObject();
            request.put("email", "wrong");
            request.put("password", "test123");
            JSONObject response = Common.postToUrl("auth/signin", request);
            Assert.assertTrue(response.has("error"), "Response didn't contain an error. response = " + response.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Test
    public void signInFacebookTest() {
        //TODO test this
        Assert.assertEquals("feedback@yoursite.com", "feedback@yoursite.com");

    }

    @Test(priority = -1)
    public void checkTokenTest() {
        try {
            JSONObject response = Common.postToUrlAuth("service/checkToken", new JSONObject());
            Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
            Assert.assertTrue(response.has("name"), "Response didn't contain name. response = " + response.toString());
            Assert.assertTrue(response.has("email"), "Response didn't contain email. response = " + response.toString());
            Assert.assertTrue(response.has("profilePicture"), "Response didn't contain profilePicture. response = " + response.toString());
            Assert.assertEquals(response.getString("name"), "testFirst testLast");
            Assert.assertEquals(response.getString("email"), Common.getEmail());
        } catch (JSONException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Test(priority = -1)
    public void checkTokenTestTokenNotValid() {
        try {
            JSONObject response = Common.postToUrl("service/checkToken", new JSONObject(), "Not a valid token");
            Assert.assertTrue(false, "Service returned 200. response = " + response.toString());
        } catch (IllegalStateException e) {

        }
    }

    @Test(priority = -1)
    public void checkTokenTestTokenNotPresent() {
        try {
            JSONObject response = Common.postToUrl("service/checkToken", new JSONObject());
            Assert.assertTrue(false, "Service returned 200. response = " + response.toString());
        } catch (IllegalStateException e) {

        }
    }

    @Test(priority = -3)
    public void registerTest() {
        try {
            JSONObject request = new JSONObject();
            request.put("email", generateEmail());
            request.put("password", "test123");
            request.put("firstName", "testFirst");
            request.put("lastName", "testLast");
            JSONObject response = Common.postToUrl("auth/register", request);
            Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
            Assert.assertTrue(response.has("token"), "Response didn't contain token. response = " + response.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Test(priority = -3)
    public void registerTestEmailNotPresent() {
        try {
            JSONObject request = new JSONObject();
            request.put("password", "test123");
            request.put("firstName", "testFirst");
            request.put("lastName", "testLast");
            JSONObject response = Common.postToUrl("auth/register", request);
            Assert.assertTrue(response.has("error"), "Response didn't contain an error. response = " + response.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (IllegalStateException e) {

        }
    }

    @Test(priority = -3)
    public void registerTestPasswordNotPresent() {
        try {
            JSONObject request = new JSONObject();
            request.put("email", generateEmail());
            request.put("firstName", "testFirst");
            request.put("lastName", "testLast");
            JSONObject response = Common.postToUrl("auth/register", request);
            Assert.assertTrue(response.has("error"), "Response didn't contain an error. response = " + response.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (IllegalStateException e) {

        }
    }

    @Test(priority = -3)
    public void registerTestFirstNameNotPresent() {
        try {
            JSONObject request = new JSONObject();
            request.put("email", generateEmail());
            request.put("password", "test123");
            request.put("lastName", "testLast");
            JSONObject response = Common.postToUrl("auth/register", request);
            Assert.assertTrue(response.has("error"), "Response didn't contain an error. response = " + response.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (IllegalStateException e) {

        }
    }

    @Test(priority = -3)
    public void registerTestLastNameNotPresent() {
        try {
            JSONObject request = new JSONObject();
            request.put("email", generateEmail());
            request.put("password", "test123");
            request.put("firstName", "testFirst");
            JSONObject response = Common.postToUrl("auth/register", request);
            Assert.assertTrue(response.has("error"), "Response didn't contain an error. response = " + response.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (IllegalStateException e) {

        }
    }

    @Test(priority = -2)
    public void registerTestSameEmail() {
        try {
            JSONObject request = new JSONObject();
            request.put("email", Common.getEmail());
            request.put("password", "test123");
            request.put("firstName", "testFirst");
            request.put("lastName", "testLast");
            JSONObject response = Common.postToUrl("auth/register", request);
            Assert.assertTrue(response.has("error"), "Response didn't contain an error. response = " + response.toString());
        } catch (JSONException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (IllegalStateException e) {

        }
    }
}
