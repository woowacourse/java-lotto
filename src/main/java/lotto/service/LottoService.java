package lotto.service;


import lotto.dao.LottoDao;
import lotto.dao.RoundDao;
import lotto.dao.WinPrizeDao;
import lotto.dao.WinningLottoDao;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.WinPrize;
import lotto.domain.WinningLotto;
import lotto.view.ResultFormat;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.*;

import static lotto.WebUILottoApplication.render;

public class LottoService {
    private static final String DELIMITER = "\r\n";
    private static final int SAVE_FAIL = 0;

    private LottoService() {
    }

    public static Object addLottos(Request req, Response res) throws SQLException {
        RoundDao roundDao = new RoundDao();
        roundDao.add();
        int round = roundDao.getLatest();

        Money money = Money.from(req.queryParams("money"));
        List<String> manualLottos = ConvertToList(req.queryParams("manualLottos"));
        List<Lotto> userLottos = LottoHelper.generateLottos(manualLottos, money);
        saveLottos(userLottos, round);

        int countOfManual = manualLottos.size();
        int countOfAuto = money.getCountOfPurchase() - countOfManual;
        res.redirect("/lottos?countOfManual=" + countOfManual + "&countOfAuto=" + countOfAuto);
        return null;
    }

    private static List<String> ConvertToList(final String manualLottos) {
        String[] results = manualLottos.split(DELIMITER);
        if (results[0].equals("")) {
            return new ArrayList<>();
        }
        return Arrays.asList(results);
    }

    private static void saveLottos(final List<Lotto> userLottos, final int round) throws SQLException {
        LottoDao lottoDao = new LottoDao();
        if (!lottoDao.add(userLottos, round)) {
            throw new SQLException("로또 저장 에러");
        }
    }

    public static Object getLottos(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = latestRound();
        List<Lotto> lottos = new LottoDao().findByRound(round);

        model.put("lottos", lottos);
        model.put("countOfAuto", req.queryParams("countOfAuto"));
        model.put("countOfManual", req.queryParams("countOfManual"));
        return render(model, "showLottos.html");
    }

    public static Object doGetResult(Request req, Response res) throws SQLException {
        Map<String, Object> model = new HashMap<>();
        String lotto = req.queryParams("winningLotto");
        int bonusNo = Integer.parseInt(req.queryParams("bonusNo"));
        int round = latestRound();
        WinningLotto winningLotto = LottoHelper.generateWinningLotto(lotto, bonusNo);

        saveWinningLotto(round, winningLotto);
        saveWinPrize(round, winningLotto);

        res.redirect("result");
        return null;
    }

    private static void saveWinningLotto(final int round, final WinningLotto winningLotto) throws SQLException {
        WinningLottoDao winningLottoDao = new WinningLottoDao();
        if (winningLottoDao.add(winningLotto, round) == SAVE_FAIL) {
            throw new SQLException("우승로또 저장 에러");
        }
    }

    private static void saveWinPrize(final int round, final WinningLotto winningLotto) throws SQLException {
        List<Lotto> userLottos = new LottoDao().findByRound(round);
        WinPrize winPrize = LottoHelper.generateWinPrize(userLottos, winningLotto);
        WinPrizeDao winPrizeDao = new WinPrizeDao();
        if (winPrizeDao.add(winPrize, round) == SAVE_FAIL) {
            throw new SQLException("우승상금 저장 에러");
        }
    }

    public static Object doPostResult(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        int round = latestRound();

        WinPrize winPrize = new WinPrizeDao().findByRound(round);
        List<String> results = ResultFormat.format(winPrize);
        model.put("results", results);
        model.put("rateOfProfit", winPrize.getRateOfProfit());
        return render(model, "result.html");
    }

    private static int latestRound() {
        return new RoundDao().getLatest();
    }
}
