package model.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import rule.Rule;

public class RandomLottoNumbersGenerator {
	private static final List<Integer> lottoNumbers = new ArrayList<>();

	static {
		IntStream.range(Rule.LOTTO_MIN_NUM.getRuleNum(), Rule.LOTTO_MAX_NUM.getRuleNum()).forEach(lottoNumbers::add);
	}

	public static List<Integer> pickSixNumbers() {
		List<Integer> shuffledNumbers = shuffleLottoNumbers();
		return shuffledNumbers.stream()
			.limit(Rule.LOTTO_SIZE.getRuleNum())
			.collect(Collectors.toList());
	}

	private static List<Integer> shuffleLottoNumbers() {
		Collections.shuffle(lottoNumbers);
		return lottoNumbers;
	}
}
