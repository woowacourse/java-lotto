package lotto.controller;

import lotto.domain.DAO.DBUtil;
import lotto.domain.DAO.ResultDAO;
import lotto.domain.DAO.UserLottoDAO;
import lotto.domain.DAO.WinningLottoDAO;
import lotto.domain.DTO.LottoesDTO;
import lotto.domain.DTO.ResultDTO;
import lotto.Exception.InvalidCustomLottoNumberException;
import lotto.Exception.InvalidPurchaseException;
import lotto.Exception.InvalidWinningLottoException;
import lotto.InputValidator;
import lotto.domain.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.Connection;
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
        post("/winning", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String[] array = req.queryParamsValues("autoLottoNumbers");
            Money money = req.session().attribute("money");
            if (InputValidator.isNotValidCustomLottoes(array)) {
                throw new InvalidCustomLottoNumberException("올바른 수동로또 번호를 입력해 주세요.");
            }
            Lottoes lottoes = LottoFactory.createLottoes(array, money);
            LottoesDTO lottoesDTO = new LottoesDTO(lottoes.getLottoes());
            List<Lotto> lottoList = lottoes.getLottoes();
            insertUserLottoData(lottoList);
            model.put("lottoes", lottoesDTO.getLottoes());
            req.session().attribute("lottoes", lottoes);
            return render(model, "winning.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<String> winnigNumberInput = Arrays.asList(req.queryParams("winningNumber").split(","));
            String bonusBall = req.queryParams("bonusBall");
            Lotto lotto = LottoFactory.createLotto(winnigNumberInput);
            if (InputValidator.isNotValidLotto(winnigNumberInput)
                    || InputValidator.isNotValidWinningLotto(lotto, bonusBall)) {
                throw new InvalidWinningLottoException("올바른 당첨번호를 입력해 주세요.");
            }
            WinningLotto winningLotto = LottoFactory.createWinningLotto(lotto, Integer.parseInt(bonusBall));
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
            InsertWinningData(bonusBall, lotto);
            insertResultData(calculator, money);
            List<Integer> rounds = getRounds();
            model.put("round", rounds);
            return render(model, "finalResult.html");
        });

        post("/main", (req, res) -> {
            Map<String, Object> resultModel = new HashMap<>();
            String moneyInput = req.queryParams("money");
            String customLottoCountInput = req.queryParams("customLottoCount");

            if (InputValidator.isNotValidPrice(moneyInput)) {
                throw new InvalidPurchaseException("올바른 구매금액을 입력해주세요.");
            }
            Money money = MoneyFactory.createMoney(Integer.parseInt(moneyInput));
            if (InputValidator.isNotValidCustomLottoCount(customLottoCountInput, money)) {
                throw new InvalidPurchaseException("올바른 수동로또 개수를 입력해주세요.");
            }

            CustomLottoCount customLottoCount = CustomLottoCountFactory
                    .createCustomLottoCount(Integer.parseInt(customLottoCountInput), money);
            req.session().attribute("money", money);
            resultModel.put("customLottoCount", customLottoCount);
            List<Integer> customLottoCounts = new ArrayList<>();
            for (int i = 1; i <= customLottoCount.getCustomLottoCount(); i++) {
                customLottoCounts.add(i);
            }
            resultModel.put("autoLottoNumbers", customLottoCounts);
            return render(resultModel, "result.html");
        });

        post("/round", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int inquiredRound = Integer.parseInt(req.queryParams("roundNumber"));
            model.put("round", inquiredRound);

            ResultDAO.selectWholeResultByCurrentRound(model, inquiredRound);
            WinningLottoDAO.selectWholeResultByCurrentRound(model, inquiredRound);
            UserLottoDAO.selectUserLottoNumbersByCurrentRound(model, inquiredRound);
            return render(model, "round.html");
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(404);
            response.body(exception.getMessage());
        });
    }

    private static List<Integer> getRounds() throws SQLException {
        List<Integer> rounds = new ArrayList<>();
        for (int i = 0; i < ResultDAO.getCurrentLottoRound(); i++) {
            rounds.add(i + 1);
        }
        return rounds;
    }

    private static void insertResultData(Calculator calculator, Money money) throws SQLException {
        Connection con = DBUtil.getConnection();
        int currentRound = ResultDAO.getCurrentLottoRound() + 1;
        List<Integer> matchCounts = new ArrayList<>();

        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue;
            }
            matchCounts.add(calculator.getMatchlottoCountPerRank(rank));
        }

        ResultDAO.addResult(calculator.getWholeMoney(), matchCounts.toString(),
                calculator.getRate(money), currentRound);
        DBUtil.closeConnection(con);
    }

    private static void insertUserLottoData(List<Lotto> lottoList) throws SQLException {
        Connection con = DBUtil.getConnection();
        int currentRound = UserLottoDAO.getCurrentLottoRound() + 1;
        for (int i = 0; i < lottoList.size(); i++) {
            UserLottoDAO.addUserLottoNumbers(lottoList.get(i).toString(), currentRound);
        }
        DBUtil.closeConnection(con);
    }

    private static void InsertWinningData(String bonusBall, Lotto lotto) throws SQLException {
        Connection con = DBUtil.getConnection();
        int currentRound = WinningLottoDAO.getCurrentLottoRound() + 1;
        WinningLottoDAO.addWinningLottoInfo(currentRound, lotto.toString(), Integer.parseInt(bonusBall));
        DBUtil.closeConnection(con);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
