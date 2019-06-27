package lotto;

import lotto.controller.*;
import lotto.domain.LottosResult;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.util.CustomStringUtils;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WebUILottoController {
    private static int ADD_ONE_BEFORE_ADD_LOTTO_GAME = 1;
    private static int ADD_ONE_FOR_INCLUDE_LIMIT_NUMBER = 1;

    public static Map<String, Object> getFutureRound() {
        Map<String, Object> model = new HashMap<>();
        model.put("round", LottoGameController.getLastRound() + ADD_ONE_BEFORE_ADD_LOTTO_GAME);
        return model;
    }

    public static String addGameAndMoney(Request req, Response res) {
        LottoGameController.addLottoGame();
        MoneyController.addMoney(req);

        res.redirect("/manual-quantity");
        return "";
    }

    public static Map<String, Object> getBuyableQuantity() {
        Money money = MoneyController.getMoney();

        Map<String, Object> model = new HashMap<>();
        model.put("totalQuantity", money.getBuyableLottoQuantity());
        return model;
    }

    public static String addAutoLottos(Request req, Response res) {
        String inputQuantity = req.queryParams("manual-quantity");
        CustomStringUtils.checkIsBlank(inputQuantity);
        LottoController.addAutoLottos(inputQuantity);

        res.redirect(String.format("/manual-numbers?quantity=%s", inputQuantity));
        return "";
    }

    public static Map<String, Object> getManualQuantity(Request req) {
        String quantity = req.queryParams("quantity");
        List<Integer> iterator = IntStream.range(1, Integer.parseInt(quantity) + ADD_ONE_FOR_INCLUDE_LIMIT_NUMBER)
                                          .boxed().collect(Collectors.toList());

        Map<String, Object> model = new HashMap<>();
        model.put("quantity", quantity);
        model.put("iterator", iterator);
        return model;
    }

    public static String addManualLottos(Request req, Response res) {
        String manualQuantity = req.queryParams("quantity");
        LottoController.addManualLottos(req, manualQuantity);

        res.redirect("/winning");
        return "";
    }

    public static Map<String, Object> getLottosInfo() {
        List<Lotto> lottos = LottoController.getBoughtLottos();
        long manualQuantity = lottos.stream().filter(lotto -> lotto.isAuto() == false).count();
        long autoQuantity = lottos.stream().filter(lotto -> lotto.isAuto() == true).count();

        Map<String, Object> model = new HashMap<>();
        model.put("round", LottoGameController.getLastRound());
        model.put("lottos", lottos);
        model.put("manualQuantity", manualQuantity);
        model.put("autoQuantity", autoQuantity);
        return model;
    }

    public static String addLottoResult(Request req, Response res) {
        LottoResultController.addLottoResult(req);

        res.redirect("/result");
        return "";
    }

    public static Map<String, Object> getResultInfo() {
        LottosResult result = LottoResultController.getLottoResult();

        List<Integer> values = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            values.add(result.valueOf(rank));
        }

        Map<String, Object> model = new HashMap<>();
        model.put("round", LottoGameController.getLastRound());
        model.put("values", values);
        model.put("result", result);
        return model;
    }

    public static Map<String, Object> getLastRound() {
        Map<String, Object> model = new HashMap<>();
        model.put("maxRound", LottoGameController.getLastRound());
        return model;
    }

    public static String deliverRoundInfo(Request req, Response res) {
        res.redirect(String.format("/report?round=%s", req.queryParams("round")));
        return "";
    }

    public static Map<String, Object> getAllGameInfo(Request req) {
        int round = CustomStringUtils.parseInt(req.queryParams("round"));

        List<Lotto> lottos = LottoController.getBoughtLottos(round);
        WinningLotto winningLotto = WinningLottoController.getWinningLotto(round);
        LottosResult result = new LottosResult(winningLotto, new Lottos(lottos));

        List<Integer> values = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            values.add(result.valueOf(rank));
        }

        Map<String, Object> model = new HashMap<>();
        model.put("round", round);
        model.put("lottos", lottos);
        model.put("winning", winningLotto);
        model.put("values", values);
        model.put("result", result);
        return model;
    }
}
