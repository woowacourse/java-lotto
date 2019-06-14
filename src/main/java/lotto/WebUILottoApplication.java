package lotto;

import lotto.dao.*;
import lotto.domain.*;
import lotto.service.*;
import lotto.util.ConvertLottoNumber;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static lotto.domain.Rank.*;
import static spark.Spark.*;

public class WebUILottoApplication {
    private final static String DELIMITER = "\r\n";

    public static void main(String[] args) {
        staticFiles.location("/static");
        Connection connection = DBManager.getConnection();

        get("/show", (req, res) -> {
            List<String> rounds = new ArrayList<>();
            for (Integer i = 1; i <= LottoInfoService.getCurrentRound(connection); i++) {
                rounds.add(i.toString());
            }

            return render(getRoundsModel(rounds), "show.html");
        });

        get("/lottosinfo", (req, res) -> {
            return render(getLottoInfoModel(connection, req.queryParams("lottoRound")),
                    "show_lottos_info.html");
        });

        post("/winninglotto", (req, res) -> {
            Money money = new Money(Integer.parseInt(req.queryParams("money")));
            LottoCount lottoCount = new LottoCount(money, Integer.parseInt(req.queryParams("numberOfManualLotto")));
            List<String> inputManualLottoNumbers = Arrays.asList(req.queryParams("manualLottoNumbers").split(DELIMITER));
            LottosFactory lottosFactory = new LottosFactory(inputManualLottoNumbers, lottoCount);
            Lottos currentLottos = lottosFactory.generateTotalLottos();
            Integer round = LottoPurchasingService.getNextRound(connection);

            // Set Session
            req.session(true);
            req.session().attribute("round", round);
            req.session().attribute("lottos", currentLottos);

            return render(getLottoModel(lottoCount, currentLottos), "winning_lotto.html");
        });

        post("/lottoresult", (req, res) -> {
            Integer round = req.session().attribute("round");
            Lottos lottos = req.session().attribute("lottos");
            WinningLotto winningLotto = new WinningLotto(new Lotto(ConvertLottoNumber.run(req.queryParams("winningNumbers"))),
                    LottoNumber.getInstance(Integer.parseInt(req.queryParams("bonusNumber"))));
            LottoResult lottoResult = new LottoResult(winningLotto, lottos);

            try {
                DBManager.startTransaction(connection);

                LottoPurchasingService.loadDBRoundTable(connection, round);
                LottoPurchasingService.loadDBLottoTable(connection, round, lottos);
                LottoResultService.loadDBWinningLottoTable(connection, round, winningLotto);
                LottoResultService.loadDBLottoResultTable(connection, round, lottoResult);

                DBManager.endTransaction(connection);
            } catch (SQLException e) {
                DBManager.rollbackTransaction(connection);
            }

            return render(getLottoResultModel(lottoResult), "lotto_result.html");
        });

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            // Handle the exception here
            StringBuilder errorPageInfo = new StringBuilder();
            response.body(errorPageInfo
                    .append("에러:")
                    .append(exception.getMessage())
                    .append("<br/><br/>")
                    .append("<input type=\"button\" value=\"뒤로가기\" onclick=\"history.back(-1);\">").toString());
        });
    }

    private static Map<String, Object> getRoundsModel(List<String> rounds) {
        Map<String, Object> model = new HashMap<>();
        model.put("rounds", rounds);
        return model;
    }

    private static Map<String, Object> getLottoInfoModel(Connection connection, String lottoRound) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        model.put("lottoRound", lottoRound);
        model.put("lottos", LottoInfoService.getDBLottos(connection, lottoRound));
        model.put("winningLotto", LottoInfoService.getDBWinningLotto(connection, lottoRound));
        model.put("lottoResult", LottoInfoService.getDBLottoResultDTO(connection, lottoRound));
        return model;
    }

    private static Map<String, Object> getLottoModel(LottoCount lottoCount, Lottos currentLottos) {
        Map<String, Object> model = new HashMap<>();
        model.put("lottos", currentLottos);
        model.put("AutoCount", lottoCount.getAutoCount());
        model.put("ManualCount", lottoCount.getManualCount());
        return model;
    }

    private static Map<String, Object> getLottoResultModel(LottoResult lottoResult) {
        Map<String, Object> model = new HashMap<>();
        model.put("first", lottoResult.getCountOfRank(FIRST));
        model.put("second", lottoResult.getCountOfRank(SECOND));
        model.put("third", lottoResult.getCountOfRank(THIRD));
        model.put("fourth", lottoResult.getCountOfRank(FOURTH));
        model.put("fifth", lottoResult.getCountOfRank(FIFTH));
        model.put("totalEarningRate", lottoResult.getEarningsRate());
        return model;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}