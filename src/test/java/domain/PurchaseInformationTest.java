package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import utils.Parser;

public class PurchaseInformationTest {

	@Nested
	@DisplayName("수동으로 구입할 로또의 갯수가 구입 금액으로 구입 가능한 갯수보다 ")
	class PurchaseManualLotteriesTest {

		@Test
		@DisplayName("크면 실패")
		void lackOfPurchaseAmount() {
			final PurchaseAmount purchaseAmount = new PurchaseAmount(1000);
			final List<Lottery> inputtedManualLotteries = new ArrayList<>();
			inputtedManualLotteries.add(new Lottery(Parser.toLotteryNumberList(Arrays.asList(1, 2, 3, 4, 5, 6))));
			final Lotteries manualLotteries = new Lotteries(inputtedManualLotteries);

			assertThatThrownBy(() -> new PurchaseInformation(purchaseAmount, manualLotteries, 2))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("수동 구입 갯수가 구입 금액보다 많습니다");
		}

		@Test
		@DisplayName("작거나 같으면 성공")
		void enoughPurchaseAmount() {
			final PurchaseAmount purchaseAmount = new PurchaseAmount(2000);
			final List<Lottery> inputtedManualLotteries = new ArrayList<>();
			inputtedManualLotteries.add(new Lottery(Parser.toLotteryNumberList(Arrays.asList(1, 2, 3, 4, 5, 6))));
			inputtedManualLotteries.add(new Lottery(Parser.toLotteryNumberList(Arrays.asList(1, 2, 3, 4, 5, 6))));
			final Lotteries manualLotteries = new Lotteries(inputtedManualLotteries);

			assertThatNoException()
				.isThrownBy(() -> new PurchaseInformation(purchaseAmount, manualLotteries, 2));
		}
	}

	@DisplayName("자동 구입 갯수와 수동 구입 갯수의 합이 구입 금액으로 구입 가능한 갯수와 일치한지 확인")
	@ParameterizedTest(name = "{index} {displayName} purchaseAmount={0} theNumberOfManualLotteries={1}")
	@CsvSource(value = {"2000, 2", "2000, 0"})
	void sumOfAutoAndManualLotteries(final int money, final int theNumberOfManualLotteries) {
		final PurchaseAmount purchaseAmount = new PurchaseAmount(money);
		final List<Lottery> inputtedManualLotteries = new ArrayList<>();
		for (int i = 0; i < theNumberOfManualLotteries; i++) {
			inputtedManualLotteries.add(new Lottery(Parser.toLotteryNumberList(Arrays.asList(1, 2, 3, 4, 5, 6))));
		}
		final Lotteries manualLotteries = new Lotteries(inputtedManualLotteries);

		final PurchaseInformation purchaseInformation = new PurchaseInformation(purchaseAmount, manualLotteries, theNumberOfManualLotteries);
		final int actualNumber = purchaseInformation.getTheNumberOfAutoPurchasedLotteries() + theNumberOfManualLotteries;
		final int expectedNumber = purchaseAmount.getTheNumberOfPurchasedLotteries();

		assertThat(actualNumber).isEqualTo(expectedNumber);
	}

	@Test
	@DisplayName("수동 구매한 로또가 제대로 저장되는지 확인")
	void purchaseManualLotteries() {
		final PurchaseAmount purchaseAmount = new PurchaseAmount(2000);
		final List<Lottery> inputtedManualLotteries = new ArrayList<>();
		inputtedManualLotteries.add(new Lottery(Parser.toLotteryNumberList(Arrays.asList(1, 2, 3, 4, 5, 6))));
		inputtedManualLotteries.add(new Lottery(Parser.toLotteryNumberList(Arrays.asList(7, 8, 9, 10, 11, 12))));
		final Lotteries manualLotteries = new Lotteries(inputtedManualLotteries);

		final PurchaseInformation purchaseInformation = new PurchaseInformation(purchaseAmount, manualLotteries, 2);

		final List<Lottery> actualManualLotteries = purchaseInformation.getManualLotteries();
		assertThat(actualManualLotteries).isEqualTo(inputtedManualLotteries);
	}
}
