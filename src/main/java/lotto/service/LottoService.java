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

    private LottoService() {}

    private static class LottoServiceHolder {
        static final LottoService LOTTO_SERVICE = new LottoService();
    }

    public static LottoService getInstance() {
        return LottoServiceHolder.LOTTO_SERVICE;
    }

    public LottosDTO.Create createLottos(List<String> manualLottoNumbers) {
        int countOfPurchase = roundDAO.findAmountByRound(roundDAO.findMaxRound()) / PRICE_PER_LOTTO;
        List<Lotto> lottos = LottoFactory.createLottos(manualLottoNumbers, countOfPurchase);

        lottoDAO.addLottos(lottos, roundDAO.findMaxRound());

        return new LottosDTO.Create(lottos);
    }

    public LottosDTO.Create findLottosByRound(int round) {
        List<Lotto> lottos = lottoDAO.findLottosByRound(round);
        return new LottosDTO.Create(lottos);
    }
}
