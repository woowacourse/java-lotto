package lotto.domain.generator;

import java.util.List;

@FunctionalInterface
public interface LottoNumberGenerator {
    List<Integer> generate(int size);
}
