package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoResultsTest {
	@Test
	void getRankCount() {
		List<LottoRank> lottoRanks = Arrays.asList(
				LottoRank.FIRST,
				LottoRank.FIRST,
				LottoRank.SECOND,
				LottoRank.THIRD,
				LottoRank.THIRD,
				LottoRank.FOURTH
		);
		LottoResults lottoResults = new LottoResults(lottoRanks);
		assertThat(lottoResults.getRankCount(LottoRank.FIRST)).isEqualTo(2);
		assertThat(lottoResults.getRankCount(LottoRank.SECOND)).isEqualTo(1);
		assertThat(lottoResults.getRankCount(LottoRank.THIRD)).isEqualTo(2);
		assertThat(lottoResults.getRankCount(LottoRank.FOURTH)).isEqualTo(1);
	}
}
