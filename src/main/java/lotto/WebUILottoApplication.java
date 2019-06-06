package lotto;

import lotto.dao.LottoDao;
import lotto.dao.RoundDao;
import lotto.dao.WinPrizeDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.*;
import lotto.service.LottoService;
import lotto.view.ResultFormat;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.*;

import static spark.Spark.*;

public class WebUILottoApplication {
    private static final String DELIMITER = "\n";
    private static final int SAVE_FAIL = 0;

    public static void main(String[] args) {
        port(8080);

        externalStaticFileLocation("src/main/resources/templates");

        get("/index", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("round", getRound() + 1);
            return render(model, "index.html");
        });

        post("/buyLotto", (req, res) -> {
            try {
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
            } catch (Exception e) {
                String message = encodeUTF8(e.getMessage());
                res.redirect("/error?message=" + message);
            }
            return null;
        });

        get("/show", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int round = getRound();
            List<Lotto> lottos = new LottoDao().findByRound(round);

            model.put("lottos", lottos);
            model.put("countOfAuto", req.queryParams("countOfAuto"));
            model.put("countOfManual", req.queryParams("countOfManual"));
            return render(model, "show.html");
        });

        post("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                String lotto = req.queryParams("winningLotto");
                int bonusNo = Integer.parseInt(req.queryParams("bonusNo"));
                int round = getRound();
                WinningLotto winningLotto = LottoService.generateWinningLotto(lotto, bonusNo);

                saveWinningLotto(round, winningLotto);
                saveWinPrize(round, winningLotto);

                res.redirect("result");
            } catch (Exception e) {
                String message = encodeUTF8(e.getMessage());
                res.redirect("/error?message=" + message);
            }

            return null;
        });

        get("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int round = getRound();

            WinPrize winPrize = new WinPrizeDao().findByRound(round);
            List<String> results = new ArrayList<>();
            for (final Rank rank : Rank.values()) {
                results.add(ResultFormat.format(rank, winPrize));
            }
            model.put("results", results);
            model.put("rateOfProfit", winPrize.getRateOfProfit());
            return render(model, "result.html");
        });

        get("/error", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("message", req.queryParams("message"));
            return render(model, "error.html");
        });
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

    private static int getRound() {
        return new RoundDao().getLatest();
    }

    private static List<String> convertList(final String manualLottos) {
        String[] results = manualLottos.split(DELIMITER);
        if (results[0].equals("")) {
            return new ArrayList<>();
        }
        return Arrays.asList(results);
    }

    private static String encodeUTF8(final String message) throws UnsupportedEncodingException {
        return URLEncoder.encode(message, "UTF-8");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
