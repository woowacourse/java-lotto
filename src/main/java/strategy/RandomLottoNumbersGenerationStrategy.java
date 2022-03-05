package strategy;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbersGenerationStrategy;

public class RandomLottoNumbersGenerationStrategy implements LottoNumbersGenerationStrategy {
	private static final List<LottoNumber> lottoNumbers;

	static {
		lottoNumbers = LottoNumber.makeLottoNumbers();
	}

	@Override
	public List<LottoNumber> generate(int size) {
		return shuffleLottoNumbers().stream()
			.limit(size)
			.collect(Collectors.toList());
	}

	private static List<LottoNumber> shuffleLottoNumbers() {
		Collections.shuffle(lottoNumbers);
		return lottoNumbers;
	}
}
