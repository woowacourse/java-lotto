package lotto.domain.number;

import lotto.util.StringParser;

import java.util.Set;
import java.util.stream.Collectors;

public class SerialLottoNumberFactory {
	public static SerialLottoNumber of(final Set<LottoNumber> lottoNumbers) {
		return new SerialLottoNumber(lottoNumbers);
	}

	public static SerialLottoNumber of(String input) {
		Set<LottoNumber> lottoNumbers = StringParser.parseIntegerList(input)
				.stream()
				.map(LottoNumber::of)
				.collect(Collectors.toSet());

		return of(lottoNumbers);
	}
}
