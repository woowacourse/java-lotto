package service;

import domain.LottoNumber;
import java.util.List;

@FunctionalInterface
public interface LottoNumbersGenerator {

    List<LottoNumber> generate();
}
