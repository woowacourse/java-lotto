package lotto.service;

import lotto.dao.LottoDao;
import lotto.domain.Lotto;

import java.util.List;

public class LottoService {
    private final LottoDao lottoDao;

    public LottoService(final LottoDao lottoDao) {
        this.lottoDao = lottoDao;
    }

    public void save(final List<Lotto> lottos, final int round) {
        lottoDao.add(lottos, round);
    }


    public List<Lotto> getByRound(final int round) {
        return lottoDao.findAllByRound(round);
    }
}
