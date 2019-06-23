package lotto.dao;

import lotto.domain.UserLotto;

import java.util.List;

public interface LottoDao {

    void addLotto(UserLotto userLotto, int round);

    int offerMaxRound();

    List<String> offerUserLottoNumber(int lottoRound);
}
