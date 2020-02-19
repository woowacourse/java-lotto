package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class WinLottoTest {
	// @DisplayName("winLotto 초기화")
	// @Test
	// void init() {
	// 	String userInput = "1,2,3,4,5,6";
	// 	WinLotto winLotto = new WinLotto(userInput);
	//
	// 	assertThat(winLotto.getWinLotto()).contains(1, 2, 3, 4, 5, 6);
	// }

	@Test
	void compareTest() {
		WinLotto winLotto = new WinLotto("1,2,3,4,5,6", "7");
		Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		assertThat(winLotto.compare(lotto)).isEqualTo(6);

	}
}
