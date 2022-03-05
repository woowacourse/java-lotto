package strategy;

import java.util.List;

import model.lottonumber.LottoNumber;

public interface LottoNumbersGenerationStrategy {
	List<LottoNumber> generate(int size);
}
