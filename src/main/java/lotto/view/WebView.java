package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.Ticket;
import lotto.domain.UserLottos;
import lotto.domain.WinningLotto;
import lotto.presentation.UserLottoPresentation;
import lotto.presentation.WinningLottoPresentation;
import spark.Request;

import java.util.*;

public class WebView {
    public static UserLottoPresentation userLottoPresentation(Request request) {
        return new UserLottoPresentation(request.queryParams("lottoMoney"),
                request.queryParams("manualCount"), Arrays.asList(request.queryParamsValues("manualLottos")));

    }

    public static Map<String, Object> userLottoJson(UserLottos userLottos) {
        List<List<Integer>> numbers = new ArrayList<>();
        for (Ticket ticket : userLottos.tickets()) {
            numbers.add(ticket.ticketNumbers());
        }
        Map<String, Object> json = new HashMap<>();
        json.put("userLotto", numbers);
        return json;
    }

    public static WinningLottoPresentation winningLottoPresentation(Request request) {
        return new WinningLottoPresentation(request.queryParams("numbers"), request.queryParams("bonus"));
    }

    public static Map<String, Object> winningLottoJson(WinningLotto winningLotto) {
        Map<String, Object> json = new HashMap<>();
        json.put("numbers", winningLotto.ticketNumbers());
        json.put("bonus", winningLotto.bonusNumbers());
        return json;
    }

    public static Map<String, Object> lottoResultJson(LottoResult lottoResult) {
        Map<String, Object> json = new HashMap<>();
        json.put("result", lottoResult.results());
        json.put("summary", lottoResult.calculate().summary());
        return json;
    }

}
