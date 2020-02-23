package lotto.domain;

import java.util.Objects;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class MoneyForLotto {
	private static final int LOTTO_PRICE = 1000;
	private static final String MIN_PRICE_EXCEPTION_MESSAGE = "1000원 이상 입력해주세요.";
	private static final String NULL_INPUT_EXCEPTION_MESSAGE = "입력금액은 null일 수 업습니다.";
	private static final String NONE_INTEGER_INPUT_EXCEPTION_MESSAGE = "입력금액이 정수가 아닙니다.";

	private final int moneyForLotto;

	public MoneyForLotto(final String inputMoney) {
		this.moneyForLotto = validateMoneyForLotto(inputMoney);
	}

	private int parseToInteger(String inputMoney) {
		try {
			return Integer.parseInt(inputMoney);
		} catch (NumberFormatException nfe) {
			throw new InvalidMoneyForLottoException(NONE_INTEGER_INPUT_EXCEPTION_MESSAGE);
		}
	}

	private void validateMinPrice(int inputMoney) {
		if (inputMoney < LOTTO_PRICE) {
			throw new InvalidMoneyForLottoException(MIN_PRICE_EXCEPTION_MESSAGE);
		}
	}

	private int validateMoneyForLotto(final String inputMoney) {
		Objects.requireNonNull(inputMoney, NULL_INPUT_EXCEPTION_MESSAGE);
		int integerMoney = parseToInteger(inputMoney);
		validateMinPrice(integerMoney);
		return integerMoney;
	}

	public int getMoneyForLotto() {
		return this.moneyForLotto;
	}

	public int calculateAmountOfLottos() {
		return this.moneyForLotto / LOTTO_PRICE;
	}
}
