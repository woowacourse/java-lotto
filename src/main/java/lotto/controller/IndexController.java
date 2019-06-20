package lotto.controller;

import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class IndexController {
    public static final String PATH_INDEX = "/";

    public static final Route serveIndexPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    };

    private IndexController() {
    }
}
