package ua.restaurant.srvlt.model.service.utility;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EncodingUtility {

    public static String getUTF8String(String string, Charset originalEncoding) {
        return new String(string.getBytes(originalEncoding), StandardCharsets.UTF_8);

    }
}
