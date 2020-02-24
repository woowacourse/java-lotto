package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

	@DisplayName("전달받는 장 수만큼 로또생성")
	@Test
	void createLotteries() {
		Money money = new Money("13000");
		List<Lotto> lottoList = LottoFactory.createLotteries(money);
		assertThat(lottoList.size()).isEqualTo(13);

		money = new Money("5000");
		List<Lotto> lottoList2 = LottoFactory.createLotteries(money);
		assertThat(lottoList2.size()).isEqualTo(5);
	}
}
