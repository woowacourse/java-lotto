package lotto.controller;

import lotto.dao.LottoDAO;
import lotto.dao.LottoDAOImpl;
import lotto.domain.Money;
import lotto.domain.creator.AutoLottoCreator;
import lotto.domain.creator.CreatorFactory;
import lotto.domain.creator.LottoCreator;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoFactory;
import lotto.domain.lotto.Lottos;
import lotto.domain.util.CustomStringUtils;
import spark.Request;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private static int ADD_ONE_FOR_INCLUDE_LIMIT_NUMBER = 1;

    private static LottoDAO lottoDAO = LottoDAOImpl.getInstance();

    public static void addAutoLottos(String quantity) {
        Money money = MoneyController.getMoney();

        int manualQuantity = CustomStringUtils.parseInt(quantity);
        money.checkIsBuyableLottoQuantity(manualQuantity);

        int autoQuantity = money.getBuyableLottoQuantity() - manualQuantity;
        int round = LottoGameController.getLastRound();

        LottoCreator creator = new AutoLottoCreator();

        for (int i = 0; i < autoQuantity; i++) {
            lottoDAO.addLotto(creator.createLotto(), round);
        }
    }

    public static void addManualLottos(Request req, String quantity) {
        int manualQuantity = CustomStringUtils.parseInt(quantity);
        int round = LottoGameController.getLastRound();

        List<String> numbers = new ArrayList<>();

        for (int i = 1; i < manualQuantity + ADD_ONE_FOR_INCLUDE_LIMIT_NUMBER; i++) {
            numbers.add(req.queryParams(String.format("manual-lotto-numbers-%d", i)));
        }

        CreatorFactory factory = new CreatorFactory(numbers);
        List<LottoCreator> creators = factory.createCreators(manualQuantity, 0);
        Lottos lottos = LottoFactory.createLottos(creators);

        for (Lotto lotto : lottos.getLottos()) {
            lottoDAO.addLotto(lotto, round);
        }
    }

    public static List<Lotto> getBoughtLottos() {
        return lottoDAO.findByRound(LottoGameController.getLastRound());
    }

    public static List<Lotto> getBoughtLottos(int round) {
        return lottoDAO.findByRound(round);
    }
}
