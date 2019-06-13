package lotto;

import lotto.domain.*;
import lotto.domain.dao.LottoDao;
import lotto.domain.dao.ResultDao;
import lotto.domain.dao.RoundDao;
import lotto.domain.dao.WinningLottoDao;
import lotto.domain.dto.ResultDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates/js");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            RoundDao roundDAO = new RoundDao();
            List<Integer> rounds = IntStream.range(1, roundDAO.getMaxRound() + 1)
                    .boxed()
                    .collect(Collectors.toList());
            model.put("rounds", rounds);
            return render(model, "index.html");
        });

        post("/manualLotto", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int price = Integer.parseInt(req.queryParams("price"));
            int manualCount = Integer.parseInt(req.queryParams("manualCount"));
            LottoMoney lottoMoney = new LottoMoney(price);

            List<String> manualLottos = new ArrayList<>();
            for (int i = 0; i < manualCount; i++) {
                manualLottos.add(req.queryParams("lotto" + i));
            }

            Lottos totalLottos = new Lottos(manualLottos, lottoMoney.getCountOfTicket());

            RoundDao roundDAO = new RoundDao();
            roundDAO.addNextRound();
            int maxRound = roundDAO.getMaxRound();

            LottoDao lottoDAO = new LottoDao();
            lottoDAO.addTotalLottos(maxRound, totalLottos);
            req.session().attribute("totalLottos", totalLottos);

            model.put("autoCount", lottoMoney.getCountOfTicket() - manualCount);
            model.put("manualCount", manualCount);
            model.put("lottos", totalLottos);
            return render(model, "manualLotto.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String input = req.queryParams("winningLotto");
            int bonusNum = Integer.parseInt(req.queryParams("bonusBall"));
            WinningLotto winningLotto = new WinningLotto(input, bonusNum);
            LottoResult lottoResult = new LottoResult(req.session().attribute("totalLottos"), winningLotto);
            Map<Rank, Integer> winners = lottoResult.getWinners();

            WinningLottoDao winningLottoDAO = new WinningLottoDao();
            ResultDao resultDAO = new ResultDao();
            RoundDao roundDAO = new RoundDao();
            int maxRound = roundDAO.getMaxRound();
            winningLottoDAO.addWinningLotto(maxRound, winningLotto);
            ResultDto resultDTO = new ResultDto(winners, lottoResult.getYield(), lottoResult.getTotalWinningMoney());
            resultDAO.addResult(maxRound, resultDTO);
            for (Rank rank : Rank.values()) {
                model.put(rank.name(), winners.get(rank));
            }
            model.put("yield", resultDAO.findYieldByRound(maxRound) * 100);
            return render(model, "result.html");
        });

        get("/error", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", req.queryParams("message"));
            return render(model, "error.html");
        });

        post("/searchRound", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int currentRound = Integer.parseInt(req.queryParams("round_selector"));
            model.put("round", currentRound);
            LottoDao lottoDAO = new LottoDao();
            ResultDao resultDAO = new ResultDao();
            WinningLottoDao winningLottoDAO = new WinningLottoDao();

            List<Lotto> lottos = lottoDAO.findLottoByRound(currentRound);
            Map<Rank, Integer> winners = resultDAO.findWinnerCountByRound(currentRound);
            List<Integer> winningLotto = winningLottoDAO.findWinningLottoByRound(currentRound);
            int bonusNum = winningLottoDAO.findBonusNumByRound(currentRound);
            double yield = resultDAO.findYieldByRound(currentRound);
            long winPrize = resultDAO.findWinPrizeByRound(currentRound);

            model.put("lottos", lottos);
            for (Rank rank : Rank.values()) {
                model.put(rank.name(), winners.get(rank));
            }
            model.put("winningLotto", winningLotto);
            model.put("bonusNum", bonusNum);
            model.put("yield", yield * 100);
            model.put("winPrize", winPrize);

            return render(model, "round.html");
        });

        exception(Exception.class, (e, req, res) -> {
            try {
                res.redirect("/error?message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
            } catch (UnsupportedEncodingException e1) {
                res.redirect("/error?message=" + "URL Encoding Error");
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
