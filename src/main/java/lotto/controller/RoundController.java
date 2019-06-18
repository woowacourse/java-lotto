package lotto.controller;

import lotto.service.RoundService;
import lotto.utils.ViewUtils;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

public class RoundController {

    public static Route makeRoundPage = (req, res) -> {
        RoundService roundService = new RoundService();
        Map<String, Object> model = new HashMap<>();

        int presentRound = roundService.getPresentRound();
        model.put("present", presentRound);
        req.session().attribute("round", presentRound);

        model.put("rounds", roundService.getAllRound());
        model.put("message", req.queryParams("message"));

        return ViewUtils.render(model, "home.html");
    };
}
