package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.result.LottoRank;

class LottoRankTest {
	@DisplayName("맞춘 갯수와 보너스 번호 일치 유무를 받아 LottoRank 객체 반환")
	@Test
	void of_MatchCountAndHasBonusNumber_ReturnLottoRank() {
		assertThat(LottoRank.of(5, true)).isEqualTo(LottoRank.SECOND);
		assertThat(LottoRank.of(4, false)).isEqualTo(LottoRank.FOURTH);
		assertThat(LottoRank.of(4, true)).isEqualTo(LottoRank.FOURTH);
	}

	@DisplayName("isLottoRankOf에 일치하는 LottoRank 입력이 들어올 때 True 반환")
	@Test
	void isLottoRankOf_SameLottoRank_ReturnTrue() {
		assertThat(LottoRank.SECOND.isLottoRankOf(LottoRank.SECOND)).isTrue();
	}

	@DisplayName("isLottoRankOf에 일치하지 않는 LottoRank 입력이 들어올 때 False 반환")
	@Test
	void isLottoRankOf_OtherLottoRank_ReturnFalse() {
		assertThat(LottoRank.SECOND.isLottoRankOf(LottoRank.FIFTH)).isFalse();
	}
}
