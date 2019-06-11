package lotto;

import lotto.dao.LottoTicketsDao;
import lotto.dao.RoundDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.LottoMoney;
import lotto.domain.LottoResults;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.domain.factory.LottoResultsFactory;
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

import static lotto.WebUILottoApplication.Type.*;
import static spark.Spark.*;

public class WebUILottoApplication {
    private static Connection connection = new DatabaseConnection().getConnection();

    public static void main(String[] args) {
        staticFileLocation("/static");

        get("/lottoBuy", (req, res) -> {
            try {
                Map<String, Object> viewModel = new HashMap<>();
                String inputMoney = req.queryParams(MONEY.type);
                String manualAmount = req.queryParams(MANUAL_AMOUNT.type);

                req.session(true);
                req.session().attribute(MONEY.type, inputMoney);
                req.session().attribute(MANUAL_AMOUNT.type, manualAmount);

                viewModel.put(MONEY.type, new LottoMoney(Long.parseLong(inputMoney)));
                viewModel.put(MANUAL_AMOUNT.type, Integer.parseInt(manualAmount));

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
                String inputManuals = req.queryParams(MANUAL_NUMBER.type);
                int manualAmount = Integer.parseInt(req.session().attribute(MANUAL_AMOUNT.type));
                LottoMoney lottoMoney = new LottoMoney(Integer.parseInt(req.session().attribute(MONEY.type)));
                List<String> manualLottoTickets = getManualLottoTickets(inputManuals);

                LottoTickets lottoTickets = LottoTicketsFactory.create(manualAmount, manualLottoTickets, lottoMoney);

                viewModel.put(LOTTO_TICKETS.type, lottoTickets);
                req.session().attribute(LOTTO_TICKETS.type, lottoTickets);

                return render(viewModel, "showLottos.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        post("/showResult", (req, res) -> {
            try {
                Map<String, Object> model = new HashMap<>();
                String inputWinningLotto = req.queryParams(WINNING_LOTTO.type);
                String inputBonusBall = req.queryParams(BONUS_BALL.type);

                WinningLotto winningLotto = WinningLottoFactory.create(inputWinningLotto, Integer.parseInt(inputBonusBall));
                //TODO 아래의 코드가 정상적으로 작동하는 이유 알아보기
                LottoTickets lottoTickets = req.session().attribute(LOTTO_TICKETS.type);
                LottoMoney money = new LottoMoney(Long.parseLong(req.session().attribute(MONEY.type)));
                LottoResults lottoResults = LottoResultsFactory.create(lottoTickets, winningLotto, money);

                model.put(LOTTO_TICKETS.type, lottoTickets);
                model.put(MONEY.type, new LottoMoney(Long.parseLong(req.session().attribute(MONEY.type))));
                model.put(WINNING_LOTTO.type, winningLotto);
                model.put(LOTTO_RESULTS.type, lottoResults);
                model.put(REWARD_MONEY.type, lottoResults.getYield());

                addDatabase(model, connection);
                return render(model, "showResult.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        get("selectRound", (req, res) -> {
            try {
                int round = getRound();
                Map<String, Object> model = new HashMap<>();
                model.put("round", round);
                return render(model, "selectRound.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        get("/showRounds", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int round = Integer.parseInt(req.queryParams(ROUND.type));
            int money = getMoney(round);
            WinningLotto winningLotto = getWinningLotto(round);
            LottoTickets lottoTickets = getLottoTickets(round);
            LottoMoney lottoMoney = new LottoMoney(money);
            LottoResults lottoResults = LottoResultsFactory.create(lottoTickets, winningLotto, lottoMoney);

            model.put(LOTTO_RESULTS.type, lottoResults);
            model.put(REWARD_MONEY.type, lottoResults.getYield());
            model.put("round", round);
            return render(model, "showRounds.html");
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

    private static int getRound() throws SQLException {
        RoundDao roundDao = new RoundDao(connection);
        return roundDao.findMaxRound();
    }

    private static int getMoney(int round) throws SQLException {
        RoundDao roundDao = new RoundDao(connection);
        return roundDao.findMoneyByRound(round);
    }

    private static LottoTickets getLottoTickets(int round) throws SQLException {
        LottoTicketsDao lottoTicketsDao = new LottoTicketsDao(connection);
        return lottoTicketsDao.findLottoByRound(round);
    }

    private static WinningLotto getWinningLotto(int round) throws SQLException {
        WinningLottoDao winningLottoDao = new WinningLottoDao(connection);
        return winningLottoDao.findWinningLottoByRound(round);
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
        LottoMoney lottoMoney = (LottoMoney) model.get(MONEY.type);
        LottoTickets lottoTickets = (LottoTickets) model.get(LOTTO_TICKETS.type);
        WinningLotto winningLotto = (WinningLotto) model.get(WINNING_LOTTO.type);

        addRoundToDB(lottoMoney, connection, round);
        addLottoTicketsToDB(lottoTickets, connection, round);
        addWinningLottoToDB(winningLotto, connection, round);
    }

    private static void addRoundToDB(LottoMoney lottoMoney, Connection connection, int round) throws SQLException {
        RoundDao roundDao = new RoundDao(connection);
        roundDao.addRound(round, lottoMoney);
    }

    private static void addWinningLottoToDB(WinningLotto winningLotto, Connection connection, int round) throws SQLException {
        WinningLottoDao winningLottoDao = new WinningLottoDao(connection);
        winningLottoDao.addWinningLotto(round, winningLotto);
    }

    private static void addLottoTicketsToDB(LottoTickets lottoTickets, Connection connection, int round) throws SQLException {
        LottoTicketsDao lottoTicketsDao = new LottoTicketsDao(connection);
        lottoTicketsDao.addLottoTickets(round, lottoTickets);
    }

    public enum Type {
        MONEY("money"),
        MANUAL_AMOUNT("manualAmount"),
        MANUAL_NUMBER("manualNumber"),
        LOTTO_TICKETS("lottoTickets"),
        WINNING_LOTTO("winningLotto"),
        BONUS_BALL("bonusBall"),
        LOTTO_RESULTS("lottoResults"),
        REWARD_MONEY("rewardMoney"),
        ROUND("round");

        private final String type;

        Type(String type) {
            this.type = type;
        }
    }
}

