package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import lotto.domain.LottoMoney.InvalidLottoMoneyException;
import lotto.domain.LottoMoney.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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

	@Test
	void purchaseLotto_Money_NumberOfLotto() {
		LottoMoney lottoMoney = new LottoMoney("2000");
		assertThat(lottoMoney.calculateNumberOfLotto()).isEqualTo(2);
	}

	@DisplayName("더한만큼의 돈을 반환하는 함수")
	@Test
	void add_AddedMoney_ReturnSum() {
		LottoMoney lottoMoney = new LottoMoney("3000");
		LottoMoney addedLottoMoney = new LottoMoney("5000");

		LottoMoney expectedLottoMoney = new LottoMoney("8000");
		assertThat(lottoMoney.add(addedLottoMoney)).isEqualTo(expectedLottoMoney);
	}

	@DisplayName("곱한만큼의 돈을 반환하는 함수")
	@Test
	void multiply_MultipliedCount_ReturnCalculatedMoney() {
		LottoMoney lottoMoney = new LottoMoney("3000");
		int multiplyCount = 3;

		LottoMoney expectedLottoMoney = new LottoMoney("9000");
		assertThat(lottoMoney.multiply(multiplyCount)).isEqualTo(expectedLottoMoney);
	}

	@DisplayName("총 수익금을 낸 돈으로 나눠서 수익률을 계산하는 함수")
	@Test
	void getWinningRatio_PaidLottoMoney_ReturnWinningRatio() {
		LottoMoney paidLottoMoney = new LottoMoney("5000");
		LottoMoney winningLottoMoney = new LottoMoney("15000");

		int expectedRatio = 300;
		assertThat(winningLottoMoney.getWinningRate(paidLottoMoney)).isEqualTo(expectedRatio);
	}
}
