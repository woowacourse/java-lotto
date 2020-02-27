package lotto.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import lotto.domain.Number;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningLotto;

class GameResultTest {
	private PurchaseMoney money;
	private Lotto winningNumbers;
	private Number bonus;
	private WinningLotto winningLotto;
	private Lottos lottos;

	@BeforeEach
	void init() {
		money = new PurchaseMoney("4000");
		winningNumbers = LottoFactory.createManualSingle("1,2,3,4,5,6");
		bonus = Number.of("7");
		winningLotto = new WinningLotto(winningNumbers, bonus);
		lottos = LottoFactory.createManualAndAuto(Arrays.asList("1,2,3,4,5,7"), 0);
	}

	@Test
	@DisplayName("정상적인 수익률을 반환하는지")
	void getProfitTest() {
		assertThat(new GameResult(winningLotto, lottos).getEarningMoney(money)
		).isEqualTo((3000000 / money.getPurchaseMoney()) * 100);
	}
}