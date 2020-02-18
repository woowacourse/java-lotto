package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketFactoryTest {

	@DisplayName("티켓 생성 테스트")
	@Test
	void createTest() {
		List<Integer> ticket = TicketFactory.create();
		assertThat(ticket.size()).isEqualTo(6);

		Set<Integer> set = new HashSet<>();
		for (int number : ticket) {
			set.add(number);
		}
		assertThat(set.size()).isEqualTo(6);
	}
}
