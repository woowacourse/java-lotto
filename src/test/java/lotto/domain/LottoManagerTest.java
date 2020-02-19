package lotto.domain;

import org.junit.jupiter.api.Test;

public class LottoManagerTest {
	// @DisplayName("티켓한장번호 당첨비교")
	// @Test
	// void compareTicket() {
	// 	LottoManager lottoManager = new LottoManager();
	// 	List<Integer> ticket = Arrays.asList(1, 2, 3, 4, 5, 6);
	// 	Lotto winNumber = (Lotto)Arrays.asList(5, 6, 7, 8, 9, 10);
	// 	assertThat(lottoManager.compareTicket(ticket, winNumber)).isEqualTo(2);
	//
	// 	winNumber = (Lotto)Arrays.asList(5, 6, 7, 8, 1, 10);
	// 	assertThat(lottoManager.compareTicket(ticket, winNumber)).isEqualTo(3);
	// }

	@Test
	void 통계() {
		WinLotto winLotto = new WinLotto("1,2,3,4,5,6", "10");

	}
}
