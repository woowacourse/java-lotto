package domain.lottery;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domain.lottery.Lottery;

public class LotteryTest {

	@Nested
	@DisplayName("로또 용지 하나에 포함된 숫자가")
	class CheckNumbersInLotteryTest {
		@Test
		@DisplayName("6개이면 올바른 로또이다.")
		void valid_lottery_number() {
			assertThatNoException().isThrownBy(() ->
				new Lottery(Arrays.asList(1, 2, 3, 4, 5, 6))
			);
		}

		@Test
		@DisplayName("6개가 아니면 올바르지 않은 로또이다.")
		void invalid_lottery_number() {
			assertThatThrownBy(() ->
				new Lottery(Arrays.asList(1, 2, 3, 4, 5))
			).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("로또 번호는 6개여야 합니다.");
		}
	}

	@Nested
	@DisplayName("로또 용지에 포함되 있는 숫자들이")
	class CheckNumberRangeTest {
		@Test
		@DisplayName("1~45 사이이면 통과")
		void valid_lottery_number_range() {
			assertThatNoException().isThrownBy(() -> new Lottery(Arrays.asList(1, 2, 4, 5, 6, 45)));
		}

		@Test
		@DisplayName("1~45 사이가 아니면 실패")
		void invalid_lottery_number_range() {
			assertThatThrownBy(() -> {
				new Lottery(Arrays.asList(-1, 0, 46, 3, 4, 5));
			}).isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("로또의 각 번호는 1~45 사이여야 합니다");
		}
	}
}