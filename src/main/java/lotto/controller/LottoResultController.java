package lotto.controller;

import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Price;
import lotto.domain.WinningLotto;
import lotto.service.LottoResultService;
import lotto.service.LottoService;
import lotto.service.RoundService;
import lotto.service.WinningLottoService;
import lotto.utils.ResultMessage;
import lotto.utils.ViewUtils;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static lotto.controller.LottoController.PRICE;

public class LottoResultController {
    private static final String YIELD = "yield";
    private static final String LOTTO_RESULT = "userLottoResult";
    private static final String ROUND = "round";
    private static final String WINNING_LOTTO = "winningLotto";
    private static final String BONUS = "bonusNumber";
    private static final String LOTTOS = "lottos";

    public static Route makeLottoResultPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        RoundService roundService = new RoundService();
        LottoService lottoService = new LottoService();
        WinningLottoService winningLottoService = new WinningLottoService();
        LottoResultService lottoResultService = new LottoResultService();

        Lottos lottos = req.session().attribute(LOTTOS);
        WinningLotto winningLotto = winningLottoService.getWinningLotto(req.queryParams(WINNING_LOTTO), req.queryParams(BONUS));
        LottoResult lottoResult = lottoResultService.getLottoResult(lottos, winningLotto);

        Price price = req.session().attribute(PRICE);
        model.put(YIELD, lottoResult.findYield(price.getPrice()));
        model.put(LOTTO_RESULT, ResultMessage.getResult(lottoResult));

        int round = req.session().attribute(ROUND);

        roundService.addRoundInDB(round, price);
        lottoService.addLottosInDB(round, lottos);
        winningLottoService.addWinningLottoInDB(round, winningLotto);

        return ViewUtils.render(model, "result.html");
    };

    public static Route makeLottoResultByRoundPage = (req, res) -> {
        LottoService lottoService = new LottoService();
        WinningLottoService winningLottoService = new WinningLottoService();
        LottoResultService lottoResultService = new LottoResultService();

        Map<String, Object> model = new HashMap<>();

        int round = Integer.parseInt(req.queryParams(ROUND));
        Lottos lottos = lottoService.getLottoByRound(round);
        WinningLotto winningLotto = winningLottoService.getWinningLottoByRound(round);
        LottoResult lottoResult = LottoResult.generateLottoResult(lottos, winningLotto);

        model.put(YIELD, lottoResultService.getYield(lottoResult, round));
        model.put(LOTTO_RESULT, lottoResultService.getResultMessage(lottoResult));
        return ViewUtils.render(model, "result.html");
    };

}
