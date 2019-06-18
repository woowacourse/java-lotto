package lotto.controller;

import lotto.domain.*;
import lotto.domain.DAO.ResultDAO;
import lotto.domain.DAO.UserLottoDAO;
import lotto.domain.DAO.WinningLottoDAO;
import lotto.domain.DTO.LottoesDTO;
import lotto.domain.DTO.ResultDTO;
import lotto.service.LottoService;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    public static void main(String[] args) {
        staticFiles.externalLocation("src/main/resources/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "form.html");
        });

        post("/main", (req, res) -> {
            Map<String, Object> resultModel = new HashMap<>();
            Money money = LottoService.createMoney(req.queryParams("money"));
            CustomLottoCount customLottoCount = LottoService.createCustomLottoCount(req.queryParams("customLottoCount"
            ), money);
            req.session().attribute("money", money);
            req.session().attribute("customLottoCount", customLottoCount.getCustomLottoCount());
            resultModel.put("customLottoCount", customLottoCount);
            resultModel.put("customLottoNumbers", customLottoCount.getCustomLottoCountInOrder());
            if (customLottoCount.getCustomLottoCount() == 0) {
                res.redirect("/winning?autoLottoCount=" + money.getSize());
            }
            return render(resultModel, "result.html");
        });

        get("/winning", (req, res) -> {
            Lottoes lottoes = LottoFactory.createOnlyAutoLottoes(Integer.parseInt(req.queryParams("autoLottoCount")));
            LottoService.InsertUserLottoNumbers(lottoes.getLottoes());
            Map<String, Object> model = printLottoNumbers(req, lottoes);
            return render(model, "winning.html");
        });

        post("/winning", (req, res) -> {
            Lottoes lottoes = LottoService.createLottoes(req.session().attribute("money"), req.queryParamsValues(
                    "customLottoNumbers"));
            LottoService.InsertUserLottoNumbers(lottoes.getLottoes());
            Map<String, Object> model = printLottoNumbers(req, lottoes);
            return render(model, "winning.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<String> winnigNumberInput = Arrays.asList(req.queryParams("winningNumber").split(","));
            String bonusBall = req.queryParams("bonusBall");
            WinningLotto winningLotto = LottoService.createWinningLotto(winnigNumberInput, bonusBall);
            Calculator calculator = CalculatorFactory.createResult();
            Lottoes lottoes = req.session().attribute("lottoes");
            calculator.calculateResult(lottoes, winningLotto);

            for (Rank rank : Rank.values()) {
                if (rank == Rank.NONE) {
                    continue;
                }
                model.put(rank.name(), new ResultDTO(rank, calculator));
            }
            Money money = req.session().attribute("money");
            model.put("rate", calculator.getRate(money));
            LottoService.InsertWinningLottoInfoData(bonusBall, winningLotto);
            LottoService.InsertResultData(calculator, money);
            List<Integer> rounds = getRounds();
            model.put("round", rounds);
            return render(model, "finalResult.html");
        });

        post("/round", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int inquiredRound = Integer.parseInt(req.queryParams("roundNumber"));
            model.put("round", inquiredRound);

            LottoService.selectWholeResultByCurrentRound(model, inquiredRound);
            LottoService.selectWinningNumbersByCourrentRound(model, inquiredRound);
            LottoService.selectUserLottoNumbersByCurrentRound(model, inquiredRound);
            return render(model, "round.html");
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(404);
            response.body(exception.getMessage());
        });
    }

    private static Map<String, Object> printLottoNumbers(Request req, Lottoes lottoes) {
        Map<String, Object> model = new HashMap<>();
        LottoesDTO lottoesDTO = new LottoesDTO(lottoes.getLottoes());
        model.put("lottoes", lottoesDTO.getLottoes());
        req.session().attribute("lottoes", lottoes);
        return model;
    }

    private static List<Integer> getRounds() throws SQLException {
        List<Integer> rounds = new ArrayList<>();
        for (int i = 0; i < ResultDAO.getCurrentLottoRound(); i++) {
            rounds.add(i + 1);
        }
        return rounds;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
