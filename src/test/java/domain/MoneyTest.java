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
	@DisplayName("구매 가능한지 경계값 확인")
	void checkIsPossibleToPurchase() {
		//given
		int compareMoney = 3000;
		//when
		Money money = Money.from("3000");
		//then
		assertTrue(money.isPossibleToPurchase(compareMoney));
	}

	@Test
	@DisplayName("구매 불가능한지 경계값 확인")
	void checkIsImPossibleToPurchase() {
		//given
		int compareMoney = 4000;
		//when
		Money money = Money.from("3000");
		//then
		assertFalse(money.isPossibleToPurchase(compareMoney));
	}
}
