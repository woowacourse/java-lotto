package util;

import domain.LottoNumbers;

import java.util.List;

public interface LottoNumbersGenerator {

	List<LottoNumbers> generate(int count);
}
