package domain.strategy;

import domain.LottoNumber;

import java.util.List;

public interface LottoNumberStrategy {
    List<LottoNumber> generate();
}
