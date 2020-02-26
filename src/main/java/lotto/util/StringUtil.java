package lotto.util;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.ticket.LottoBall;

public class StringUtil {
	private static final String LOTTO_NUMBERS_DELIMITER = ", ";
	private static final String LOTTO_SPLIT_DELIMITER = ",";

	public static String[] splitRawLottoNumbers(String rawWinningLotto) {
		return rawWinningLotto.split(LOTTO_SPLIT_DELIMITER);
	}

	public static String parseBalls(List<LottoBall> lottoBalls) {
		return lottoBalls.stream()
			.map(LottoBall::toString)
			.collect(Collectors.joining(LOTTO_NUMBERS_DELIMITER));
	}
}
