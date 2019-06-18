package lotto.controller;

import spark.Request;
import spark.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class ErrorController {
    private static final String ERROR_HTML = "error.html";
    private static final String ERROR_MESSAGE = "message";

    public Object printMessage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put(ERROR_MESSAGE, req.queryParams(ERROR_MESSAGE));
        return render(model, ERROR_HTML);
    }

    public void catchException(Exception e, Request req, Response res) {
        try {
            res.redirect("/error?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            res.redirect("/error?message=" + "URL Encoding Error");
        }
    }
}
