package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoParser {
	public static final String DELIMITER = ",";

	public static List<LottoNumber> parser(String inputWinningLotto) {
		return Arrays.stream(inputWinningLotto.split(DELIMITER))
			.map(String::trim)
			.map(Integer::parseInt)
			.map(LottoNumber::valueOf)
			.collect(Collectors.toList());
	}
}
