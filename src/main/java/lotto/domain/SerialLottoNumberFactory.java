package lotto.domain;

import lotto.utils.StringParser;

import java.util.List;
import java.util.stream.Collectors;

public class SerialLottoNumberFactory {

	/**
	 * @param input 예시) "1,2,3,4,5,6"
	 */
	public static SerialLottoNumber create(String input) throws IllegalArgumentException {
		List<Integer> integers = StringParser.stringToIntegerList(input);
		List<LottoNumber> lottoNumbers = integers.stream()
				.map(LottoNumber::new)
				.collect(Collectors.toUnmodifiableList());

		return new SerialLottoNumber(lottoNumbers);
	}
}
