package lotto.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import lotto.domain.lottoRank.LottoRank;
import lotto.domain.lottoTicket.LottoTicket;

class StringUtilTest {
	@Test
	void joiningLottoNumbersAt_LottoTicket_JoiningLottoTicketAndWrappingPrefixAndSuffix() {
		LottoTicket value = LottoTicket.valueOf("1, 2, 3, 4, 5, 6");

		String actual = StringUtil.joiningLottoNumbersAt(value);

		String expected = "[1, 2, 3, 4, 5, 6]";
		assertThat(actual).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(value = LottoRank.class, names = {"SECOND"})
	void generateFormOfLottoRank_SecondLottoRankAndLottoRankCount_generateResultMessage(LottoRank lottoRank) {
		Long value = 3L;

		String actual = StringUtil.generateFormOfLottoRank(lottoRank, value);

		String expected = String.format("%d개 일치, 보너스 볼 일치(%d원) - %d개",
			lottoRank.getMatchCount(),
			lottoRank.getWinningLottoMoney(),
			value);
		assertThat(actual).isEqualTo(expected);
	}

	@ParameterizedTest
	@EnumSource(
		value = LottoRank.class,
		names = {"SECOND"},
		mode = EnumSource.Mode.EXCLUDE)
	void generateFormOfLottoRank_DefaultLottoRankAndLottoRankCount_generateResultMessage(LottoRank lottoRank) {
		Long value = 3L;

		String actual = StringUtil.generateFormOfLottoRank(lottoRank, value);

		String expected = String.format("%d개 일치 (%d원) - %d개",
			lottoRank.getMatchCount(),
			lottoRank.getWinningLottoMoney(),
			value);
		assertThat(actual).isEqualTo(expected);
	}
}
