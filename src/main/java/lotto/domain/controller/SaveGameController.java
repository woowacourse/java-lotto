package lotto.domain.controller;

import lotto.domain.service.GameService;
import spark.Request;

public class SaveGameController {
    public static final String SAVE_GAME_URL = "/saveGame";

    private static final SaveGameController INSTANCE = new SaveGameController();

    private SaveGameController() {
    }

    public static SaveGameController getInstance() {
        return INSTANCE;
    }

    public String get(final Request req) {
        GameService gameService = GameService.getInstance();
        gameService.saveGame(req.session().attribute("newRound"), req.queryParams("manualLottos"),
                req.queryParams("totalPurchaseCount"), req.queryParams("manualCount"),
                req.queryParams("bonusNumber"), req.queryParams("winningLottoNumber"), req.queryParams("money"));
        return null;
    }
}
