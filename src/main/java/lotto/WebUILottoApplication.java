package lotto;

import lotto.dao.LottoDao;
import lotto.dao.RoundDao;
import lotto.dao.WinPrizeDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.*;
import lotto.service.LottoService;
import lotto.utils.Encoder;
import lotto.view.ResultFormat;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final String DELIMITER = "\r\n";
    private static final int SAVE_FAIL = 0;

    public static void main(String[] args) {
        port(8080);

        externalStaticFileLocation("src/main/resources/templates");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("round", latestRound() + 1);
            model.put("rounds", new RoundDao().findAll());
            return render(model, "start.html");
        });

        get("/round", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int round = Integer.parseInt(req.queryParams("round"));
            WinPrize winPrize = new WinPrizeDao().findByRound(round);

            model.put("results", getResultsForm(winPrize));
            model.put("rateOfProfit", winPrize.getRateOfProfit());
            model.put("round", round);
            model.put("lottos", new LottoDao().findByRound(round));
            model.put("winningLotto", new WinningLottoDao().findByRound(round));
            return render(model, "round.html");
        });

        post("/buyLotto", (req, res) -> {
            RoundDao roundDao = new RoundDao();
            roundDao.add();
            int round = roundDao.getLatest();

            Money money = Money.from(req.queryParams("money"));
            List<String> manualLottos = convertList(req.queryParams("manualLottos"));
            List<Lotto> userLottos = LottoService.generateLottos(manualLottos, money);
            saveLottos(userLottos, round);

            int countOfManual = manualLottos.size();
            int countOfAuto = money.getCountOfPurchase() - countOfManual;
            res.redirect("/show?countOfManual=" + countOfManual + "&countOfAuto=" + countOfAuto);
            return null;
        });

        get("/show", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int round = latestRound();
            List<Lotto> lottos = new LottoDao().findByRound(round);

            model.put("lottos", lottos);
            model.put("countOfAuto", req.queryParams("countOfAuto"));
            model.put("countOfManual", req.queryParams("countOfManual"));
            return render(model, "show.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String lotto = req.queryParams("winningLotto");
            int bonusNo = Integer.parseInt(req.queryParams("bonusNo"));
            int round = latestRound();
            WinningLotto winningLotto = LottoService.generateWinningLotto(lotto, bonusNo);

            saveWinningLotto(round, winningLotto);
            saveWinPrize(round, winningLotto);

            res.redirect("result");
            return null;
        });

        get("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int round = latestRound();

            WinPrize winPrize = new WinPrizeDao().findByRound(round);
            List<String> results = getResultsForm(winPrize);
            model.put("results", results);
            model.put("rateOfProfit", winPrize.getRateOfProfit());
            return render(model, "result.html");
        });

        get("/error", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", req.queryParams("message"));
            return render(model, "error.html");
        });

        exception(Exception.class, (exception, req, res) -> {
            String message = null;
            try {
                message = Encoder.encodeUTF8(exception.getMessage());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            res.redirect("/error?message=" + message);
        });
    }

    private static List<String> getResultsForm(final WinPrize winPrize) {
        return ResultFormat.format(winPrize);
    }

    private static void saveLottos(final List<Lotto> userLottos, final int round) throws SQLException {
        LottoDao lottoDao = new LottoDao();
        if (!lottoDao.add(userLottos, round)) {
            throw new SQLException("로또 저장 에러");
        }
    }

    private static void saveWinningLotto(final int round, final WinningLotto winningLotto) throws SQLException {
        WinningLottoDao winningLottoDao = new WinningLottoDao();
        if (winningLottoDao.add(winningLotto, round) == SAVE_FAIL) {
            throw new SQLException("우승로또 저장 에러");
        }
    }

    private static void saveWinPrize(final int round, final WinningLotto winningLotto) throws SQLException {
        List<Lotto> userLottos = new LottoDao().findByRound(round);
        WinPrize winPrize = LottoService.generateWinPrize(userLottos, winningLotto);
        WinPrizeDao winPrizeDao = new WinPrizeDao();
        if (winPrizeDao.add(winPrize, round) == SAVE_FAIL) {
            throw new SQLException("우승상금 저장 에러");
        }
    }

    private static int latestRound() {
        return new RoundDao().getLatest();
    }

    private static List<String> convertList(final String manualLottos) {
        String[] results = manualLottos.split(DELIMITER);
        if (results[0].equals("")) {
            return new ArrayList<>();
        }
        return Arrays.asList(results);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
