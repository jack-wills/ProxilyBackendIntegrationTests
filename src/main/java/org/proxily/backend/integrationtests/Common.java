package org.proxily.backend.integrationtests;

import junit.framework.Assert;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Common {
    private static String token;
    private static String email;

    public static JSONObject postToUrlAuth(String urlPath, JSONObject requestBody) {
        return postToUrl(urlPath, requestBody, token);
    }

    public static JSONObject postToUrl(String urlPath, JSONObject requestBody) {
        return postToUrl(urlPath, requestBody, "");
    }

    public static JSONObject postToUrl(String urlPath, JSONObject requestBody, String token) {
        try {
            System.out.println("Sending: " + requestBody.toString());
            System.out.println("To URL: " + "http://localhost:8080/" + urlPath);
            URL url = new URL("http://localhost:8080/" + urlPath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            if (token != "") {
                conn.setRequestProperty("Authorization", "Bearer " + token);
            }
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
            os.write(requestBody.toString());
            os.flush();
            BufferedReader is = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            while(is.ready()) {
                stringBuilder.append(is.readLine());
            }
            conn.disconnect();
            return new JSONObject(stringBuilder.toString());
        } catch (IOException e){
            throw new IllegalStateException(e);
        } catch (JSONException e){
            throw new IllegalStateException(e);
        }
    }

    public static void setToken(String t) {
        token = t;
    }
    public static String getToken() {
        return token;
    }

    public static void setEmail(String t) {
        email = t;
    }
    public static String getEmail() {
        return email;
    }
}
