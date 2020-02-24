package lotto.domain.lotto;

import lotto.domain.lottonumber.LottoNumber;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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
