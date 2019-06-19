package lotto.domain;

import lotto.dao.*;
import lotto.domain.creator.AutoLottoCreator;
import lotto.domain.creator.CreatorFactory;
import lotto.domain.creator.LottoCreator;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.util.CustomStringUtils;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static int ADD_ONE_BEFORE_ADD_LOTTO_GAME = 1;
    private static int ADD_ONE_FOR_INCLUDE_LIMIT_NUMBER = 1;

    public static void main(String[] args) {
        staticFiles.location("/");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String message = "안녕하세요! 로또를 구매하시겠습니까? 로또 결과를 조회하시겠습니까?";

            model.put("message", message);

            return render(model, "home.html");
        });

        get("/money", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            LottoGameDAO lottoGameDAO = new LottoGameDAO(DBConnector.getInstance());
            String message = String.format("%d회차 로또를 구매하러 오셨군요~ 구입 금액을 입력해주세요.",
                                        lottoGameDAO.getLastRound() + ADD_ONE_BEFORE_ADD_LOTTO_GAME);

            model.put("message", message);

            return render(model, "paymoney.html");
        });

        post("/money", (req, res) -> {
            LottoGameDAO lottoGameDAO = new LottoGameDAO(DBConnector.getInstance());
            MoneyDAO moneyDAO = new MoneyDAO(DBConnector.getInstance());
            Money money = new Money(req.queryParams("money"));
            int round = lottoGameDAO.getLastRound() + ADD_ONE_BEFORE_ADD_LOTTO_GAME;
            System.out.println(round);

            lottoGameDAO.addLottoGame(round);
            moneyDAO.addMoney(money, round);

            res.redirect("/manual-quantity");

            return "";
        });

        get("/manual-quantity", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            LottoGameDAO lottoGameDAO = new LottoGameDAO(DBConnector.getInstance());
            MoneyDAO moneyDAO = new MoneyDAO(DBConnector.getInstance());

            Money money = moneyDAO.findByRound(lottoGameDAO.getLastRound());
            String message = String.format("총 %d장의 로또를 구매할 수 있습니다. 이중 수동으로 구매할 로또의 수 입력해주세요.", money.getBuyableLottoQuantity());

            model.put("message", message);

            return render(model, "inputmanualquantity.html");
        });

        post("/manual-quantity", (req, res) -> {
            String inputQuantity = req.queryParams("manual-quantity");
            CustomStringUtils.checkIsBlank(inputQuantity);
            int manualQuantity = CustomStringUtils.parseInt(inputQuantity);

            LottoGameDAO lottoGameDAO = new LottoGameDAO(DBConnector.getInstance());
            MoneyDAO moneyDAO = new MoneyDAO(DBConnector.getInstance());
            LottoDAO lottoDAO = new LottoDAO(DBConnector.getInstance());
            int round = lottoGameDAO.getLastRound();

            Money money = moneyDAO.findByRound(round);
            money.checkIsBuyableLottoQuantity(manualQuantity);
            LottoCreator creator = new AutoLottoCreator();

            int autoQuantity = money.getBuyableLottoQuantity() - manualQuantity;

            for (int i = 0; i < autoQuantity; i++) {
                lottoDAO.addLotto(creator.createLotto(), round);
            }

            res.redirect(String.format("/manual-numbers?quantity=%s", inputQuantity));

            return "";
        });

        get("/manual-numbers", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String quantity = req.queryParams("quantity");
            String message = String.format("수동으로 구매할 로또 숫자들을 입력해주세요. %s장 산다고 했으니 %s개 입력해야겠죠?", quantity, quantity);
            List<Integer> iterator = IntStream.range(1, Integer.parseInt(quantity) + ADD_ONE_FOR_INCLUDE_LIMIT_NUMBER)
                                    .boxed().collect(Collectors.toList());

            model.put("quantity", quantity);
            model.put("message", message);
            model.put("iterator", iterator);

            return render(model, "inputmanualnumbers.html");
        });

        post("/manual-numbers", (req, res) -> {
            LottoGameDAO lottoGameDAO = new LottoGameDAO(DBConnector.getInstance());
            int round = lottoGameDAO.getLastRound();

            int manualQuantity = Integer.parseInt(req.queryParams("quantity"));

            List<String> numbers = new ArrayList<>();

            for (int i = 1; i < manualQuantity + ADD_ONE_FOR_INCLUDE_LIMIT_NUMBER; i++) {
                numbers.add(req.queryParams(String.format("manual-lotto-numbers-%d", i)));
            }

            CreatorFactory factory = new CreatorFactory(numbers);
            List<LottoCreator> creators = factory.createCreators(manualQuantity, 0);
            Lottos lottos = LottoFactory.createLottos(creators);

            LottoDAO lottoDAO = new LottoDAO(DBConnector.getInstance());

            for (Lotto lotto : lottos.getLottos()) {
                lottoDAO.addLotto(lotto, round);
            }

            res.redirect("/winning");

            return "";
        });

        get("/winning", (req, res) -> {
            LottoGameDAO lottoGameDAO = new LottoGameDAO(DBConnector.getInstance());
            LottoDAO lottoDAO = new LottoDAO(DBConnector.getInstance());

            Map<String, Object> model = new HashMap<>();

            int round = lottoGameDAO.getLastRound();
            List<Lotto> lottos = lottoDAO.findByRound(round);
            long manualQuantity = lottos.stream().filter(lotto -> lotto.getIsAuto() == false).count();
            long autoQuantity = lottos.stream().filter(lotto -> lotto.getIsAuto() == true).count();

            String message = String.format("수동으로 %d개, 자동으로 %d개를 구매했습니다. 이번주 당첨 번호와 보너스 볼을 입력해주세요.", manualQuantity, autoQuantity);

            model.put("round", round);
            model.put("lottos", lottos);
            model.put("message", message);

            return render(model, "inputwinningnumbers.html");
        });

        post("/winning", (req, res) -> {
            LottoGameDAO lottoGameDAO = new LottoGameDAO(DBConnector.getInstance());
            WinningLottoDAO winningLottoDAO = new WinningLottoDAO(DBConnector.getInstance());
            LottoDAO lottoDAO = new LottoDAO(DBConnector.getInstance());
            LottoResultDAO lottoResultDAO = new LottoResultDAO(DBConnector.getInstance());
            int round = lottoGameDAO.getLastRound();

            WinningLotto winningLotto = new WinningLotto(req.queryParams("winning-numbers"), req.queryParams("winning-bonus-numbers"));
            winningLottoDAO.addWinningLotto(winningLotto, round);
            LottosResult lottosResult = new LottosResult(winningLotto, new Lottos(lottoDAO.findByRound(round)));
            lottoResultDAO.addLottoResult(lottosResult, round);

            res.redirect("/result");

            return "";
        });

        get("/result", (req, res) -> {
            LottoGameDAO lottoGameDAO = new LottoGameDAO(DBConnector.getInstance());
            LottoDAO lottoDAO = new LottoDAO(DBConnector.getInstance());
            WinningLottoDAO winningLottoDAO = new WinningLottoDAO(DBConnector.getInstance());
            int round = lottoGameDAO.getLastRound();

            Map<String, Object> model = new HashMap<>();

            LottosResult result = new LottosResult(winningLottoDAO.findByRound(round), new Lottos(lottoDAO.findByRound(round)));

            List<Integer> values = new ArrayList<>();
            for (Rank rank : Rank.values()) {
                values.add(result.valueOf(rank));
            }

            String message = "두구두구 당신의 당첨 결과는??";
            model.put("round", round);
            model.put("values", values);
            model.put("result", result);
            model.put("message", message);

            return render(model, "reportresult.html");
        });

        get("/lookup", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            LottoGameDAO lottoGameDAO = new LottoGameDAO(DBConnector.getInstance());
            String message = String.format("조회하고 싶은 회차를 입력해주세요. 현재 최대 %d회차까지 진행되었습니다.", lottoGameDAO.getLastRound());

            model.put("message", message);

            return render(model, "lookupresult.html");
        });

        post("/lookup", (req, res) -> {
            res.redirect(String.format("/report?round=%s", req.queryParams("round")));

            return "";
        });

        get("/report", (req, res) -> {
            int round = CustomStringUtils.parseInt(req.queryParams("round"));

            Map<String, Object> model = new HashMap<>();

            LottoDAO lottoDAO = new LottoDAO(DBConnector.getInstance());
            WinningLottoDAO winningLottoDAO = new WinningLottoDAO(DBConnector.getInstance());

            List<Lotto> lottos = lottoDAO.findByRound(round);
            WinningLotto winningLotto = winningLottoDAO.findByRound(round);
            LottosResult result = new LottosResult(winningLotto, new Lottos(lottos));

            List<Integer> values = new ArrayList<>();
            for (Rank rank : Rank.values()) {
                values.add(result.valueOf(rank));
            }

            String message = String.format("%d회차 로또 당첨 결과입니다.", round);
            model.put("round", round);
            model.put("lottos", lottos);
            model.put("winning", winningLotto);
            model.put("values", values);
            model.put("result", result);
            model.put("message", message);

            return render(model, "reportlookup.html");
        });

        exception(Exception.class, (exception, request, response) -> {
            response.body(String.format("<script>alert('%s'); history.back();</script>", exception.getMessage(), request.pathInfo()));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
