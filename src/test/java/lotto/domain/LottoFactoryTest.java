package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

public class LottoFactoryTest {

	@DisplayName("사용자가 입력한 돈에 맞게 로또 생성")
	@Test
	void createLotteries() {
		String userInputMoney = "13000";
		List<Lotto> lottoList = LottoFactory.createLotteries(new Money(userInputMoney));
		assertThat(lottoList.size()).isEqualTo(13);

		userInputMoney = "5000";
		List<Lotto> lottoList2 = LottoFactory.createLotteries(new Money(userInputMoney));
		assertThat(lottoList2.size()).isEqualTo(5);
	}
}
