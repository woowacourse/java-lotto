package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoResultTest {
	private LottoResult lottoResult;

	@BeforeEach
	void setUp() {
		List<Rank> ranks = Arrays.asList(Rank.FIFTH, Rank.FOURTH);
		Money money = new Money("10000");
		lottoResult = new LottoResult(ranks, money);
	}

	@DisplayName("List<Rank>를 Map으로 잘 변환하는지 확인")
	@Test
	void getResultTest() {
		Map<Rank, Integer> results = new HashMap<>();
		results.put(Rank.NO_WIN, 0);
		results.put(Rank.FIFTH, 1);
		results.put(Rank.FOURTH, 1);
		results.put(Rank.THIRD, 0);
		results.put(Rank.SECOND, 0);
		results.put(Rank.FIRST, 0);
		assertThat(lottoResult.getResult()).isEqualTo(results);
	}

	@Test
	void getIncomeRateTest() {
		/* 10,000원으로 55,000원의 수익을 올림 */
		assertThat(lottoResult.getIncomeRate()).isEqualTo(550);
	}
}
