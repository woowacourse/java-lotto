package lotto.domain.controller;

import lotto.domain.service.ResultService;
import spark.Request;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class ShowGameController {
    public static final String SHOW_GAME_URL = "/showGame";

    private static final ShowGameController INSTANCE = new ShowGameController();

    private ShowGameController() {
    }

    public static ShowGameController getInstance() {
        return INSTANCE;
    }

    public String get(final Request req) {
        Map<String, Object> model = new HashMap<>();
        ResultService resultService = ResultService.getInstance();
        model.put("resultDTO", resultService.getResult(req.session().attribute("newRound")));
        return render(model, "result.html");
    }
}
