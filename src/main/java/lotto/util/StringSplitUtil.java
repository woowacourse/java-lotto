package lotto.util;

public class StringSplitUtil {
	static final String LOTTO_SPLIT_DELIMITER = ", ";

	public static String[] splitRawLottoNumbers(String rawWinningLotto) {
		return rawWinningLotto.split(LOTTO_SPLIT_DELIMITER);
	}
}
