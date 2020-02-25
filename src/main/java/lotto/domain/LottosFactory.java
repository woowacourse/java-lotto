package lotto.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * LottosFactory 클래스
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/21
 */
public class LottosFactory {
	private LottosFactory() {}

	public static Lottos createLottosAuto(Money money) {
		validateMoney(money);

		int amountOfLottos = calculateAmountOfLottos(money);
		List<Lotto> paidLotto = new ArrayList<>();

		for (int i = 0; i < amountOfLottos; i++) {
			paidLotto.add(Lotto.createLottoAuto());
		}
		return new Lottos(paidLotto);
	}

	private static void validateMoney(Money inputMoney) {
		if (inputMoney.getMoney() < Lotto.LOTTO_PRICE) {
			throw new IllegalArgumentException(Lotto.LOTTO_PRICE + "원 이상 입력해주세요.");
		}
	}

	private static int calculateAmountOfLottos(Money money) {
		return money.getMoney() / Lotto.LOTTO_PRICE;
	}
}
