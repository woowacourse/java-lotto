package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PurchaseInformationTest {

	@Nested
	@DisplayName("수동으로 구입할 로또의 갯수가 구입 금액으로 구입 가능한 갯수보다 ")
	class PurchaseManualLotteriesTest {

		@Test
		@DisplayName("크면 실패")
		void lackOfPurchaseAmount() {
			final PurchaseAmount purchaseAmount = new PurchaseAmount(1000);

			assertThatThrownBy(() -> new PurchaseInformation(purchaseAmount, 2))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("수동 구입 갯수가 구입 금액보다 많습니다");
		}

		@Test
		@DisplayName("작거나 같으면 성공")
		void enoughPurchaseAmount() {
			final PurchaseAmount purchaseAmount = new PurchaseAmount(2000);

			assertThatNoException()
				.isThrownBy(() -> new PurchaseInformation(purchaseAmount, 2));
		}
	}

	@DisplayName("자동 구입 갯수와 수동 구입 갯수의 합이 구입 금액으로 구입 가능한 갯수와 일치한지 확인")
	@ParameterizedTest(name = "{index} {displayName} purchaseAmount={0} theNumberOfManualLotteries={1}")
	@CsvSource(value = {"2000, 2", "2000, 0"})
	void sumOfAutoAndManualLotteries(final int money, final int theNumberOfManualLotteries) {
		final PurchaseAmount purchaseAmount = new PurchaseAmount(money);

		final PurchaseInformation purchaseInformation =
			new PurchaseInformation(purchaseAmount, theNumberOfManualLotteries);
		final int actualNumber = purchaseInformation.getTheNumberOfAutoPurchasedLotteries()
			+ theNumberOfManualLotteries;
		final int expectedNumber = purchaseAmount.getTheNumberOfPurchasedLotteries();

		assertThat(actualNumber).isEqualTo(expectedNumber);
	}

}
