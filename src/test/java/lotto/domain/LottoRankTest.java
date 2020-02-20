package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class LottoRankTest {
	@Test
	void getWinningMoney_MatchLottoNumberCountAndContainBonusNumber_getWinningMoney() {
		Lotto winningLotto = new Lotto(Arrays.asList(
			LottoNumber.valueOf(1),
			LottoNumber.valueOf(2),
			LottoNumber.valueOf(3),
			LottoNumber.valueOf(4),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(6)));
		LottoNumber bonusNumber = LottoNumber.valueOf(7);
		WinningLotto winningLotto1 = new WinningLotto(winningLotto, bonusNumber);

		Lotto purchasedLotto = new Lotto(Arrays.asList(
			LottoNumber.valueOf(1),
			LottoNumber.valueOf(2),
			LottoNumber.valueOf(3),
			LottoNumber.valueOf(4),
			LottoNumber.valueOf(5),
			LottoNumber.valueOf(6)));

		assertThat(LottoRank.getWinningMoney(purchasedLotto.getMatchCount(winningLotto),
			purchasedLotto.isContains(bonusNumber)))
			.isEqualTo(LottoMoney.FIRST_RANK);
	}
}
