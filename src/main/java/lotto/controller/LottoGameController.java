package lotto.controller;

import lotto.db.dao.LottoTicketDAO;
import lotto.db.dao.LottoGameDAO;
import lotto.domain.LottoTicket;
import lotto.domain.RankType;
import lotto.domain.WinStatistics;
import lotto.domain.WinningLotto;
import lotto.domain.dto.LottoGameResultDTO;
import lotto.service.LottoGameService;
import spark.Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.utils.WebUIRenderer.render;

public class LottoGameController {
    private static final int MONEY_OFFSET = 1000;

    public static Route index = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    };

    public static Route addWinningLotto = (req, res) -> {
        StringBuilder lottoNumbers = new StringBuilder();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.append(req.queryParams("num" + i)).append(",");
        }

        LottoGameService.addWinningLottoTicket(lottoNumbers.toString(), req.queryParams("bonusBall"));

        res.redirect("/win/result");
        return null;
    };

    public static Route latestGameResult = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        LottoGameResultDTO winningLotto = LottoGameDAO.findLatestWinningLotto();
        List<LottoTicket> lottoTickets = LottoTicketDAO.findLottosByLottoId(winningLotto.getWinningLottoId());
        WinStatistics winStatistics = new WinStatistics(lottoTickets, WinningLotto.of(winningLotto.getWinningNumbers(), winningLotto.getBonusBall()));

        model.put("week", winningLotto.getWinningLottoId());
        model.put("winningNumbers", winningLotto.getWinningNumbers().split(","));
        model.put("bonusBall", winningLotto.getBonusBall());
        model.put("results", getEachRank(winStatistics));
        model.put("money", lottoTickets.size() * MONEY_OFFSET);
        model.put("profit", winStatistics.getProfit());
        model.put("incoming_rate", String.format("%.2f", lottoTickets.size() > 0 ? winStatistics.calculateProfitRate(lottoTickets.size() * MONEY_OFFSET) : 0));

        return render(model, "lotto_result.html");
    };

    public static Route gameResultByWinningLottoId = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        LottoGameResultDTO winningLotto = LottoGameDAO.findByWinningLottoId(req.params(":week"));
        List<LottoTicket> lottoTickets = LottoTicketDAO.findLottosByLottoId(winningLotto.getWinningLottoId());
        WinStatistics winStatistics = new WinStatistics(lottoTickets, WinningLotto.of(winningLotto.getWinningNumbers(), winningLotto.getBonusBall()));

        model.put("week", winningLotto.getWinningLottoId());
        model.put("winningNumbers", winningLotto.getWinningNumbers().split(","));
        model.put("bonusBall", winningLotto.getBonusBall());
        model.put("results", getEachRank(winStatistics));
        model.put("money", lottoTickets.size() * MONEY_OFFSET);
        model.put("profit", winStatistics.getProfit());
        model.put("incoming_rate", String.format("%.2f", lottoTickets.size() > 0 ? winStatistics.calculateProfitRate(lottoTickets.size() * MONEY_OFFSET) : 0));

        return render(model, "lotto_result.html");
    };

    public static Route allWeeksOfGame = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        model.put("weeksOfGame", LottoGameDAO.findAllWinningLottoId());

        return render(model, "select_result.html");
    };

    private static List<String> getEachRank(WinStatistics winStatistics) {
        List<String> results = new ArrayList<>();
        for (RankType rankType : RankType.values()) {
            int matchingCount = rankType.getMatchingCount();
            int prize = rankType.getPrize();
            int count = winStatistics.getCountOfResult().get(rankType);

            if (rankType.equals(RankType.SECOND)) {
                results.add(String.format("%d개 일치, 보너스 볼 일치(%d원) - %d개", matchingCount, prize, count));
                continue;
            }
            if (rankType.equals(RankType.NOTHING)) {
                continue;
            }
            results.add(String.format("%d개 일치 (%d원) - %d개", matchingCount, prize, count));
        }
        return results;
    }
}
