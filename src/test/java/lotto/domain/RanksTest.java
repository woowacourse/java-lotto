package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class RanksTest {
	@Test
	void checkGetCountOfWhenGivenSameRanks() {
		Ranks ranks = new Ranks(Arrays.asList(Rank.SECOND, Rank.SECOND));
		assertThat(ranks.getCountOf(Rank.SECOND)).isEqualTo(2);
	}

	@Test
	void checkGetTotalProfit() {
		Money money = new Money("10000");
		List<Rank> value = new ArrayList<>();
		value.add(Rank.FIFTH);
		Ranks ranks = new Ranks(value);

		assertThat(ranks.getTotalProfitComparedTo(money)).isEqualTo(0.5);
	}

	@Test
	void checkGetHavePrizes() {
		List<Rank> values = new ArrayList<>();
		values.add(Rank.FIFTH);
		values.add(Rank.FIRST);
		values.add(Rank.NONE);
		values.add(Rank.NONE);
		Ranks ranks = new Ranks(values);

		List<Rank> expected = Arrays.asList(Rank.FIFTH, Rank.FIRST);
		List<Rank> actual = ranks.getHavePrizes();

		assertThat(actual).isEqualTo(expected);
	}
}