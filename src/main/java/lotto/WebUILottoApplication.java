package lotto;

import lotto.domain.LottoTickets;
import lotto.service.LottoTicketService;
import lotto.service.RoundService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
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

                Map<String, Object> viewModel = lottoTicketService.lottoBuy(inputMoney, manualAmount);

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

                Map<String, Object> model = lottoTicketService.showLotto(inputManuals, manualAmount, lottoMoney);

                req.session().attribute(LOTTO_TICKETS.type(), model.get(LOTTO_TICKETS.type()));

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

                Map<String, Object> model = lottoTicketService.showResult(inputWinningLotto, inputBonusBall, lottoTickets, inputMoney);

                return render(model, "showResult.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        get("/select/round", (req, res) -> {
            try {
                Map<String, Object> model = roundService.getRound();
                return render(model, "selectRound.html");
            } catch (Exception e) {
                e.printStackTrace();
                res.redirect("error.html");
                return "error";
            }
        });

        get("/show/round", (req, res) -> {
            String round = req.queryParams(ROUND.type());
            Map<String, Object> model = roundService.getLottoResults(round);
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

