package lotto.controller;

import lotto.domain.lotto.*;
import lotto.domain.lotto.db.ConnectionHandler;
import lotto.domain.lotto.db.dao.LottosDAO;
import lotto.domain.lotto.db.dao.WinningLottoDAO;
import lotto.domain.lotto.dto.PrizeInfoDTO;
import lotto.domain.money.Money;
import lotto.domain.money.Prize;
import spark.ModelAndView;
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
            WinningLottoDAO winningLottoDAO = new WinningLottoDAO
                    (new ConnectionHandler("lottoGame"));
            winningLottoDAO.create();
            int recentId = winningLottoDAO.getRecentId();
            List<String> buttonIds = new ArrayList<>();
            for (int i = 1; i <= recentId; i++) {
                buttonIds.add(String.valueOf(i));
            }
            winningLottoDAO.closeConnection();

            Map<String, Object> model = new HashMap<>();
            model.put("buttonIds", buttonIds);
            model.put("empty", buttonIds.isEmpty());

            return render(model, "history.html");
        });

        get("/history_specific", (req, res) -> {
            int round = Integer.parseInt(req.queryParams("round_number"));

            WinningLottoDAO winningLottoDAO = new WinningLottoDAO
                    (new ConnectionHandler("lottoGame"));
            LottosDAO lottosDAO = new LottosDAO
                    (new ConnectionHandler("lottoGame"));
            winningLottoDAO.create();
            lottosDAO.create();
            WinningLotto winningLotto = winningLottoDAO.foundById(round);
            Lottos lottos = lottosDAO.foundById(round);
            LottoResult lottoResult = LottoResult.create
                    (Money.create(lottos.size() * Lotto.PRICE), lottos.getPrizes(winningLotto));

            winningLottoDAO.closeConnection();

            Map<String, Object> model = new HashMap<>();
            List<PrizeInfoDTO> prizeInfoDTOs = new ArrayList<>();
            for (Prize prize : Prize.values()) {
                prizeInfoDTOs.add(new PrizeInfoDTO(prize.getMatchCount()
                        , prize.getPrizeMoney(), lottoResult.getCount(prize)));
            }
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

            List<String> manualLottoNames = new ArrayList<>();
            for (int i = 0; i < manualLottoCount.size(); i++) {
                manualLottoNames.add("manualLotto" + i);
            }

            Map<String, Object> model = new HashMap<>();
            model.put("manualLottoNames", manualLottoNames);

            req.session().attribute("money", money);
            req.session().attribute("manualLottoCount", manualLottoCount.size());

            return render(model, "manual.html");
        });

        post("/lottos", (req, res) -> {
            Money money = req.session().attribute("money");
            int manualLottoCount = req.session().attribute("manualLottoCount");

            List<Lotto> manualLottos = new ArrayList<>();
            for (int i = 0; i < manualLottoCount; i++) {
                List<Integer> lottoNumbers = new ArrayList<>();
                List<String> tokens = Arrays.asList(req.queryParams("manualLotto" + i).split(","));
                for (int j = 0; j < tokens.size(); j++) {
                    lottoNumbers.add(Integer.parseInt(tokens.get(j)));
                }
                manualLottos.add(new Lotto(lottoNumbers));
            }

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
            String[] tokens = req.queryParams("winningLotto").split(",");
            List<Integer> winningLottoNumbers = new ArrayList<>();
            for (String token : tokens) {
                winningLottoNumbers.add(Integer.parseInt(token));
            }
            WinningLotto winningLotto = WinningLotto.create(winningLottoNumbers,
                    Integer.parseInt(req.queryParams("bonusNumber")));
            Money money = req.session().attribute("money");
            Lottos lottos = req.session().attribute("lottos");
            LottoResult lottoResult = LottoResult.create(money, lottos.getPrizes(winningLotto));

            WinningLottoDAO winningLottoDAO = new WinningLottoDAO(new ConnectionHandler("lottoGame"));
            winningLottoDAO.create();

            LottosDAO lottosDAO = new LottosDAO(new ConnectionHandler("lottoGame"));
            lottosDAO.create();
            lottosDAO.add(lottos, winningLottoDAO.getRecentId() + 1);
            winningLottoDAO.add(winningLotto);
            lottosDAO.closeConnection();
            winningLottoDAO.closeConnection();

            Map<String, Object> model = new HashMap<>();

            List<PrizeInfoDTO> prizeInfoDTOs = new ArrayList<>();
            for (Prize prize : Prize.values()) {
                prizeInfoDTOs.add(new PrizeInfoDTO(prize.getMatchCount()
                        , prize.getPrizeMoney(), lottoResult.getCount(prize)));
            }
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

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
