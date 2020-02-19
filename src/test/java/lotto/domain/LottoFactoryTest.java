package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

	// @DisplayName("티켓 생성 테스트")
	// @Test
	// void createTest() {
	// 	Lotto ticket = LottoFactory.createLotto();
	// 	assertThat(ticket.numbers.size()).isEqualTo(6);
	//
	// 	Set<Integer> set = new HashSet<>();
	// 	for (int number : ticket.numbers) {
	// 		set.add(number);
	// 	}
	// 	assertThat(set.size()).isEqualTo(6);
	// }

	@DisplayName("전달받는 장 수만큼 로또생성")
	@Test
	void createLotteries() {
		int count = 4;
		List<Lotto> lottoList = LottoFactory.createLotteries(count);
		assertThat(lottoList.size()).isEqualTo(count);
	}
}
