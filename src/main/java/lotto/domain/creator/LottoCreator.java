package lotto.domain.creator;

import lotto.domain.lotto.Lotto;

import java.util.List;

public interface LottoCreator {
    List<Lotto> createLottos(int lottoQuantity);
}
