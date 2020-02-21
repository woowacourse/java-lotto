package lotto.domain;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningLottoParser {
	private static final String DELIMITER = ",";

	public static Set<LottoNumber> parseToLottoNumberSet(String inputWinningLotto) {
		return Arrays.stream(inputWinningLotto.split(DELIMITER))
			.map(String::trim)
			.map(Integer::parseInt)
			.map(LottoNumber::valueOf)
			.collect(Collectors.toSet());
	}
}
