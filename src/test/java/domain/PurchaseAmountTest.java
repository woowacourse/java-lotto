package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("PurchaseAmount 테스트")
class PurchaseAmountTest {

	@Nested
	@DisplayName("로또 구입 금액이")
	class AmountRangeTest {

		@DisplayName("1,000원 ~ 100,000원 사이이면 통과")
		@ParameterizedTest(name = "{index} {displayName} purchaseAmount={0}")
		@ValueSource(ints = {1000, 50000, 100000})
		void properAmountRange(final int purchaseAmount) {
			assertThatNoException().isThrownBy(
				() -> new PurchaseAmount(purchaseAmount)
			);
		}

		@DisplayName("1,000원 이하 또는 100,000원 이상이면 실패")
		@ParameterizedTest(name = "{index} {displayName} purchaseAmount={0}")
		@ValueSource(ints = {999, 0, 100001})
		void invalidAmountRange(final int purchaseAmount) {
			assertThatThrownBy(() -> {
				new PurchaseAmount(purchaseAmount);
			}).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("구입 금액의 범위는 1000원~100000원 입니다.");
		}
	}

	@DisplayName("구입한 롯또 갯수가 올바른지 확인")
	@ParameterizedTest(name = "{index} {displayName} money={0} expectedLotteries={1}")
	@CsvSource(value = {"1000, 1", "1001, 1", "99999, 99"})
	void getTheNumberOfPurchasedLotteries(final int money, final int expectedLotteries) {
		final PurchaseAmount purchaseAmount = new PurchaseAmount(money);
		final int actualLotteries = purchaseAmount.getTheNumberOfPurchasedLotteries();
		assertThat(actualLotteries).isEqualTo(expectedLotteries);
	}

	@DisplayName("이윤률이 제대로 나오는지 확인")
	@ParameterizedTest(name = "{index} {displayName} money={0} earningAmount={1}")
	@CsvSource(value = {"1000, 1000", "1000, 0", "1000, 10000"})
	void calculateEarningRate(final int money, final int earningAmount) {
		final PurchaseAmount purchaseAmount = new PurchaseAmount(money);
		final double expectedEarningRate = purchaseAmount.calculateEarningRate(earningAmount);
		final double actualEarningRate = (double)earningAmount / money;

		assertThat(expectedEarningRate).isEqualTo(actualEarningRate);
	}
}