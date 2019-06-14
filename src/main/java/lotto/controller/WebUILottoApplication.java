package lotto.controller;

import lotto.domain.lotto.*;
import lotto.domain.lotto.db.LottosDBHandler;
import lotto.domain.lotto.db.WinningLottoDBhandler;
import lotto.domain.lotto.dto.PrizeInfoDTO;
import lotto.domain.money.Money;
import lotto.domain.money.Prize;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {

    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/history", (req, res) -> {
            WinningLottoDBhandler winningLottoDBhandler = new WinningLottoDBhandler();

            int recentId = winningLottoDBhandler.getRecentId();
            List<String> buttonIds = createNames(recentId, "");

            Map<String, Object> model = new HashMap<>();
            model.put("buttonIds", buttonIds);
            model.put("empty", buttonIds.isEmpty());

            return render(model, "history.html");
        });

        get("/history_specific", (req, res) -> {
            int round = Integer.parseInt(req.queryParams("round_number"));
            WinningLottoDBhandler winningLottoDBhandler = new WinningLottoDBhandler();
            LottosDBHandler lottosDBHandler = new LottosDBHandler();
            WinningLotto winningLotto = winningLottoDBhandler.foundById(round);
            Lottos lottos = lottosDBHandler.foundById(round);
            LottoResult lottoResult = LottoResult.create
                    (Money.create(lottos.size() * Lotto.PRICE), lottos.getPrizes(winningLotto));


            Map<String, Object> model = new HashMap<>();
            List<PrizeInfoDTO> prizeInfoDTOs = createPrizeInfoDTOs(lottoResult);
            model.put("prizeInfos", prizeInfoDTOs);
            model.put("profitRate", lottoResult.getProfitRate());
            model.put("lottos", lottos.getLottosList());
            model.put("winningLotto", winningLotto.getWinningLotto());
            model.put("bonusNumber", winningLotto.getBonusNumber());
            model.put("profit", lottoResult.getProfit());
            model.put("expense", lottos.size() * Lotto.PRICE);
            model.put("round", round);

            return render(model, "history_specific.html");
        });

        post("/manual", (req, res) -> {
            Money money = Money.create(Integer.parseInt(req.queryParams("money")));
            LottoCount manualLottoCount = LottoCount.create(
                    Integer.parseInt(req.queryParams("manualLottoCount")), money);
            List<String> manualLottoTagNames = createNames(manualLottoCount.size(), "manualLotto");

            Map<String, Object> model = new HashMap<>();
            model.put("manualLottoTagNames", manualLottoTagNames);

            req.session().attribute("money", money);
            req.session().attribute("manualLottoCount", manualLottoCount.size());

            return render(model, "manual.html");
        });

        post("/lottos", (req, res) -> {
            Money money = req.session().attribute("money");
            int manualLottoCount = req.session().attribute("manualLottoCount");

            List<Lotto> manualLottos = generateManualLottos(req, manualLottoCount);
            Lottos lottos = LottoMachine
                    .generateLottos(manualLottos, money);

            Map<String, Object> model = new HashMap<>();
            model.put("lottos", lottos.getLottosList());
            model.put("manualLottoCount", manualLottoCount);
            model.put("automaticLottoCount", lottos.getLottosList().size()
                    - manualLottoCount);

            req.session().attribute("lottos", lottos);

            return render(model, "lottos.html");
        });

        post("/result", (req, res) -> {
            List<String> tokens = Arrays.asList(req.queryParams("winningLotto").split(","));
            List<Integer> winningLottoNumbers = convertTokensToNumbers(tokens);

            WinningLotto winningLotto = WinningLotto.create(winningLottoNumbers,
                    Integer.parseInt(req.queryParams("bonusNumber")));
            Money money = req.session().attribute("money");
            Lottos lottos = req.session().attribute("lottos");
            LottoResult lottoResult = LottoResult.create(money, lottos.getPrizes(winningLotto));

            WinningLottoDBhandler winningLottoDBhandler = new WinningLottoDBhandler();
            LottosDBHandler lottosDBHandler = new LottosDBHandler();

            lottosDBHandler.add(lottos, winningLottoDBhandler.getRecentId() + 1);
            winningLottoDBhandler.add(winningLotto);

            Map<String, Object> model = new HashMap<>();

            List<PrizeInfoDTO> prizeInfoDTOs = createPrizeInfoDTOs(lottoResult);
            model.put("prizeInfos", prizeInfoDTOs);
            model.put("profitRate", lottoResult.getProfitRate());
            return render(model, "result.html");
        });

        internalServerError((res, req) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("error", true);
            return render(model, "index.html");
        });
    }

    private static List<PrizeInfoDTO> createPrizeInfoDTOs(LottoResult lottoResult) {
        List<PrizeInfoDTO> prizeInfoDTOs = new ArrayList<>();
        for (Prize prize : Prize.values()) {
            prizeInfoDTOs.add(new PrizeInfoDTO(prize.getMatchCount()
                    , prize.getPrizeMoney(), lottoResult.getCount(prize)));
        }
        return prizeInfoDTOs;
    }

    private static List<String> createNames(int count, String name) {
        List<String> tagNames = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            tagNames.add(name + i);
        }
        return tagNames;
    }

    private static List<Lotto> generateManualLottos(Request req, int manualLottoCount) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 1; i <= manualLottoCount; i++) {
            List<String> tokens = Arrays.asList(req.queryParams("manualLotto" + i).split(","));
            List<Integer> lottoNumbers = convertTokensToNumbers(tokens);
            manualLottos.add(new Lotto(lottoNumbers));
        }
        return manualLottos;
    }

    private static List<Integer> convertTokensToNumbers(List<String> tokens) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < tokens.size(); i++) {
            numbers.add(Integer.parseInt(tokens.get(i).trim()));
        }
        return numbers;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
