package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {

	@Test
	@DisplayName("입력한 금액이 숫자가 아니면 예외를 발생시킨다")
	void notDigitMoney() {
		assertThatThrownBy(() -> Money.from("a100"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("구입 금액은 숫자여야 합니다");
	}

	@Test
	@DisplayName("입력한 금액이 1000원 단위가 아닐 때 예외를 발생시킨다")
	void checkUnitOfMoney() {
		assertThatThrownBy(() -> Money.from("12345"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("구입 금액은 1000원 단위여야 합니다");
	}

	@Test
	@DisplayName("구매 가능한 개수 확인")
	void checkFindPurchaseCount() {
		//given
		Money money = Money.from("3000");
		//then
		assertThat(money.findPurchaseLottoCount()).isEqualTo(3);
	}

	@Test
	@DisplayName("수동 로또 구매 확인")
	public void checkManualLottoPurchase() {
		//given
		Money money = Money.from("5000");
		int manualLottoCount = 2;
		//when
		money.purchaseManualLotto(manualLottoCount);
		//then
		assertThat(money.findPurchaseLottoCount()).isEqualTo(3);
	}
}
