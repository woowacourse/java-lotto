package lotto.domain;

import java.util.Objects;

/**
 * MoneyForLotto 클래스
 *
 * @author 히히, 토니
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class MoneyForLotto {
	private static final int LOTTO_PRICE = 1000;

	private final int moneyForLotto;

	public MoneyForLotto(final Integer inputMoney) {
		Objects.requireNonNull(inputMoney);
		if (inputMoney < LOTTO_PRICE) {
			throw new IllegalArgumentException("1000원 이상 입력해주세요.");
		}
		this.moneyForLotto = inputMoney;
	}

	public int getMoneyForLotto() {
		return this.moneyForLotto;
	}

	public int calculateAmountOfLottos() {
		return this.moneyForLotto / LOTTO_PRICE;
	}
}
