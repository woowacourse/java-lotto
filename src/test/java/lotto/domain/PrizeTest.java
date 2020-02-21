package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PrizeTest {
	@ParameterizedTest
	@CsvSource(value = {"2000000000,3", "10,5", "100000000,0"})
	void multiplyTest(long beforePrize, long multiplier) {
		Prize prize = Prize.of(beforePrize);
		long expected = beforePrize * multiplier;
		assertThat(prize.multiply(multiplier)).extracting("prize").isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"1000,1000,2000","10000,90000,100000","500,100,600"})
	void plusTest(long firstOperand, long secondOperand, long expected) {
		Prize firstPrize = Prize.of(firstOperand);
		Prize secondPrize = Prize.of(secondOperand);
		assertThat(firstPrize.plus(secondPrize)).isEqualTo(Prize.of(expected));
	}

	@ParameterizedTest
	@CsvSource(value = {"10000,10000,100","2000,10000,20"})
	void findProfits(long prize, int money, long expected) {
		Prize prizeValue = Prize.of(prize);
		Money moneyValue = new Money(money);
		assertThat(prizeValue.findProfits(moneyValue)).isEqualTo(expected);
	}
}