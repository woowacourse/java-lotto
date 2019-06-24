package lotto.controller.web;

import lotto.controller.web.exception.LottoCheckControllerException;
import lotto.service.LottoTicketService;
import lotto.service.LottoWinningService;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.WebUILottoApplication.render;

public class LottoCheckController {
    public static String findRoundLotto(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        try {
            int round = Integer.valueOf(req.queryParams("checkRound"));
            List<String> results = new LottoWinningService().findByResultRound(round);

            model.put("lottos", new LottoTicketService().findByLottosTicket(round));
            model.put("winningLottos", results.get(0));
            model.put("bonusBall", results.get(1));
            model.put("rank", printRank(results.get(2)));
            model.put("prize", results.get(3));
            model.put("incomeRate", results.get(4));
            return render(model, "view/lotto_find.html");
        } catch (Exception e) {
            model.put("error_check", e.getMessage());
            return render(model, "view/lotto_check_round.html");
        }
    }

    private static List<String> printRank(String rank) {
        return Arrays.asList(rank.substring(1, rank.length() - 1).split(","));
    }
}
