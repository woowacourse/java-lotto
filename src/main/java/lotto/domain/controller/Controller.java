package lotto.domain.controller;

import lotto.domain.service.GameService;
import lotto.domain.service.ResultService;
import lotto.domain.service.WinningLottoService;
import spark.Request;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    public static Map<String, Object> startGame(final Request req) {
        Map<String, Object> model = new HashMap<>();
        WinningLottoService winningLottoService = new WinningLottoService();

        req.session().attribute("newRound", winningLottoService.getNewRound());
        model.put("newRound", req.session().attribute("newRound"));
        return model;
    }

    public static Map<String, Object> showGame(final int newRound) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        ResultService resultService = new ResultService();
        model.put("resultDTO", resultService.getResult(newRound));
        return model;
    }

    public static void saveGame(final Request req) throws SQLException {
        GameService gameService = new GameService();
        gameService.saveGame(req.session().attribute("newRound"), req.queryParams("manualLottos"),
                req.queryParams("totalPurchaseCount"), req.queryParams("manualCount"),
                req.queryParams("bonusNumber"),req.queryParams("winningLottoNumber"), req.queryParams("money"));
    }
}
