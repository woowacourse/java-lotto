package lotto.service;

import lotto.dao.LottosDao;
import lotto.dao.RoundDao;
import lotto.dao.WinningLottoDao;
import lotto.db.DatabaseConnection;
import lotto.domain.*;
import lotto.utils.Converter;
import lotto.utils.ResultMessage;
import lotto.utils.ViewUtils;
import spark.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoResultService {
    private static final String PRICE = "price";
    private static final String YIELD = "yield";
    private static final String LOTTO_RESULT = "userLottoResult";


    public static Route makeLottoResultPage = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        Lotto lotto = new Lotto(Converter.convertNumbers(req.queryParams("winningLotto")));
        LottoNumber bonusNo = LottoNumber.valueOf(Integer.parseInt(req.queryParams("bonusNumber")));
        WinningLotto winningLotto = new WinningLotto(lotto, bonusNo);
        Lottos lottos = req.session().attribute("lottos");
        LottoResult lottoResult = LottoResult.generateLottoResult(lottos, winningLotto);
        Price price = req.session().attribute(PRICE);
        model.put(YIELD, lottoResult.findYield(price.getPrice()));
        model.put(LOTTO_RESULT, ResultMessage.getResult(lottoResult, getRanks()));
        int round = req.session().attribute("round");

        addRoundInDB(round, price);
        addLottosInDB(round, lottos);
        addWinningLottoInDB(round, winningLotto);

        return ViewUtils.render(model, "result.html");
    };

    public static Route makeLottoResultByRoundPage = (req, res) -> {
        Connection conn = new DatabaseConnection().getConnection();
        RoundDao roundDao = new RoundDao(conn);
        LottosDao lottosDao = new LottosDao(conn);
        WinningLottoDao winningLottoDao = new WinningLottoDao(conn);
        Map<String, Object> model = new HashMap<>();

        int round = Integer.parseInt(req.queryParams("round"));
        Lottos lottos = lottosDao.findLottoByRound(round);
        WinningLotto winningLotto = winningLottoDao.findWinningLottoByRound(round);
        LottoResult lottoResult = LottoResult.generateLottoResult(lottos, winningLotto);

        model.put(YIELD, lottoResult.findYield(roundDao.findPriceByRound(round)));
        model.put(LOTTO_RESULT, ResultMessage.getResult(lottoResult, getRanks()));
        return ViewUtils.render(model, "result.html");
    };

    private static void addRoundInDB(int round, Price price) throws SQLException {
        Connection conn = new DatabaseConnection().getConnection();
        RoundDao roundDao = new RoundDao(conn);

        roundDao.addRound(round, price.getPrice());
    }

    private static void addLottosInDB(int round, Lottos lottos) throws SQLException {
        Connection conn = new DatabaseConnection().getConnection();
        LottosDao lottosDao = new LottosDao(conn);

        lottosDao.addLottos(round, lottos);
    }

    private static void addWinningLottoInDB(int round, WinningLotto winningLotto) throws SQLException {
        Connection conn = new DatabaseConnection().getConnection();
        WinningLottoDao winningLottoDao = new WinningLottoDao(conn);

        winningLottoDao.addWinningLotto(round, winningLotto);
    }

    private static List<Rank> getRanks() {
        List<Rank> ranks = new ArrayList<>();
        ranks.add(Rank.FIRST);
        ranks.add(Rank.SECOND);
        ranks.add(Rank.THIRD);
        ranks.add(Rank.FOURTH);
        ranks.add(Rank.FIFTH);
        return ranks;
    }
}
