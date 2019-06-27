package lotto.domain.controller;

import lotto.domain.service.WinningLottoService;
import spark.Request;

import java.util.HashMap;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class StartGameController {
    public static final String START_GAME_URL = "/startGame";
    private static final StartGameController INSTANCE = new StartGameController();

    private StartGameController() {
    }

    public static StartGameController getInstance() {
        return INSTANCE;
    }

    public String get(final Request req) {
        Map<String, Object> model = new HashMap<>();
        WinningLottoService winningLottoService = WinningLottoService.getInstance();

        req.session().attribute("newRound", winningLottoService.getNewRound());
        model.put("newRound", req.session().attribute("newRound"));

        return render(model, "lotto.html");
    }
}
