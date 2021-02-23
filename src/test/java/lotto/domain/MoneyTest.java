package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static lotto.domain.Money.MANUAL_LOTTO_QUANTITY_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MoneyTest {
	@DisplayName("올바른 입력값 확인")
	@Test
	void moneyConstructor() {
		Money money = new Money(1_000);

		assertThat(money).isEqualTo(new Money(1_000));
	}

	@DisplayName("금액 따라 로또 갯수 산출 하는지")
	@Test
	void calculateNumberOfLotto() {
		Money money = new Money(13_001);
		long numberOfLotto = money.getAffordableLottoQuantity();

		assertThat(numberOfLotto).isEqualTo(13);
	}

	@DisplayName("input으로 받은 금액 중에서 실제로 로또를 구매한 금액을 제대로 산출하는지")
	@Test
	void calculateMoneyActuallyInvested() {
		Money thisMoney = new Money(4_000);
		Money thatMoney = new Money(3_999);

		assertThat(thisMoney.getMoneyActuallyInvested()).isEqualTo(new Money(4_000));
		assertThat(thatMoney.getMoneyActuallyInvested()).isEqualTo(new Money(3_000));
	}

	@DisplayName("수익률 계산")
	@Test
	void calculateProfitRate() {
		Money moneyInvested = new Money(2_000);
		Money profit = new Money(10_000);

		assertThat(moneyInvested.getProfitRate(profit)).isEqualTo((float) 10_000 / 2_000);
	}

	@DisplayName("수동 로또 구매할 돈이 부족할 때 에러 반환 하는지")
	@Test
	void validateAffordability() {
		Money money = new Money(2_999);
		int manualLottoQuantity = 3;

		assertThatThrownBy(() -> money.validateAffordability(manualLottoQuantity)).isInstanceOf(IllegalArgumentException.class)
				.hasMessage(MANUAL_LOTTO_QUANTITY_ERROR);
	}

	@DisplayName("수동 로또 구매 후 잔돈 계산 제대로 하는지")
	@Test
	void calculateMoneyLeft() {
		Money money = new Money(5_540);
		int manualLottoQuantity = 3;

		assertThat(money.getChange(manualLottoQuantity)).isEqualTo(new Money(2_540));
	}
}