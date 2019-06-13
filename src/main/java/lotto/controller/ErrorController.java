package lotto.controller;

import spark.Request;
import spark.Response;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class ErrorController {
    public Object printMessage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("message", req.queryParams("message"));
        return render(model, "error.html");
    }

    public void catchException(Exception e, Request req, Response res) {
        try {
            res.redirect("/error?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            res.redirect("/error?message=" + "URL Encoding Error");
        }
    }
}
