package lotto.domain;

import lotto.utils.StringParser;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoNumbersFactory {
	public static SerialLottoNumber create(String input) throws IllegalArgumentException {
		List<Integer> integers = StringParser.stringToIntegerList(input);
		List<LottoNumber> lottoNumbers = integers.stream()
				.map(LottoNumber::new)
				.collect(Collectors.toUnmodifiableList());

		return new SerialLottoNumber(lottoNumbers);
	}
}
