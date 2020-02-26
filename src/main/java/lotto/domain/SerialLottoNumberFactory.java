package lotto.domain;

import lotto.utils.StringParser;

import java.util.List;
import java.util.stream.Collectors;

public class SerialLottoNumberFactory {
	public static SerialLottoNumber create(String input) {
		List<Integer> integers = StringParser.stringToIntegerList(input);
		List<LottoNumber> lottoNumbers = integers.stream()
				.map(LottoNumber::getLottoNumber)
				.collect(Collectors.toUnmodifiableList());

		return new SerialLottoNumber(lottoNumbers);
	}
}
