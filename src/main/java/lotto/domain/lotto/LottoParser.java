package lotto.domain.lotto;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.lottonumber.LottoNumber;

public class LottoParser {
	private static final String DELIMITER = ",";

	public static Set<LottoNumber> parser(String inputWinningLotto) {
		return Arrays.stream(inputWinningLotto.split(DELIMITER))
				.map(String::trim)
				.map(Integer::parseInt)
				.map(LottoNumber::valueOf)
				.collect(Collectors.toSet());
	}
}
