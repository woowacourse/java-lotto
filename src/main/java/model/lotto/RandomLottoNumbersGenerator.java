package model.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoNumbersGenerator {
	private static final List<Integer> lottoNumbers = new ArrayList<>();
	private static final int LOTTO_SIZE = 6;
	private static final int LOTTO_MIN_NUMBER = 1;
	private static final int LOTTO_MAX_NUMBER = 45;

	static {
		IntStream.range(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER).forEach(lottoNumbers::add);
	}

	public static List<Integer> pickSixNumbers() {
		List<Integer> shuffledNumbers = shuffleLottoNumbers();
		return shuffledNumbers.stream()
			.limit(LOTTO_SIZE)
			.collect(Collectors.toList());
	}

	private static List<Integer> shuffleLottoNumbers() {
		Collections.shuffle(lottoNumbers);
		return lottoNumbers;
	}
}
