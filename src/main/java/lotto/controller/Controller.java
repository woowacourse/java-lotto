package lotto.controller;

import lotto.model.*;
import lotto.model.creator.lotto.AutoLottoCreatorStrategy;
import lotto.model.creator.lotto.LottoCreatorStrategy;
import lotto.model.creator.lotto.ManualLottoCreatorStrategy;
import lotto.model.creator.lottos.AutoLottosCreatorStrategty;
import lotto.model.creator.lottos.LottosCreator;
import lotto.model.creator.lottos.ManualLottosCreatorStrategy;
import lotto.model.dao.LottoDAO;
import lotto.model.dao.ResultDAO;
import lotto.model.dao.RoundInfoDAO;
import lotto.model.dto.ResultDTO;
import lotto.model.dto.RoundInfoDTO;
import lotto.service.LottoService;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
        public static Route home() {
                return (req, res) -> {
                        Map<String, Object> model = new HashMap<>();
                        return render(model, "index.html");
                };
        }

        public static Route roundResult() {
                return (req, res)->{
                        Map<String, Object> model = new HashMap<>();

                        List<Integer> ids = RoundInfoDAO.getInstance().selectIds();
                        model.put("ids", ids);

                        return render(model, "round-result.html");
                };
        }

        public static Route eachResult() {
                return (req, res) -> {
                        Map<String, Object> model = new HashMap<>();

                        int id = Integer.parseInt(req.queryParams("id"));
                        List<String> lottos = LottoDAO.getInstance().selectLottos(id);
                        WinningInfo winningInfo = RoundInfoDAO.getInstance().selectWinningInfo(id);
                        ResultDTO resultDTO = ResultDAO.getInstance().selectResultDTO(id);

                        model.put("lottos", lottos);
                        model.put("winningLotto", winningInfo.getWinningLotto().toString());
                        model.put("bonusball", winningInfo.getBonusBall().getLottoNumber().getNumber());
                        model.put("first", resultDTO.getWinningResult().get(LottoRank.FIRST));
                        model.put("second", resultDTO.getWinningResult().get(LottoRank.SECOND));
                        model.put("third", resultDTO.getWinningResult().get(LottoRank.THIRD));
                        model.put("fourth", resultDTO.getWinningResult().get(LottoRank.FOURTH));
                        model.put("fifth", resultDTO.getWinningResult().get(LottoRank.FIFTH));
                        model.put("none", resultDTO.getWinningResult().get(LottoRank.NONE));
                        model.put("revenue", resultDTO.getRevenue());
                        model.put("yield", resultDTO.getYield());

                        return render(model, "result.html");
                };
        }

        public static Route pay() {
                return (req, res) -> {
                        Map<String, Object> model = new HashMap<>();

                        int payment = Integer.parseInt(req.queryParams("payment"));
                        req.session(true);
                        req.session().attribute("payment", new Payment(payment));

                        model.put("max-manual-payment-number", payment / 1000);
                        return render(model, "manual-lotto-number.html");
                };
        }

        public static Route manualNumber() {
                return (req, res) -> {
                        Map<String, Object> model = new HashMap<>();

                        int manualPurchaseNumber = Integer.parseInt(req.queryParams("manual-lotto-number"));
                        req.session().attribute("manualPurchaseNumber", new ManualPurchaseNumber(manualPurchaseNumber, req.session().attribute("payment")));

                        model.put("manual-lotto-number", manualPurchaseNumber);
                        return render(model, "manual-lottos.html");
                };
        }

        public static Route purchaseLottos() {
                return (req, res) -> {
                        Map<String, Object> model = new HashMap<>();

                        List<Lotto> lottos = createLottos(req);
                        req.session().attribute("lottos", lottos);

                        model.put("lottos", lottos);
                        return render(model, "purchase-lottos.html");
                };
        }

        private static List<Lotto> createLottos(Request req) {
                LottosCreator lottosCreator;
                List<Lotto> lottos = new ArrayList<>();
                ManualPurchaseNumber manualPurchaseNumber = req.session().attribute("manualPurchaseNumber");
                if (manualPurchaseNumber.getNumber() > 0) {
                        lottos = createManual(req);
                }
                //자동
                lottosCreator = new LottosCreator(new AutoLottosCreatorStrategty(AutoLottoCreatorStrategy.getInstance(), req.session().attribute("payment"), req.session().attribute("manualPurchaseNumber")));
                lottos.addAll(lottosCreator.create());
                return lottos;
        }

        private static List<Lotto> createManual(Request req) {
                LottosCreator lottosCreator;
                List<Lotto> lottos;
                List<LottoCreatorStrategy> manualLottoCreatorStrategies = new ArrayList<>();

                String[] inputs = req.queryParams("manual-lottos").split("\r\n");
                for (String input : inputs) {
                        manualLottoCreatorStrategies.add(new ManualLottoCreatorStrategy(input.split(", ")));
                }
                //수동
                lottosCreator = new LottosCreator(new ManualLottosCreatorStrategy(manualLottoCreatorStrategies));
                lottos = new ArrayList<>(lottosCreator.create());
                return lottos;
        }

        public static Route result() {
                return (req, res) -> {
                        Map<String, Object> model = new HashMap<>();

                        Lotto winningLotto = new Lotto(req.queryParams("winningLotto").split(", "));
                        BonusBall bonusBall = new BonusBall(Integer.parseInt(req.queryParams("bonusBall")));
                        WinningInfo winningInfo = new WinningInfo(winningLotto, bonusBall);

                        WinStats winStats = new WinStats(req.session().attribute("lottos"), winningInfo);

                        Payment payment = req.session().attribute("payment");
                        Yield yield = new Yield(payment, winStats);

                        ManualPurchaseNumber manualPurchaseNumber = req.session().attribute("manualPurchaseNumber");
                        RoundInfoDTO roundInfoDTO = new RoundInfoDTO(payment.getAmount(), manualPurchaseNumber.getNumber(), winningLotto.toString(), bonusBall.getLottoNumber().getNumber());
                        RoundInfoDAO.getInstance().insertRoundInfo(roundInfoDTO);

                        LottoService.insertResult(new ResultDTO(winStats.getMappingStats(), yield.getTotalRevenue(), yield.getRate()));
                        List<Lotto> lottos = req.session().attribute("lottos");
                        LottoService.insertLottos(lottos);

                        model.put("winStats", winStats.getMappingStats().values());
                        model.put("yield", yield.getRate());
                        return render(model, "winStatsAndYield.html");
                };
        }

        public static String render(Map<String, Object> model, String templatePath) {
                return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
        }

        public static ExceptionHandler<Exception> error() {
                return (exception, request, response) -> {
                        Map<String, Object> model = new HashMap<>();
                        model.put("message", exception.getMessage());
                        response.body(render(model, "error.html"));
                };
        }
}
