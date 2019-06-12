package lotto;

import lotto.dao.*;
import lotto.domain.*;
import lotto.service.DBAccessor;
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

    private static Lottos lottos;
    private static Integer round;

    public static void main(String[] args) throws SQLException {
        staticFiles.location("/static");
        Connection connection = DBManager.getConnection();
        DBManager.startTransection(connection);

        get("/show", (req, res) -> {
            List<String> rounds = new ArrayList<>();
            RoundDAO roundDAO = new RoundDAO(connection);
            for (Integer i = 1; i <= roundDAO.getCurrentRound(); i++) {
                rounds.add(i.toString());
            }

            return render(getRoundsModel(rounds), "show.html");
        });

        get("/showLottoInfo", (req, res) -> {
            return render(getLottoInfoModel(connection, req.queryParams("lottoRound")),
                    "showLottoInfo.html");
        });

        post("/winningLotto", (req, res) -> {
            Money money = new Money(Integer.parseInt(req.queryParams("money")));
            LottoCount lottoCount = new LottoCount(money, Integer.parseInt(req.queryParams("numberOfManualLotto")));
            List<String> inputManualLottoNumbers = Arrays.asList(req.queryParams("manualLottoNumbers").split(DELIMITER));
            LottosFactory lottosFactory = new LottosFactory(inputManualLottoNumbers, lottoCount);
            Lottos currentLottos = lottosFactory.generateTotalLottos();
            setLottos(currentLottos);
            round = DBAccessor.getNextRound(connection);
            DBAccessor.loadDBRoundTable(connection, round);
            DBAccessor.loadDBLottoTable(connection, round, lottos);

            return render(getLottoModel(lottoCount, currentLottos), "winningLotto.html");
        });

        post("/lottoResult", (req, res) -> {
            WinningLotto winningLotto = new WinningLotto(new Lotto(ConvertLottoNumber.run(req.queryParams("winningNumbers"))),
                    LottoNumber.getInstance(Integer.parseInt(req.queryParams("bonusNumber"))));
            LottoResult lottoResult = new LottoResult(winningLotto, lottos);

            DBAccessor.loadDBWinningLottoTable(connection, round, winningLotto);
            DBAccessor.loadDBLottoResultTable(connection, round, lottoResult);

            DBManager.endTransection(connection);

            return render(getLottoResultModel(lottoResult), "lottoResult.html");
        });

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            // Handle the exception here
            StringBuilder errorPageInfo = new StringBuilder();
            response.body(errorPageInfo.append("에러:").append(exception.getMessage()).append("<br/><br/>")
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
        model.put("lottos", DBAccessor.getDBLottos(connection, lottoRound));
        model.put("winningLotto", DBAccessor.getDBWinningLotto(connection, lottoRound));
        model.put("lottoResult", DBAccessor.getDBLottoResultDTO(connection, lottoRound));
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

    private static void setLottos(Lottos currentLottos) {
        lottos = currentLottos;
    }
}