package model.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import rule.LottoRule;

public class RandomLottoNumbersGenerator {
	private static final List<Integer> lottoNumbers = new ArrayList<>();

	static {
		IntStream.range(LottoRule.LOTTO_MIN_NUMBER, LottoRule.LOTTO_MAX_NUMBER).forEach(lottoNumbers::add);
	}

	public static List<Integer> pickSixNumbers() {
		List<Integer> shuffledNumbers = shuffleLottoNumbers();
		return shuffledNumbers.stream()
			.limit(LottoRule.LOTTO_SIZE)
			.collect(Collectors.toList());
	}

	private static List<Integer> shuffleLottoNumbers() {
		Collections.shuffle(lottoNumbers);
		return lottoNumbers;
	}
}
