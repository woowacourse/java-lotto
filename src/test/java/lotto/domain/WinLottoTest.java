package lotto.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinLottoTest {
	@DisplayName("winLotto 초기화")
	@Test
	void init() {
		String userInput = "1,2,3,4,5,6";
		WinLotto winLotto = new WinLotto(userInput);

		assertThat(winLotto.getWinLotto()).contains(1, 2, 3, 4, 5, 6);
	}
}
