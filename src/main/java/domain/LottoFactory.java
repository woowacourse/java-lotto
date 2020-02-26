package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import exception.LackOfMoneyException;

public class LottoFactory {
	private static final List<LottoNumber> numbers = new ArrayList<>();
	private static final int LOTTO_LENGTH_FRONT = 0;
	private static final int LOTTO_LENGTH = 6;
	private static final int LOTTO_PRICE = 1_000;
	private static final int LOTTO_NUMBER_FRONT = 1;
	private static final int LOTTO_NUMBER_RANGE = 45;

	static {
		for (int i = LOTTO_NUMBER_FRONT; i <= LOTTO_NUMBER_RANGE; i++) {
			numbers.add(LottoNumber.createNumber(i));
		}
	}

	public static List<Lotto> createLottos(Money purchaseMoney) {
		moneyValidate(purchaseMoney);
		int lottoCount = (int)purchaseMoney.division(LOTTO_PRICE);
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < lottoCount; i++) {
			lottos.add(createLotto());
		}
		return lottos;
	}

	private static void moneyValidate(Money purchaseMoney) {
		if (purchaseMoney.isLessThan(LOTTO_PRICE)) {
			throw new LackOfMoneyException();
		}
	}

	private static Lotto createLotto() {
		Collections.shuffle(numbers);
		ArrayList<LottoNumber> subNumbers = new ArrayList<>(numbers.subList(LOTTO_LENGTH_FRONT, LOTTO_LENGTH));
		return new Lotto(subNumbers);
	}
}