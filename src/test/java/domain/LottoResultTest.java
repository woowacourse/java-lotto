package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultTest {

	@DisplayName("총 당첨금 확인")
	@Test
	void count_ranks() {
		//given
		Map<Rank,Integer> ranks = Rank.getMap();
		ranks.replace(Rank.FIFTH, 4);
		//when
		LottoResult result = new LottoResult(ranks);
		//then
		assertThat(result.calculateTotalProfit()).isEqualTo(20000);
	}
}