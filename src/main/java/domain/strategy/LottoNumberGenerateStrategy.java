package domain.strategy;

import domain.LottoNumber;
import java.util.Set;

@FunctionalInterface
public interface LottoNumberGenerateStrategy {
    Set<LottoNumber> generateNumbers();
}
