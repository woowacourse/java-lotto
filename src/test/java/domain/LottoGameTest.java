package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGameTest {

	@Test
	@DisplayName("랜덤 방식의 로또를 한 개 생성하는 경우")
	void createRandomLotto() {
		LottoGame lottoGame = new LottoGame();
		int money = 1000;

		List<Lotto> lottos = lottoGame.purchaseLottos(money);

		assertThat(lottos).isNotNull();
	}

	@Test
	@DisplayName("구입 금액 만큼의 로또를 생성하는 경우")
	void createRandomLottoByMoney() {
		LottoGame lottoGame = new LottoGame();
		int money = 5000;

		List<Lotto> lottos = lottoGame.purchaseLottos(money);

		assertThat(lottos.size()).isEqualTo(money / 1000);
	}
}
