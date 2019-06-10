package lotto;

import lotto.domain.LottoMoney;
import lotto.domain.LottoResults;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.domain.dao.GameResultDao;
import lotto.domain.dao.LottoTicketsDao;
import lotto.domain.dao.RoundDao;
import lotto.domain.dao.WinningLottoDao;
import lotto.domain.factory.LottoTicketsFactory;
import lotto.domain.factory.WinningLottoFactory;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUILottoApplication {
    enum Type {
        MONEY("money"),
        MANUAL_AMOUNT("manualAmount"),
        MANUAL_NUMBER("manualNumber"),
        LOTTO_TICKETS("lottoTickets");

        private final String type;

        Type(String type) {
            this.type = type;
        }
    }

    private static Connection connection = new DatabaseConnection().getConnection();

    public static void main(String[] args) {
        staticFileLocation("/static");

        get("/lottoBuy", (req, res) -> {
            try {
                Map<String, Object> viewModel = new HashMap<>();
                String inputMoney = req.queryParams("money");
                String manualAmount = req.queryParams("manualAmount");

                req.session(true);
                req.session().attribute("inputMoney", inputMoney);
                req.session().attribute("manualAmount", manualAmount);

                viewModel.put("inputMoney", new LottoMoney(Long.parseLong(inputMoney)));
                viewModel.put("manualAmount", Integer.parseInt(manualAmount));

                return render(viewModel, "manual.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        post("/showLotto", (req, res) -> {
            try {
                Map<String, Object> viewModel = new HashMap<>();
                String inputManuals = req.queryParams("manualNumber");
                int manualAmount = Integer.parseInt(req.session().attribute("manualAmount"));
                LottoMoney lottoMoney = new LottoMoney(Integer.parseInt(req.session().attribute("inputMoney")));
                List<String> manualLottoTickets = getManualLottoTickets(inputManuals);

                LottoTickets lottoTickets = LottoTicketsFactory.create(manualAmount, manualLottoTickets, lottoMoney);

                viewModel.put("lottos", lottoTickets);
                req.session().attribute("lottoTickets", lottoTickets);

                return render(viewModel, "showLottos.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        post("/showResult", (req, res) -> {
            try {
                Map<String, Object> viewModel = new HashMap<>();
                String inputWinningLotto = req.queryParams("winningLotto");
                String inputBonusBall = req.queryParams("bonusBall");

                WinningLotto winningLotto = WinningLottoFactory.create(inputWinningLotto, Integer.parseInt(inputBonusBall));
                //TODO 아래의 코드가 정상적으로 작동하는 이유 알아보기
                LottoTickets lottoTickets = req.session().attribute("lottoTickets");
                LottoMoney money = new LottoMoney(Long.parseLong(req.session().attribute("inputMoney")));
                LottoResults lottoResults = new LottoResults(lottoTickets, winningLotto, money);

                viewModel.put("lottos", lottoTickets);
                viewModel.put("inputMoney", new LottoMoney(Long.parseLong(req.session().attribute("inputMoney"))));
                viewModel.put("winningLotto", winningLotto);
                viewModel.put("lottoResults", lottoResults);
                viewModel.put("rewardMoney", lottoResults.getYield());

                addDatabase(viewModel, connection);
                return render(viewModel, "showResult.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        get("/reset", (req, res) -> {
            res.redirect("index.html");
            return "error";
        });

        get("/error", (req, res) -> {
            res.redirect("error.html");
            return "error";
        });
    }

    private static List<String> getManualLottoTickets(String inputManuals) {
        return Arrays.asList(inputManuals.replaceAll("\r", "").split("\n"));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static int findThisRound() throws SQLException {
        Connection connection = new DatabaseConnection().getConnection();
        RoundDao roundDao = new RoundDao(connection);
        return roundDao.findMaxRound();
    }

    private static void addDatabase(Map<String, Object> model, Connection connection) throws SQLException {
        int round = findThisRound() + 1;
        addRoundToDB(model, connection, round);
        addLottoTicketsToDB(model, connection, round);
        addWinningLottoToDB(model, connection, round);
        GameResultDao gameResultDao = new GameResultDao(connection);
        LottoTickets lottos = (LottoTickets) model.get("lottos");
        WinningLotto winningLotto = (WinningLotto) model.get("winningLotto");
        LottoMoney inputMoney = (LottoMoney) model.get("inputMoney");
        gameResultDao.addGameResult(lottos, winningLotto, inputMoney);
    }

    private static void addWinningLottoToDB(Map<String, Object> model, Connection connection, int round) throws SQLException {
        WinningLottoDao winningLottoDao = new WinningLottoDao(connection);
        WinningLotto winningLotto = (WinningLotto) model.get("winningLotto");
        winningLottoDao.addWinningLotto(round, winningLotto);
    }

    private static void addLottoTicketsToDB(Map<String, Object> model, Connection connection, int round) throws SQLException {
        LottoTicketsDao lottoTicketsDao = new LottoTicketsDao(connection);
        LottoTickets lottos = (LottoTickets) model.get("lottos");
        lottoTicketsDao.addLottoTickets(round, lottos);
    }

    private static void addRoundToDB(Map<String, Object> model, Connection connection, int round) throws SQLException {
        RoundDao roundDao = new RoundDao(connection);
        LottoMoney inputMoney = (LottoMoney) model.get("inputMoney");
        roundDao.addRound(round, inputMoney);
    }

}

