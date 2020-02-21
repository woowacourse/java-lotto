package lotto.domain;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningLottoParser {
	private static final String DELIMITER = ",";
	private static final int LOTTO_NUMBER_COUNT = 6;

	public static Set<LottoNumber> parseToLottoNumberSet(String inputWinningLotto) {
		validateNullAndEmpty(inputWinningLotto);
		validateCount(inputWinningLotto);
		validateInteger(inputWinningLotto);

		return Arrays.stream(inputWinningLotto.split(DELIMITER))
			.map(String::trim)
			.map(Integer::parseInt)
			.map(LottoNumber::valueOf)
			.collect(Collectors.toSet());
	}

	private static void validateInteger(String inputWinningLotto) {
		try {
			Arrays.stream(inputWinningLotto.split(DELIMITER)).forEach(Integer::parseInt);
		} catch (NumberFormatException ne) {
			throw new InvalidWinningLottoException(InvalidWinningLottoException.NOT_INTEGER);
		}
	}

	private static void validateCount(String inputWinningLotto) {
		if (inputWinningLotto.split(DELIMITER).length != LOTTO_NUMBER_COUNT) {
			throw new InvalidWinningLottoException(InvalidWinningLottoException.INVALID_COUNT);
		}
	}

	private static void validateNullAndEmpty(String inputWinningLotto) {
		if (inputWinningLotto == null || inputWinningLotto.isEmpty()) {
			throw new InvalidWinningLottoException(InvalidWinningLottoException.NULL_AND_EMPTY_STRING);
		}
	}
}
