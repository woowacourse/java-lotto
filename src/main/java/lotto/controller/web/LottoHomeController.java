package lotto.controller.web;

import spark.*;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class LottoHomeController {

    public static String home(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "view/lotto_home.html");
    }

    public static String checkRound(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "view/lotto_check_round.html");
    }

    public static String contact(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        return render(model, "view/lotto_contact.html");
    }
}
