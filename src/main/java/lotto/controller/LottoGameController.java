package lotto.controller;

import lotto.db.dao.LottoGameDAO;
import lotto.domain.dto.LottoGameResultDto;
import lotto.service.LottoGameService;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static lotto.utils.WebUIRenderer.render;

public class LottoGameController {
    public static final int MIN_BOUND_OF_LOTTO_COUNT = 1;
    public static final int MAX_BOUND_OF_LOTTO_COUNT = 6;
    private static final String DELIMITER = ",";

    public static Route index = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    };

    public static Route addWinningLotto = (req, res) -> {
        StringBuilder lottoNumbers = new StringBuilder();
        for (int i = MIN_BOUND_OF_LOTTO_COUNT; i <= MAX_BOUND_OF_LOTTO_COUNT; i++) {
            lottoNumbers.append(req.queryParams("num" + i)).append(DELIMITER);
        }

        LottoGameService.addWinningLottoTicket(lottoNumbers.toString(), req.queryParams("bonusBall"));

        res.redirect("/win/result");
        return null;
    };

    public static Route latestGameResult = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        LottoGameResultDto lottoGameResult = LottoGameService.findLatestLottoGameResult();

        model.put("result", lottoGameResult);
        return render(model, "lotto_result.html");
    };

    public static Route gameResultByWinningLottoId = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        LottoGameResultDto lottoGameResult = LottoGameService.findResultByWinningLottoId(req.params(":week"));

        model.put("result", lottoGameResult);
        return render(model, "lotto_result.html");
    };

    public static Route allWeeksOfGame = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("weeksOfGame", LottoGameDAO.findAllWinningLottoId());

        return render(model, "select_result.html");
    };
}
