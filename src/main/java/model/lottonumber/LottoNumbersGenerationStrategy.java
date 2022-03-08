package model.lottonumber;

import java.util.List;

public interface LottoNumbersGenerationStrategy {
	List<LottoNumber> generate(int size);
}
