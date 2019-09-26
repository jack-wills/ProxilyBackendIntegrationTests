package org.proxily.backend.integrationtests;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadItemTest {

    @Test(dependsOnMethods = {"signInTest"})
    public void uploadTextTest() throws JSONException {
        JSONObject request = new JSONObject();
        request.put("latitude", 17.432402);
        request.put("longitude", 17.432402);
        request.put("mediaType", "text");
        request.put("media", "I am text");
        JSONObject response = Common.postToUrlAuth("service/uploadItem", request);
        Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
    }
/*
    @Test
    public void uploadItemTestNoUpload() throws JSONException {
        JSONObject response = Common.postToUrlAuth("service/uploadItem", new JSONObject());
        Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
    }

    @Test
    public void uploadItemTestWrongMediaType() throws JSONException {
        JSONObject response = Common.postToUrlAuth("service/uploadItem", new JSONObject());
        Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
    }

    @Test
    public void uploadItemTestNoMediaType() throws JSONException {
        JSONObject response = Common.postToUrlAuth("service/uploadItem", new JSONObject());
        Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
    }

    @Test
    public void uploadItemTestNoMedia() throws JSONException {
        JSONObject response = Common.postToUrlAuth("service/uploadItem", new JSONObject());
        Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
    }

    @Test
    public void uploadItemTestNoLatitude() throws JSONException {
        JSONObject response = Common.postToUrlAuth("service/uploadItem", new JSONObject());
        Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
    }

    @Test
    public void uploadItemTestNoLongitude() throws JSONException {
        JSONObject response = Common.postToUrlAuth("service/uploadItem", new JSONObject());
        Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
    }

    @Test
    public void uploadProfilePictureTest() throws JSONException {
        JSONObject response = Common.postToUrlAuth("service/uploadProfilePicture", new JSONObject());
        Assert.assertFalse(response.has("error"), "Response contained an error. response = " + response.toString());
    }
*/
}
