package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ProfitCalculatorTest {
	@Test
	void checkCalculate() {
		Money money = new Money("10000");
		List<Rank> value = new ArrayList<>();
		value.add(Rank.FIFTH);
		Ranks ranks = new Ranks(value);

		assertThat(ProfitCalculator.calculate(money, ranks)).isEqualTo(0.5);
	}
}