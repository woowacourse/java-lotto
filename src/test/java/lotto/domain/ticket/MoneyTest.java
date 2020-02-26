package lotto.domain.ticket;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MoneyTest {
	@DisplayName("1_000원 단위의 금액이 아닌 경우 예외 발생 검증")
	@ParameterizedTest
	@ValueSource(ints = {1_001, 999, 10_010, -10, -1_000})
	void validDivideByThousand(int money) {
		assertThatThrownBy(() -> Money.valueOf(money))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("금액을 통해 구매 로또 갯수 반환 테스트")
	@ParameterizedTest
	@ValueSource(longs = {1_000, 10_000, 50_000, 1_000_000, 2_100_000_000})
	void calculateLottoCount(long moneyValue) {
		Money money = Money.valueOf(moneyValue);
		int expected = (int)moneyValue / 1000;
		assertThat(money.calculatePurchaseCount()).extracting("lottoCount").isEqualTo(expected);
	}

	@DisplayName("Money 객체의 배수 계 기능 확인")
	@ParameterizedTest
	@ValueSource(longs = {2_000_000_000, 1_000, 1_000_000_000})
	void multiplyTest(long beforeMoney) {
		Money money = Money.valueOf(beforeMoney);
		int multiplier = 10;
		long expected = beforeMoney * 10;
		assertThat(money.multiply(multiplier)).extracting("money").isEqualTo(expected);
	}

	@DisplayName("Money 객체끼리 덧셈 기능 확인")
	@ParameterizedTest
	@ValueSource(longs = {1000, 10000, 5000})
	void plusTest(long moneyValue) {
		long operandMoneyValue = 1_000_000;
		Money firstOperand = Money.valueOf(moneyValue);
		Money secondOperand = Money.valueOf(operandMoneyValue);
		assertThat(firstOperand.plus(secondOperand)).isEqualTo(Money.valueOf(moneyValue + operandMoneyValue));
	}

	@DisplayName("Money 객체의 수익률 계산 기능 확인")
	@ParameterizedTest
	@ValueSource(longs = {5_000, 10_000, 1_000_000, 4_300_005_000L})
	void findProfits(long totalPrizeValue) {
		long purchaseMoneyValue = 1_000_000;
		Money prize = Money.valueOf(totalPrizeValue);
		Money purchaseMoney = Money.valueOf(purchaseMoneyValue);
		long expected = totalPrizeValue * 100 / purchaseMoneyValue;
		assertThat(prize.calculateProfitRate(purchaseMoney)).isEqualTo(expected);
	}
}
