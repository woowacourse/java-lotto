package lotto.controller;

import lotto.service.PaymentInfoService;
import lotto.service.dto.RankingDto;
import lotto.service.dto.ResultDto;
import lotto.domain.lotto.Lotto;
import lotto.service.LottoResultService;
import lotto.service.LottoService;
import spark.Request;
import spark.Response;

import java.sql.SQLDataException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.controller.common.CommonController.nullable;
import static lotto.controller.common.CommonController.render;

public class LottoResultController {
    private static final LottoService LOTTO_SERVICE = LottoService.getInstance();
    private static final LottoResultService LOTTO_RESULT_SERVICE = LottoResultService.getInstance();

    private LottoResultController() {
        throw new AssertionError();
    }

    public static String goIndex(Request request, Response response) throws SQLDataException {
        Map<String, Object> model = new HashMap<>();
        List<ResultDto> lottoGames = LOTTO_RESULT_SERVICE.selectAllLottoResult();
        List<RankingDto> ranking = LOTTO_RESULT_SERVICE.createUserRanking(lottoGames);

        model.put("lottoGames", lottoGames);
        model.put("ranking", ranking);
        return render(model, "index.html");
    }

    public static String goLottoResult(Request request, Response response) throws SQLDataException {
        int round = Integer.parseInt(nullable(request.params(":round")));

        ResultDto resultDto = LOTTO_RESULT_SERVICE.selectLottoResult(round);
        List<Lotto> lottoTicket = LOTTO_SERVICE.selectAllLotto(round);

        long countOfAutoLotto = LOTTO_SERVICE.calculateCountOfAutoLotto(lottoTicket);

        Map<String, Object> model = new HashMap<>();
        model.put("result", resultDto);
        model.put("winningLotto", LOTTO_SERVICE.selectWinningLotto(round));
        model.put("lottoTicket", lottoTicket);
        model.put("auto", countOfAutoLotto);
        model.put("manual", lottoTicket.size() - countOfAutoLotto);
        return render(model, "lottoResult.html");
    }
}
