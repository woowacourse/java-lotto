package lotto.controller;

import lotto.domain.lotto.Lotto;
import lotto.service.LottoResultService;
import lotto.service.LottoService;
import lotto.service.dto.RankingDto;
import lotto.service.dto.ResultDto;
import spark.Request;
import spark.Response;

import java.sql.SQLDataException;
import java.sql.SQLException;
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

    public static String goIndex(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        List<ResultDto> lottoGames = null;
        try {
            lottoGames = LOTTO_RESULT_SERVICE.selectAllLottoResult();
        } catch (SQLException e) {
            e.printStackTrace();
            e.printStackTrace();
            model.put("errorMessage", "죄송합니다. 해당 로또를 조회할 수 없습니다. 관리자에게 문의해주세요.");
            return render(model, "lottoNumbers.html");
        }
        List<RankingDto> ranking = LOTTO_RESULT_SERVICE.createUserRanking(lottoGames);

        model.put("lottoGames", lottoGames);
        model.put("ranking", ranking);
        return render(model, "index.html");
    }

    public static String goLottoResult(Request request, Response response) {
        int round = Integer.parseInt(nullable(request.params(":round")));

        List<Lotto> lottoTicket = null;
        Map<String, Object> model = new HashMap<>();
        try {
            lottoTicket = LOTTO_SERVICE.selectAllLotto(round);
            model.put("result", LOTTO_RESULT_SERVICE.selectLottoResult(round));
            model.put("winningLotto", LOTTO_SERVICE.selectWinningLotto(round));
        } catch (SQLException e) {
            e.printStackTrace();
            model.put("errorMessage", "죄송합니다. 해당 로또를 조회할 수 없습니다. 관리자에게 문의해주세요.");
            return render(model, "lottoNumbers.html");
        }

        long countOfAutoLotto = LOTTO_SERVICE.calculateCountOfAutoLotto(lottoTicket);

        model.put("lottoTicket", lottoTicket);
        model.put("auto", countOfAutoLotto);
        model.put("manual", lottoTicket.size() - countOfAutoLotto);
        return render(model, "lottoResult.html");
    }
}
