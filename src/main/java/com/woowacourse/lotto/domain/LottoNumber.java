package com.woowacourse.lotto.domain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {
	public static final int NUMBER_OF_LOTTO = 6;
	public static final int MIN_NUMBER_OF_LOTTO = 1;
	public static final int MAX_NUMBER_OF_LOTTO = 45;
	private static final List<Integer> lottoNumbers = IntStream.rangeClosed(MIN_NUMBER_OF_LOTTO, MAX_NUMBER_OF_LOTTO)
			.boxed()
			.collect(Collectors.toList());

	public static Integer getLottoNumber(int index) {
		if (index < MIN_NUMBER_OF_LOTTO - 1 || index > MAX_NUMBER_OF_LOTTO - 1) { // index 범위는 0이상 44이하
			throw new IllegalArgumentException(ExceptionOutput.VIOLATE_INDEX.getExceptionMessage());
		}
		return lottoNumbers.get(index);
	}

	public static List<Integer> getLottos() {
		List<Integer> shuffleLottoNumbers = new ArrayList<>(lottoNumbers);
		Collections.shuffle(shuffleLottoNumbers);
		return Collections.unmodifiableList(shuffleLottoNumbers.subList(0, NUMBER_OF_LOTTO));
	}
}
