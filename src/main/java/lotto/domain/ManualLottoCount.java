package lotto.domain;

import java.util.Iterator;

import lotto.exception.InvalidLottoCountException;
import lotto.validator.Validator;

public class ManualLottoCount implements Iterator<Integer> {
	private static final int MIN_COUNT = 1;
	private final Money money;
	private int count;

	public ManualLottoCount(String count, Money money) {
		Validator.validateInteger(count);
		this.money = money;
		int lottoCount = Integer.parseInt(count);
		validProperCount(lottoCount);
		this.count = lottoCount;
	}

	private void validProperCount(int lottoCount) {
		if (lottoCount < MIN_COUNT || !this.money.isBuyLotto(lottoCount)) {
			throw new InvalidLottoCountException();
		}
	}

	@Override
	public boolean hasNext() {
		return count >= MIN_COUNT;
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new IllegalArgumentException();
		}
		money.next();
		return --count;
	}
}
