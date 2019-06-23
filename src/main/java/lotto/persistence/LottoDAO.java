package lotto.persistence;

import lotto.domain.Lottos;

public interface LottoDAO {
    static LottoDAO getInstance() {
        return LottoDAOImpl.getInstance();
    }

    void addLotto(int roundId, Lottos lottos);

    Lottos findLottosByRoundId(int roundId);
}
