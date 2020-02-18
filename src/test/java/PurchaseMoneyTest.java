import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.IllegalMoneyUnitException;

public class PurchaseMoneyTest {
	@ParameterizedTest
	@ValueSource(strings = {"1500","3200","7500"})
	@DisplayName("구입 금액이 1000원 단위가 아닌 경우")
	void 구입_금액_1000_단위_검증(String purchaseMoney) {
		assertThatThrownBy(() -> {
			new PurchaseMoney(purchaseMoney);
		}).isInstanceOf(IllegalMoneyUnitException.class)
			.hasMessageMatching("구입금액은 1000원단위로만 가능합니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"asd","문자열","100a"})
	void 문자열_포함(String money) {
		assertThatThrownBy(() -> {
			new PurchaseMoney(money);
		}).isInstanceOf(NumberFormatException.class)
			.hasMessageMatching("문자열은 입력되지 않습니다.");
	}



}
