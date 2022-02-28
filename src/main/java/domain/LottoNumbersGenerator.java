package domain;

import java.util.Set;

@FunctionalInterface
public interface LottoNumbersGenerator {
    Set<LottoNumber> generate();
}
