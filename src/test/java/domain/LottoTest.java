package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LottoTest {

	@Test
	void compare() {
		//given
		Lotto lotto = LottoFactory.createSelfNumberLotto(1, 2, 3, 4, 5, 6);
		Lotto winningTicket = LottoFactory.createSelfNumberLotto(1, 2, 3, 7, 8, 9);
		LottoNumber bonus = LottoNumber.get(13);
		//when
		//then
		assertThat(lotto.compare(winningTicket, bonus)).isEqualTo(Rank.FIFTH);
	}
}