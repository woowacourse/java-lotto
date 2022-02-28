package domain.strategy;

import java.util.List;

@FunctionalInterface
public interface LottoNumberGenerateStrategy {
    List<Integer> generateLottoNumbers();
}
