package com.woowacourse.lotto.domain;

import java.util.*;

public class LottoNumber implements Comparable<LottoNumber> {
	public static final int NUMBER_OF_LOTTO = 6;
	public static final int MIN_NUMBER_OF_LOTTO = 1;
	public static final int MAX_NUMBER_OF_LOTTO = 45;

	private static final List<LottoNumber> lottoNumbers = new LinkedList<>();
	private final int number;

	static {
		for (int i = MIN_NUMBER_OF_LOTTO; i <= MAX_NUMBER_OF_LOTTO; i++) {
			lottoNumbers.add(new LottoNumber(i));
		}
	}

	private LottoNumber(int number) {
		this.number = number;
	}

	public static LottoNumber getLottoNumber(int value) {
		if (value < MIN_NUMBER_OF_LOTTO || value > MAX_NUMBER_OF_LOTTO) {
			throw new IllegalArgumentException(ExceptionOutput.VIOLATE_INDEX.getExceptionMessage());
		}
		return lottoNumbers.get(value - 1);
	}

	public static List<LottoNumber> getLottos() {
		List<LottoNumber> shuffleLottoNumbers = new ArrayList<>(lottoNumbers);
		Collections.shuffle(shuffleLottoNumbers);
		return Collections.unmodifiableList(shuffleLottoNumbers.subList(0, NUMBER_OF_LOTTO));
	}

	public String getName() {
		return String.valueOf(number);
	}

	@Override
	public int compareTo(LottoNumber lottoNumber) {
		return this.number - lottoNumber.number;
	}
}
