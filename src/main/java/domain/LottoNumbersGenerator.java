package domain;

import java.util.List;

@FunctionalInterface
public interface LottoNumbersGenerator {
    List<LottoNumber> generate();
}
