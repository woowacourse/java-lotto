package lotto.controller;

import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class WinningLottoController {

    public static Object showWinningLottoInputPage(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "winninglottoinput.html");
    }
}
