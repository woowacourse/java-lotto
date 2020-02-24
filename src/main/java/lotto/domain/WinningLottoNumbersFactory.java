package lotto.domain;

import lotto.utils.StringParser;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoNumbersFactory {
	public static SerialLottoNumber createWinningLottoNumbers(String input) {
		List<Integer> integers = StringParser.stringToIntegerList(input);
		List<LottoNumber> lottoNumbers = integers.stream()
				.map(AllLottoNumbers::getLottoNumber)
				.collect(Collectors.toUnmodifiableList());

		return new SerialLottoNumber(lottoNumbers);
	}
}
