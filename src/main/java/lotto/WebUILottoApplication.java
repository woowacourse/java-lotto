package lotto;

import lotto.domain.LottoMoney;
import lotto.domain.LottoResults;
import lotto.domain.LottoTickets;
import lotto.domain.WinningLotto;
import lotto.service.LottoTicketService;
import lotto.service.RoundService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import static lotto.ServiceMessage.*;
import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");

        Connection connection = new DatabaseConnection().getConnection();
        LottoTicketService lottoTicketService = new LottoTicketService(connection);
        RoundService roundService = new RoundService(connection);

        get("/buy/lotto", (req, res) -> {
            try {
                String inputMoney = req.queryParams(MONEY.type());
                String manualAmount = req.queryParams(MANUAL_AMOUNT.type());

                req.session(true);
                req.session().attribute(MONEY.type(), inputMoney);
                req.session().attribute(MANUAL_AMOUNT.type(), manualAmount);

                Map<String, Object> viewModel = new HashMap<>();
                viewModel.put(MONEY.type(), inputMoney);
                viewModel.put(MANUAL_AMOUNT.type(), manualAmount);

                return render(viewModel, "manual.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        post("/show/lotto", (req, res) -> {
            try {
                String inputManuals = req.queryParams(MANUAL_NUMBER.type());
                String manualAmount = req.session().attribute(MANUAL_AMOUNT.type());
                String lottoMoney = req.session().attribute(MONEY.type());
                LottoTickets lottoTickets = lottoTicketService.showLotto(inputManuals, manualAmount, lottoMoney);

                Map<String, Object> model = new HashMap<>();
                model.put(LOTTO_TICKETS.type(), lottoTickets);
                req.session().attribute(LOTTO_TICKETS.type(), lottoTickets);

                return render(model, "showLottos.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        post("/show/result", (req, res) -> {
            try {
                String inputWinningLotto = req.queryParams(WINNING_LOTTO.type());
                String inputBonusBall = req.queryParams(BONUS_BALL.type());
                LottoTickets lottoTickets = req.session().attribute(LOTTO_TICKETS.type());
                String inputMoney = req.session().attribute(MONEY.type());

                LottoMoney money = lottoTicketService.getMoney(inputMoney);
                WinningLotto winningLotto = lottoTicketService.getWinningLotto(inputWinningLotto, inputBonusBall);
                LottoResults results = lottoTicketService.getResults(lottoTickets, winningLotto, money);

                Map<String, Object> model = new HashMap<>();
                model.put(LOTTO_TICKETS.type(), lottoTickets);
                model.put(MONEY.type(), money);
                model.put(WINNING_LOTTO.type(), winningLotto);
                model.put(LOTTO_RESULTS.type(), results);
                model.put(REWARD_MONEY.type(), results.getYield());

                lottoTicketService.addDataBase(lottoTickets, money, winningLotto);
                return render(model, "showResult.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        get("/select/round", (req, res) -> {
            try {
                int round = roundService.getRound();
                Map<String, Object> model = new HashMap<>();
                model.put(ROUND.type(), round);
                return render(model, "selectRound.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        get("/show/round", (req, res) -> {
            String round = req.queryParams(ROUND.type());
            LottoResults lottoResults = roundService.getLottoResults(round);
            Map<String, Object> model = new HashMap<>();
            model.put(LOTTO_RESULTS.type(), lottoResults);
            model.put(REWARD_MONEY.type(), lottoResults.getYield());
            model.put(ROUND.type(), round);
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

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

