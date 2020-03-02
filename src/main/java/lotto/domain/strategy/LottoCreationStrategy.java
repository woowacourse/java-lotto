package lotto.domain.strategy;

import lotto.domain.Lotto;

import java.util.List;

public interface LottoCreationStrategy {
    public List<Lotto> create(int count);
}
