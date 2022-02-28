package domain.strategy;

import java.util.List;

import domain.LottoNumber;

public interface LottoGeneratorStrategy {

    List<LottoNumber> generate();
}
