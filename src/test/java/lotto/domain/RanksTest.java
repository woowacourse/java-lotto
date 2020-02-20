package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class RanksTest {
	@Test
	void checkGetCountOfWhenGivenSameRanks() {
		Ranks ranks = new Ranks(Arrays.asList(Rank.SECOND, Rank.SECOND));
		assertThat(ranks.getCountOf(Rank.SECOND)).isEqualTo(2);
	}
}