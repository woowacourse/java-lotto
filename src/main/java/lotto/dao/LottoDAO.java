package lotto.dao;

import lotto.domain.lotto.Lotto;

import java.util.List;

public interface LottoDAO {
    List<Lotto> findByRound(int round);

    int addLotto(Lotto lotto, int round);

    int deleteLotto(int round);
}
