package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.exception.InvalidLottoMoneyException;
import lotto.domain.lotto.LottoMoney;

class LottoMoneyTest {
	@DisplayName("money 생성자에 null이나 빈 스트링 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void validateNullOrEmpty_NullOrBlankInput_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new LottoMoney(input))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NULL_OR_EMPTY);
	}

	@DisplayName("money 생성자에 양수 입력이 들어올 때 객체 생성")
	@Test
	void constructor_InputNumber_CreatedMoney() {
		assertThat(new LottoMoney("14000"))
			.isInstanceOf(LottoMoney.class);
	}

	@DisplayName("money 생성자에 정수가 아닌 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"abc", "124.1"})
	void parseToInteger_NotInteger_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new LottoMoney(input))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NOT_INTEGER);
	}

	@DisplayName("money 생성자에 0이하인 정수 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"-1", "0"})
	void validatePositive_NotPositiveInteger_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new LottoMoney(input))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NOT_POSITIVE);
	}

	@DisplayName("money 생성자에 1000단위가 아닌 정수 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@Test
	void validateUnit_NotThousandUnitInteger_ExceptionThrown() {
		assertThatThrownBy(() -> new LottoMoney("1001"))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.INVALID_UNIT);
	}

	@DisplayName("money 생성자에 최대금액 이상의 정수 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@Test
	void validateMaxBound_OutOfMaxBoundInteger_ExceptionThrown() {
		assertThatThrownBy(() -> new LottoMoney("200000"))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.OUT_OF_BOUND);
	}

	@DisplayName("getNumberOfLotto에 LottoMoney를 입력하면 로또 가격으로 나눈 값이 반환")
	@Test
	void getNumberOfLotto_LottoMoney_NumberOfLotto() {
		LottoMoney lottoMoney = new LottoMoney("2000");
		assertThat(lottoMoney.getPurchasedLottoCount()).isEqualTo(2);
	}

	@DisplayName("add에 더할 LottoMoney를 입력하면 더한 LottoMoney 반환")
	@Test
	void add_AddedLottoMoney_ReturnSumLottoMoney() {
		LottoMoney lottoMoney = new LottoMoney("3000");
		LottoMoney addedLottoMoney = new LottoMoney("5000");

		LottoMoney expectedLottoMoney = new LottoMoney("8000");
		assertThat(lottoMoney.add(addedLottoMoney)).isEqualTo(expectedLottoMoney);
	}

	@DisplayName("multiply에 곱할 LottoMoney를 입력하면 곱한 LottoMoney 반환")
	@Test
	void multiply_MultipliedCount_ReturnCalculatedMoney() {
		LottoMoney lottoMoney = new LottoMoney("3000");
		int multiplyCount = 3;

		LottoMoney expectedLottoMoney = new LottoMoney("9000");
		assertThat(lottoMoney.multiply(multiplyCount)).isEqualTo(expectedLottoMoney);
	}

	@DisplayName("getWinningRatio에 구입한 로또 금액을 입력하면 총 수익률 반환")
	@Test
	void getWinningRatio_PaidLottoMoney_ReturnWinningRatio() {
		LottoMoney winningLottoMoney = new LottoMoney("15000");
		LottoMoney paidLottoMoney = new LottoMoney("5000");

		int expectedRatio = 300;
		assertThat(winningLottoMoney.getWinningRatio(paidLottoMoney)).isEqualTo(expectedRatio);
	}
}
