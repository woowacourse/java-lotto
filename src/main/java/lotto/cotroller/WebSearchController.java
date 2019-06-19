package lotto.cotroller;

import lotto.WebUILottoApplication;
import lotto.domain.dao.LottoDAO;
import lotto.domain.dao.ResultDAO;
import lotto.domain.dao.RoundDAO;
import lotto.domain.dao.WinningDAO;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebSearchController implements WebController {
    public static final List<String> RANK = Arrays.asList("fifth", "fourth", "third", "second", "first");

    public String page(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        int round = Integer.parseInt(req.queryParams("round"));
        if (round != 0) {
            model.put("purchaseLotto", LottoDAO.searchLottoNumbers(round));
            addWinningLotto(model, round);
            addResult(model, round);
        }
        model.put("searchCount", RoundDAO.searchMaxCount());
        return WebUILottoApplication.render(model, "lotto.html");
    }

    private static void addWinningLotto(Map<String, Object> model, int round) throws SQLException {
        model.put("winningLotto", WinningDAO.searchWinningNumbers(round).toString());
        model.put("winningLottoBonus", WinningDAO.searchWinningBonus(round));
    }

    private static void addResult(Map<String, Object> model, int round) throws SQLException {
        List<Integer> resultNumbers = ResultDAO.searchResultNumbers(round);
        for (int i = 0; i < resultNumbers.size(); i++) {
            model.put(RANK.get(i), resultNumbers.get(i));
        }
        model.put("resultMoney", ResultDAO.searchTotalMoney(round));
        model.put("resultRate", ResultDAO.searchRateOfReturn(round));
    }
}
