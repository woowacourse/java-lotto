package com.woowacourse.lotto.domain;

import java.util.Objects;

import com.woowacourse.lotto.exception.InvalidNumberException;

import static com.woowacourse.lotto.domain.LottoNumber.MAX_NUMBER_OF_LOTTO;
import static com.woowacourse.lotto.domain.LottoNumber.MIN_NUMBER_OF_LOTTO;

public class BonusBall {
	private final LottoNumber number;

	public BonusBall(int number) {
		if(number < MIN_NUMBER_OF_LOTTO || number > MAX_NUMBER_OF_LOTTO) {
			throw new InvalidNumberException(ExceptionOutput.VIOLATE_LOTTO_NUMBER_RANGE.getExceptionMessage());
		}
		this.number = LottoNumber.getLottoNumber(number);
	}

	public LottoNumber getBonusBallNumber() {
		return this.number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BonusBall)) {
			return false;
		}
		final BonusBall bonusBall = (BonusBall) o;
		return number == bonusBall.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
