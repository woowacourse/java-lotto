package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * 구입 정보 테스트 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/29
 */
public class PurchaseInformationTest {
	@ParameterizedTest
	@DisplayName("구입 금액과 수동 개수를 입력받아 구입 정보 생성")
	@CsvSource(value = {"5000,0", "5000,3", "5000,5"})
	void constructor(long lottoMoney, int manualCount) {
		assertThat(new PurchaseInformation(new PurchaseMoney(lottoMoney), manualCount))
				.isInstanceOf(PurchaseInformation.class);
	}

	@Test
	@DisplayName("구입 금액이 null인 경우 예외 발생")
	void constructor_PurchaseMoneyIsNull() {
		assertThatThrownBy(() -> new PurchaseInformation(null, -1))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("수동 구입 개수로 음수를 입력한 경우 예외 발생")
	void constructor_InvalidManualCount() {
		assertThatThrownBy(() -> new PurchaseInformation(new PurchaseMoney(1000), -1))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("구매 금액이 부족하여 수동 구입을 할 수 없는 경우 예외 발생")
	void constructor_LackOfMoney() {
		assertThatThrownBy(() -> new PurchaseInformation(new PurchaseMoney(3000), 4))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
