package lotto.domain.money;

import java.util.Objects;

/**
 * 로또를 위한 돈을 래핑한 객체, 예외를 던진다
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class MoneyForLotto {
	private static final int LOTTO_PRICE = 1000;

	private final int moneyForLotto;

	public MoneyForLotto(final String inputMoney) {
		this.moneyForLotto = validateMoneyForLotto(inputMoney);
	}

	private int parseToInteger(String inputMoney) {
		try {
			return Integer.parseInt(inputMoney);
		} catch (NumberFormatException nfe) {
			throw new InvalidMoneyForLottoException("입력금액이 정수가 아닙니다.");
		}
	}

	private void validateMinPrice(int inputMoney) {
		if (inputMoney < LOTTO_PRICE) {
			throw new InvalidMoneyForLottoException("1000원 이상 입력해주세요.");
		}
	}

	private int validateMoneyForLotto(final String inputMoney) {
		Objects.requireNonNull(inputMoney, "입력금액은 null일 수 업습니다.");
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
