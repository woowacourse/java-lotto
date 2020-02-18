package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoManagerTest {
	@DisplayName("티켓한장번호 당첨비교")
	@Test
	void compareTicket() {
		List<Integer> ticket = Arrays.asList(1, 2, 3, 4, 5, 6);
		List<Integer> winNumber = Arrays.asList(5, 6, 7, 8, 9, 10);
		assertThat(LottoManager.compareTicket(ticket, winNumber)).isEqualTo(2);

		winNumber = Arrays.asList(5, 6, 7, 8, 1, 10);
		assertThat(LottoManager.compareTicket(ticket, winNumber)).isEqualTo(3);
	}
}
