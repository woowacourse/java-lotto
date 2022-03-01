package domain.strategy;

import java.util.Set;

@FunctionalInterface
public interface LottoNumberGenerateStrategy {
    Set<Integer> generateLottoNumbers();
}
