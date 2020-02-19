package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class ResultTest {
	@Test
	void calculateTotalPrize() {
		Map<LottoRank, Integer> map = new HashMap<>();
		map.put(LottoRank.FIRST, 1);
		map.put(LottoRank.SECOND, 2);
		map.put(LottoRank.THIRD, 3);
		map.put(LottoRank.FOURTH, 4);
		map.put(LottoRank.FIFTH, 5);

		long expectedTotalPrize = 0;
		for (LottoRank lottoRank : map.keySet()) {
			expectedTotalPrize += lottoRank.getTotal(map.get(lottoRank));
		}

		Result result = new Result(map);
		long prize = result.calculateTotalPrize();
		assertThat(prize).isEqualTo(expectedTotalPrize);
	}
}
