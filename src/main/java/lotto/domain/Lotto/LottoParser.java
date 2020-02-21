package lotto.domain.Lotto;

import lotto.domain.LottoNumber.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoParser {
	private static final String DELIMITER = ",";

	public static List<LottoNumber> parser(String inputWinningLotto) {
		return Arrays.stream(inputWinningLotto.split(DELIMITER))
				.map(String::trim)
				.map(Integer::parseInt)
				.map(LottoNumber::valueOf)
				.collect(Collectors.toList());
	}
}
