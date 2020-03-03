package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * 구입 정보 테스트 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/29
 */
public class PurchaseCountTest {
	@ParameterizedTest
	@DisplayName("구입 개수를 입력받아 인스턴스 생성")
	@ValueSource(longs = {0, 100})
	void calculateRestPurchaseCount(long count) {
		assertThat(new PurchaseCount(count)).isInstanceOf(PurchaseCount.class);
	}

	@ParameterizedTest
	@DisplayName("구입 금액과 수동 개수를 입력받아 구입 정보 생성")
	@CsvSource(value = {"5000,0", "5000,3", "5000,5"})
	void calculateRestPurchaseCount(long lottoMoney, int count) {
		PurchaseCount manualCount = new PurchaseCount(count);
		assertThat(manualCount.calculateRestPurchaseCount(new PurchaseMoney(lottoMoney)))
				.isInstanceOf(PurchaseCount.class);
	}

	@Test
	@DisplayName("수동 구입 개수로 음수를 입력한 경우 예외 발생")
	void constructor_InvalidManualCount() {
		assertThatThrownBy(() -> new PurchaseCount(-1))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("구매 금액이 부족하여 수동 구입을 할 수 없는 경우 예외 발생")
	void constructor_LackOfMoney() {
		PurchaseCount manualCount = new PurchaseCount(4);
		assertThatThrownBy(() -> manualCount.calculateRestPurchaseCount(new PurchaseMoney(3_000)))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
