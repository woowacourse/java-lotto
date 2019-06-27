package lotto.domain.controller;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;


public class HomeController {

    public static final String HOME_URL = "/";
    private static final HomeController INSTANCE = new HomeController();

    private HomeController() {
    }

    public static HomeController getInstance() {
        return INSTANCE;
    }

    public String get(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        req.session(true);
        return render(model, "main.html");
    }
}
