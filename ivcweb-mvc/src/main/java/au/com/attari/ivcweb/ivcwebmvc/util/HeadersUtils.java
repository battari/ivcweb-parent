package au.com.attari.ivcweb.ivcwebmvc.util;

import java.util.Base64;

public class HeadersUtils {

    public static String getAuthorization(String user, String password)
    {
        String notEncoded = user + ":" + password;
        String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(notEncoded.getBytes());

        return encodedAuth;
    }
}
