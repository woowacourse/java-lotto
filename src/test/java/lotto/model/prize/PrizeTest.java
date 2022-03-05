package lotto.model.prize;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeTest {

	@DisplayName("1개가 일치하고 보너스가 false면 NONE 을 반환한다")
	@Test
	void none_1_false() {
		assertThat(Prize.getPrize(1, false)).isEqualTo(Prize.NONE);
	}

	@DisplayName("1개가 일치하고 보너스가 true면 NONE 을 반환한다")
	@Test
	void none_1_true() {
		assertThat(Prize.getPrize(1, true)).isEqualTo(Prize.NONE);
	}

	@DisplayName("3개가 일치하고 보너스가 false면 FIFTH 을 반환한다")
	@Test
	void fifth_3_false() {
		assertThat(Prize.getPrize(3, false)).isEqualTo(Prize.FIFTH);
	}

	@DisplayName("3개가 일치하고 보너스가 true면 FIFTH 을 반환한다")
	@Test
	void fifth_3_true() {
		assertThat(Prize.getPrize(3, true)).isEqualTo(Prize.FIFTH);
	}

	@DisplayName("4개가 일치하고 보너스가 false면 FOURTH 을 반환한다")
	@Test
	void fourth_4_false() {
		assertThat(Prize.getPrize(4, false)).isEqualTo(Prize.FOURTH);
	}

	@DisplayName("4개가 일치하고 보너스가 true면 FOURTH 을 반환한다")
	@Test
	void fourth_4_true() {
		assertThat(Prize.getPrize(4, true)).isEqualTo(Prize.FOURTH);
	}

	@DisplayName("5개가 일치하고 보너스가 false면 THIRD 을 반환한다")
	@Test
	void third_5_false() {
		assertThat(Prize.getPrize(5, false)).isEqualTo(Prize.THIRD);
	}

	@DisplayName("5개가 일치하고 보너스가 true면 SECOND 을 반환한다")
	@Test
	void second_5__true() {
		assertThat(Prize.getPrize(5, true)).isEqualTo(Prize.SECOND);
	}

	@DisplayName("6개가 일치하고 보너스가 false면 FIRST 을 반환한다")
	@Test
	void first_6_false() {
		assertThat(Prize.getPrize(6, false)).isEqualTo(Prize.FIRST);
	}

	@DisplayName("6개가 일치하고 보너스가 true면 FIRST 을 반환한다")
	@Test
	void first_6_true() {
		assertThat(Prize.getPrize(6, true)).isEqualTo(Prize.FIRST);
	}
}
