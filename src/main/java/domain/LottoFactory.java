package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
	private static final List<LottoNumber> numbers = new ArrayList<>();
	private static final int RANGE_START = 0;
	private static final int RANGE_END = 6;
	private static final int LOTTO_PRICE = 1000;
	private static final int LOTTO_NUMBER_RANGE = 45;
	private static final int ONE = 1;

	static {
		for (int i = ONE; i <= LOTTO_NUMBER_RANGE; i++) {
			numbers.add(new LottoNumber(i));
		}
	}

	public static List<Lotto> createLottos(Money purchaseMoney) {
		validate(purchaseMoney);
		int lottoCount = purchaseMoney.getMoney() / LOTTO_PRICE;
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < lottoCount; i++) {
			lottos.add(createLotto());
		}
		return lottos;
	}

	private static void validate(Money purchaseMoney) {
		if (purchaseMoney.getMoney() < LOTTO_PRICE) {
			throw new IllegalArgumentException("가격은 1000원 이상 입력해야합니다.");
		}
	}

	private static Lotto createLotto() {
		Collections.shuffle(numbers);
		ArrayList<LottoNumber> subNumbers = new ArrayList<>(numbers.subList(RANGE_START, RANGE_END));
		return new Lotto(subNumbers);
	}
}
