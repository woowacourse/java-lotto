package lotto.domain.money;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
	@DisplayName("money 생성자에 양수 입력이 들어올 때 객체 생성")
	@Test
	void constructor_InputNumber_CreatedMoney() {
		assertThat(new Money("14000"))
			.isInstanceOf(Money.class);
	}

	@DisplayName("money 생성자에 null이나 빈 스트링 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void validateNullOrEmpty_NullOrBlankInput_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new Money(input))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NULL_OR_EMPTY);
	}

	@DisplayName("money 생성자에 정수가 아닌 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"abc", "124.1"})
	void parseToInteger_NotInteger_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new Money(input))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NOT_INTEGER);
	}

	@DisplayName("money 생성자에 0이하인 정수 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"-1", "0"})
	void validatePositive_NotPositiveInteger_ExceptionThrown(String input) {
		assertThatThrownBy(() -> new Money(input))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.NOT_POSITIVE);
	}

	@DisplayName("money 생성자에 1000단위가 아닌 정수 입력이 들어올 때 InvalidLottoMoneyException 발생")
	@Test
	void validateUnit_NotThousandUnitInteger_ExceptionThrown() {
		assertThatThrownBy(() -> new Money("1001"))
			.isInstanceOf(InvalidLottoMoneyException.class)
			.hasMessage(InvalidLottoMoneyException.INVALID_UNIT);
	}

	@DisplayName("로또 금액이 들어오면 (구매하는) 전체 로또 장수를 반환")
	@ParameterizedTest
	@CsvSource(value = {"1000:1", "14000:14"}, delimiter = ':')
	void calculateCountOfLotto_ValidUnit_ReturnCountOfLotto(int money, int countOfLotto) {
		assertThat(new Money(money).calculateCountOfLotto(1000)).isEqualTo(countOfLotto);
	}

	@DisplayName("더한만큼의 돈을 반환하는 함수")
	@Test
	void add_AddedMoney_ReturnSum() {
		Money money = new Money("3000");
		Money addedMoney = new Money("5000");

		Money expectedMoney = new Money("8000");
		assertThat(money.add(addedMoney)).isEqualTo(expectedMoney);
	}

	@DisplayName("곱한만큼의 돈을 반환하는 함수")
	@Test
	void multiply_MultipliedCount_ReturnCalculatedMoney() {
		Money money = new Money("3000");
		int multiplyCount = 3;

		Money expectedMoney = new Money("9000");
		assertThat(money.multiply(multiplyCount)).isEqualTo(expectedMoney);
	}

	@DisplayName("총 수익금을 낸 돈으로 나눠서 수익률을 계산하는 함수")
	@Test
	void getWinningRatio_PaidLottoMoney_ReturnWinningRatio() {
		Money paidMoney = new Money("5000");
		Money winningMoney = new Money("15000");

		int expectedRatio = 300;
		assertThat(winningMoney.getWinningRate(paidMoney)).isEqualTo(expectedRatio);
	}
}
