package model.lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {
	private static final int LOTTO_SIZE = 6;
	private static final int LOTTO_MIN_NUM = 1;
	private static final int LOTTO_MAX_NUM = 45;

	public static void validateOutOfRange(int number, String message) {
		if (number > LOTTO_MAX_NUM || number < LOTTO_MIN_NUM) {
			throw new IllegalArgumentException(message);
		}
	}

	public static boolean isSizeCorrect(int size) {
		return size == LOTTO_SIZE;
	}

	public static void makeLottoNumbers(List<Integer> lottoNumbers) {
		IntStream.range(LOTTO_MIN_NUM, LOTTO_MAX_NUM).forEach(lottoNumbers::add);
	}

	public static List<Integer> cutByLottoSize(List<Integer> shuffledNumbers) {
		return shuffledNumbers.stream()
			.limit(LOTTO_SIZE)
			.collect(Collectors.toList());
	}
}
