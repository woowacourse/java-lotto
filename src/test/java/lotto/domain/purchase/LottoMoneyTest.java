package lotto.domain.purchase;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMoneyTest {
	@ParameterizedTest
	@ValueSource(ints = {1_000, 3_000, 5_000, 15_000})
	void LottoMoney_validInputNumber_generateInstance(int value) {
		assertThat(new LottoMoney(value)).isInstanceOf(LottoMoney.class);
	}

	@Test
	void validateNegative_NegativeNumber_InvalidLottoMoneyExceptionThrown() {
		int value = -1;

		assertThatThrownBy(() -> new LottoMoney(value))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NEGATIVE);
	}

	@ParameterizedTest
	@ValueSource(ints = {1_001, 1_010, 1_100})
	void validateUnit_InvalidUnitNumber_InvalidLottoMoneyExceptionThrown(int value) {
		assertThatThrownBy(() -> new LottoMoney(value))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.INVALID_UNIT);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validateNullOrEmpty_NullOrEmptyInput_InvalidLottoMoneyExceptionThrown(String value) {
		assertThatThrownBy(() -> LottoMoney.valueOf(value))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NULL_OR_EMPTY);
	}

	@ParameterizedTest
	@ValueSource(strings = {"abc", "124.1"})
	void valueOf_NotintTypeNumber_InvalidLottoMoneyExceptionThrown(String value) {
		assertThatThrownBy(() -> LottoMoney.valueOf(value))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NOT_INTEGER);
	}

	@ParameterizedTest
	@CsvSource(value = {"10000,10", "15000,15", "37000,37"})
	void countPurchasingLottoTicket_ValidUnitLottoMoney_DivideByUnit(int value, int value1) {
		LottoMoney lottoMoney = new LottoMoney(value);

		PurchasingCount actual = lottoMoney.generatePurchasingLottoTicketCount();

		PurchasingCount expected = new PurchasingCount(value1);

		assertThat(actual).isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(ints = {1_000, 2_000, 3_000})
	void addBy_AddedLottoMoney_SumThisLottoMoneyAndInputAddedLottoMoney(int value) {
		int initLottoMoney = 1_000;
		LottoMoney lottoMoney = new LottoMoney(initLottoMoney);
		LottoMoney addedLottoMoney = new LottoMoney(value);

		LottoMoney actual = lottoMoney.addBy(addedLottoMoney);

		LottoMoney expected = new LottoMoney(initLottoMoney + value);
		assertThat(actual).isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 4})
	void multiplyBy_MultiplyLottoMoney_MultiplyThisLottoMoneyByInputOperand(int value) {
		int initLottoMoney = 1_000;
		LottoMoney lottoMoney = new LottoMoney(initLottoMoney);

		LottoMoney actual = lottoMoney.multiplyBy(value);

		LottoMoney expected = new LottoMoney(initLottoMoney * value);
		assertThat(actual).isEqualTo(expected);
	}

	@ParameterizedTest
	@ValueSource(ints = {5_000, 15_000, 100_000})
	void measureWinningRate_AmountOfPurchaseLottoMoney_WinningRateByPercentage(int value) {
		int winningLottoMoney = 3_000_000;
		LottoMoney lottoMoney = new LottoMoney(winningLottoMoney);
		LottoMoney amountOfPurchaseLottoMoney = new LottoMoney(value);

		int actual = lottoMoney.measureWinningRate(amountOfPurchaseLottoMoney);

		int expected = (winningLottoMoney / value) * 100;
		assertThat(actual).isEqualTo(expected);
	}
}
