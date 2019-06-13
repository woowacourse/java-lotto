package lotto.service;

import lotto.dao.LottoDao;
import lotto.domain.Lotto;

import java.sql.SQLException;
import java.util.List;

public class LottoService {
    private final LottoDao lottoDao;

    public LottoService(final LottoDao lottoDao) {
        this.lottoDao = lottoDao;
    }

    public void save(final List<Lotto> lottos, final int round) throws SQLException {
        if (!lottoDao.add(lottos, round)) {
            throw new SQLException("로또 DB 저장 에러");
        }
    }


    public List<Lotto> getByRound(final int round) {
        return lottoDao.findAllByRound(round);
    }
}
