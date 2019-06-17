package lotto.domain.lotto.LottoStrategy;

import java.util.List;

@FunctionalInterface
public interface LottoStrategy {
    List<Integer> generate();
}
