package lotto.controller;

import lotto.model.*;
import lotto.model.creator.lotto.AutoLottoCreatorStrategy;
import lotto.model.creator.lotto.LottoCreatorStrategy;
import lotto.model.creator.lotto.ManualLottoCreatorStrategy;
import lotto.model.creator.lottos.AutoLottosCreatorStrategty;
import lotto.model.creator.lottos.LottosCreator;
import lotto.model.creator.lottos.ManualLottosCreatorStrategy;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
        public static void main(String[] args) {
                staticFiles.location("/static");

                post("/", home());
                post("/payment", pay());
                post("/manual-lotto-number", manualNumber());
                post("/purchase-lottos", purchaseLottos());
                post("/winStatsAndYield", result());
                exception(Exception.class, error());
        }

        private static Route result() {
                return (req, res) -> {
                        Map<String, Object> model = new HashMap<>();
                        WinningInfo winningInfo = createWinningInfo(req);

                        WinStats winStats = new WinStats(req.session().attribute("lottos"), winningInfo);
                        req.session().attribute("winStats", winStats);
                        Yield yield = new Yield(new Payment(5000), winStats);
                        req.session().attribute("yield", yield);

                        model.put("winStats", winStats.getMappingStats().values());
                        model.put("yield", yield.getRate());

                        return render(model, "winStatsAndYield.html");
                };
        }

        private static WinningInfo createWinningInfo(Request req) {
                Lotto winningLotto = new Lotto(req.queryParams("winningLotto").split(", "));
                req.session().attribute("winningLotto", winningLotto);
                BonusBall bonusBall = new BonusBall(Integer.parseInt(req.queryParams("bonusBall")));
                req.session().attribute("bonusball", bonusBall);
                return new WinningInfo(winningLotto, bonusBall);
        }

        private static Route home() {
                return (req, res)->{
                        Map<String, Object> model = new HashMap<>();

                        req.session().invalidate();

                        return render(model, "index.html");
                };
        }

        private static Route pay() {
                return (req, res) -> {
                        Map<String, Object> model = new HashMap<>();

                        int payment = Integer.parseInt(req.queryParams("payment"));
                        req.session(true);
                        req.session().attribute("payment", new Payment(payment));

                        model.put("max-manual-payment-number", payment / 1000);
                        return render(model, "manual-lotto-number.html");
                };
        }

        private static Route manualNumber() {
                return (req, res) -> {
                        Map<String, Object> model = new HashMap<>();

                        int manualPurchaseNumber = Integer.parseInt(req.queryParams("manual-lotto-number"));
                        req.session().attribute("manualPurchaseNumber", new ManualPurchaseNumber(manualPurchaseNumber, req.session().attribute("payment")));

                        model.put("manual-lotto-number", manualPurchaseNumber);
                        return render(model, "manual-lottos.html");
                };
        }

        private static Route purchaseLottos() {
                return (req, res) -> {
                        Map<String, Object> model = new HashMap<>();

                        List<Lotto> lottos = createLottos(req);
                        req.session().attribute("lottos", lottos);

                        model.put("lottos", lottos);
                        return render(model, "purchase-lottos.html");
                };
        }

        private static List<Lotto> createLottos(Request req) {
                List<LottoCreatorStrategy> manualLottoCreatorStrategies = new ArrayList<>();

                String[] inputs = req.queryParams("manual-lottos").split("\r\n");
                for (String input : inputs) {
                        manualLottoCreatorStrategies.add(new ManualLottoCreatorStrategy(input.split(", ")));
                }
                //수동
                LottosCreator lottosCreator = new LottosCreator(new ManualLottosCreatorStrategy(manualLottoCreatorStrategies));
                List<Lotto> lottos = new ArrayList<>(lottosCreator.create());
                //자동
                lottosCreator = new LottosCreator(new AutoLottosCreatorStrategty(AutoLottoCreatorStrategy.getInstance(), req.session().attribute("payment"), req.session().attribute("manualPurchaseNumber")));
                lottos.addAll(lottosCreator.create());
                return lottos;
        }

        private static ExceptionHandler<Exception> error() {
                return (exception, request, response) -> {
                        Map<String, Object> model = new HashMap<>();
                        model.put("message", exception.getMessage());
                        response.body(render(model, "error.html"));
                };
        }



        private static String render(Map<String, Object> model, String templatePath) {
                return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
        }
}
