package lotto.controller;

import lotto.domain.dto.RankingDTO;
import lotto.domain.dto.ResultDTO;
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
    private LottoResultController() {
        throw new AssertionError();
    }

    public static String goIndex(Request request, Response response) throws SQLDataException {
        Map<String, Object> model = new HashMap<>();
        List<ResultDTO> lottoGames = LottoResultService.getInstance().selectAllLottoResult();
        List<RankingDTO> ranking = LottoResultService.getInstance().createUserRanking(lottoGames);

        model.put("lottoGames", lottoGames);
        model.put("ranking", ranking);
        return render(model, "index.html");
    }

    public static String goLottoResult(Request request, Response response) throws SQLDataException {
        int round = Integer.parseInt(nullable(request.params(":round")));

        ResultDTO resultDTO = LottoResultService.getInstance().selectLottoResult(round);
        List<Lotto> lottoTicket = LottoService.getInstance().selectAllLotto(round);

        long countOfAutoLotto = LottoService.getInstance().calculateCountOfAutoLotto(lottoTicket);

        Map<String, Object> model = new HashMap<>();
        model.put("result", resultDTO);
        model.put("winningLotto", LottoService.getInstance().selectWinningLotto(round));
        model.put("lottoTicket", lottoTicket);
        model.put("auto", countOfAutoLotto);
        model.put("manual", lottoTicket.size() - countOfAutoLotto);
        return render(model, "lottoResult.html");
    }
}
