package lotto.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Encoder {
    private Encoder() {
    }

    public static String encodeUTF8(final String message) throws UnsupportedEncodingException {
        return URLEncoder.encode(message, "UTF-8");
    }
}
