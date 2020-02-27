package lotto.domain.number;

import lotto.domain.random.RandomGenerator;
import lotto.util.StringParser;

import java.util.Set;
import java.util.stream.Collectors;

public class SerialLottoNumberFactory {
	public static SerialLottoNumber of(String input) {
		Set<LottoNumber> lottoNumbers = StringParser.parseIntegerList(input)
				.stream()
				.map(LottoNumber::of)
				.collect(Collectors.toSet());

		return new SerialLottoNumber(lottoNumbers);
	}

	public static SerialLottoNumber of(RandomGenerator randomGenerator) {
		return new SerialLottoNumber(randomGenerator.ofSixLottoNumber());
	}
}
