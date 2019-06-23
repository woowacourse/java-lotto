package lotto.service;

import lotto.dao.LottoDAO;
import lotto.dao.RoundDAO;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.dto.LottosDTO;

import java.util.List;

public class LottoService {
    private static final int PRICE_PER_LOTTO = 1_000;

    private final LottoDAO lottoDAO = LottoDAO.getInstance();
    private final RoundDAO roundDAO = RoundDAO.getInstance();

    public LottosDTO.Create createLottos(List<String> manualLottoNumbers) {
        int countOfPurchase = roundDAO.findAmountByRound(roundDAO.findMaxRound()) / PRICE_PER_LOTTO;
        List<Lotto> lottos = LottoFactory.createLottos(manualLottoNumbers, countOfPurchase);

        lottoDAO.addLottos(lottos);

        return new LottosDTO.Create(lottos);
    }

    public LottosDTO.Create findLottosByRound(int round) {
        List<Lotto> lottos = lottoDAO.findLottosByRound(round);
        return new LottosDTO.Create(lottos);
    }
}
