package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

	@DisplayName("티켓 생성 테스트")
	@Test
	void createTest() {
		Lotto ticket = LottoFactory.create();
		assertThat(ticket.numbers.size()).isEqualTo(6);

		Set<Integer> set = new HashSet<>();
		for (int number : ticket.numbers) {
			set.add(number);
		}
		assertThat(set.size()).isEqualTo(6);
	}
}
