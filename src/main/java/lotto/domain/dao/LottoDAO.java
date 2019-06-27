package lotto.domain.dao;

import lotto.domain.dto.LottoDTO;

import java.util.List;

public interface LottoDAO {

    static LottoDAOImpl getInstance() {
        return LottoDAOImpl.getInstance();
    }

    void addLotto(final LottoDTO lottoDTO);

    List<LottoDTO> getPurchasedLotto(final int round);

    void deleteLotto(final int round);
}


