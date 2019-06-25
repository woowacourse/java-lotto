package lotto.service;

import lotto.dao.LottoPurchaseDAO;
import lotto.dao.LottoRoundDAO;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.Numbers;
import lotto.domain.purchase.PurchaseCount;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LottoPurchaseService {
    private LottoPurchaseDAO lottoPurchaseDAO ;
    private LottoRoundDAO lottoRoundDAO;

    public LottoPurchaseService(Connection connection) {
        lottoRoundDAO = new LottoRoundDAO(connection);
        lottoPurchaseDAO = new LottoPurchaseDAO(connection);
    }

    public void saveLottos(PurchaseCount purchaseCount, String[] manualLotto) throws SQLException {
        List<Numbers> numbers = Arrays.stream(manualLotto)
                .map(lotto -> new Numbers(lotto))
                .collect(toList());

        Lottos lottos = Lottos.of(purchaseCount, numbers);

        lottoPurchaseDAO.savePurchaseLotto(lottos.getLottos(), lottoRoundDAO.totalRound());
    }

    public Lottos inquireLottos() throws SQLException {
        return Lottos.of(lottoPurchaseDAO.inquireLotto(lottoRoundDAO.totalRound()));
    }

    public Lottos inquireLottos(int round) throws SQLException {
        return Lottos.of(lottoPurchaseDAO.inquireLotto(round));
    }
}
