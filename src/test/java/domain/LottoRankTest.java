package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoRankTest {

	@ParameterizedTest
	@CsvSource(value = {"6:FIRST", "5:THIRD", "4:FOURTH", "3:FIFTH", "2:NOTHING"}, delimiter = ':')
	@DisplayName("2등 제외 매칭 개수와 보너스 유무로 등수 반환 검증")
	void findRankByMatchingCountAndBonus_ExceptSecond(String input, String expected) {
		assertThat(LottoRank.valueOf(Integer.parseInt(input), false))
			.isEqualTo(LottoRank.valueOf(expected));
	}

	@Test
	@DisplayName("2등 매칭 개수 보너스 유무로 등수 반환 검증")
	void findRankByMatchingCountAndBonus_SECOND() {
		assertThat(LottoRank.valueOf(5, true)).isEqualTo(LottoRank.SECOND);
	}

	@Test
	@DisplayName("상금이 있는 값만 반환 검증")
	void lottoRankValues_WithoutNOTHING() {
		assertThat(LottoRank.valuesWithPrize())
			.containsExactly(LottoRank.FIFTH, LottoRank.FOURTH,
				LottoRank.THIRD, LottoRank.SECOND, LottoRank.FIRST);
	}
}
