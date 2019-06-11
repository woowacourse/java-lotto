package lotto;

import lotto.dao.*;
import lotto.domain.*;
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

    public static void main(String[] args) {
        staticFiles.location("/static");
        Connection connection = DBManager.getConnection();

        get("/show", (req, res) -> {
            List<String> rounds = new ArrayList<>();
            RoundDAO roundDAO = new RoundDAO(connection);
            for (Integer i = 1; i <= roundDAO.getCurrentRound(); i++) {
                rounds.add(i.toString());
            }

            Map<String, Object> model = new HashMap<>();
            model.put("rounds", rounds);
            return render(model, "show.html");
        });

        get("/showLottoInfo", (req, res) -> {
            String lottoRound = req.queryParams("lottoRound");

            LottoDAO lottoDAO = new LottoDAO(connection);
            WinningLottoDAO winningLottoDAO = new WinningLottoDAO(connection);
            LottoResultDAO lottoResultDAO = new LottoResultDAO(connection);
            Lottos lottos = lottoDAO.findByLottoRound(lottoRound);
            WinningLotto winningLotto = winningLottoDAO.findByLottoRound(lottoRound);
            LottoResultDTO lottoResultDTO = lottoResultDAO.findByLottoRound(lottoRound);

            Map<String, Object> model = new HashMap<>();
            model.put("lottoRound", lottoRound);
            model.put("lottos", lottos);
            model.put("winningLotto", winningLotto);
            model.put("lottoResult", lottoResultDTO);
            return render(model, "showLottoInfo.html");
        });

        post("/winningLotto", (req, res) -> {
            Money money = new Money(Integer.parseInt(req.queryParams("money")));
            LottoCount lottoCount = new LottoCount(money, Integer.parseInt(req.queryParams("numberOfManualLotto")));
            List<String> inputManualLottoNumbers = Arrays.asList(req.queryParams("manualLottoNumbers").split(DELIMITER));
            LottosFactory lottosFactory = new LottosFactory(inputManualLottoNumbers, lottoCount);
            Lottos currentLottos = lottosFactory.generateTotalLottos();
            setLottos(currentLottos);
            loadRoundTable(connection);
            loadLottoTable(connection);

            Map<String, Object> model = new HashMap<>();
            model.put("lottos", currentLottos);
            model.put("AutoCount", lottoCount.getAutoCount());
            model.put("ManualCount", lottoCount.getManualCount());
            return render(model, "winningLotto.html");
        });

        post("/lottoResult", (req, res) -> {
            WinningLotto winningLotto = new WinningLotto(new Lotto(ConvertLottoNumber.run(req.queryParams("winningNumbers"))),
                    LottoNumber.getInstance(Integer.parseInt(req.queryParams("bonusNumber"))));
            loadWinningLottoTable(connection, winningLotto);
            LottoResult lottoResult = new LottoResult(winningLotto, lottos);
            loadLottoResultTable(connection, lottoResult);

            Map<String, Object> model = new HashMap<>();
            model.put("first", lottoResult.getCountOfRank(FIRST));
            model.put("second", lottoResult.getCountOfRank(SECOND));
            model.put("third", lottoResult.getCountOfRank(THIRD));
            model.put("fourth", lottoResult.getCountOfRank(FOURTH));
            model.put("fifth", lottoResult.getCountOfRank(FIFTH));
            model.put("totalEarningRate", lottoResult.getEarningsRate());
            return render(model, "lottoResult.html");
        });

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            // Handle the exception here
            StringBuilder errorPageInfo = new StringBuilder();
            response.body(errorPageInfo.append("에러:").append(exception.getMessage()).append("<br/><br/>")
                    .append("<input type=\"button\" value=\"뒤로가기\" onclick=\"history.back(-1);\">").toString());
        });
    }

    private static void loadRoundTable(Connection connection) throws SQLException {
        RoundDAO roundDAO = new RoundDAO(connection);
        round = roundDAO.getNextRound();
        roundDAO.addRound(round.toString());
    }

    private static void loadLottoTable(Connection connection) throws SQLException {
        LottoDAO lottoDAO = new LottoDAO(connection);
        lottoDAO.addLottos(round.toString(), lottos);
    }

    private static void loadWinningLottoTable(Connection connection, WinningLotto winningLotto)
            throws SQLException {
        WinningLottoDAO winningLottoDAO = new WinningLottoDAO(connection);
        winningLottoDAO.addWinningLotto(round.toString(), winningLotto);
    }

    private static void loadLottoResultTable(Connection connection, LottoResult lottoResult)
            throws SQLException {
        LottoResultDAO lottoResultDAO = new LottoResultDAO(connection);
        lottoResultDAO.addLottoResult(round.toString(), lottoResult);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static void setLottos(Lottos currentLottos) {
        lottos = currentLottos;
    }
}