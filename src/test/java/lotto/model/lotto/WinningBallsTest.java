package lotto.model.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.prize.Prize;

public class WinningBallsTest {
	List<String> winningNumbers;

	@BeforeEach
	void initializePrizeInformation() {
		winningNumbers = Arrays.asList("1", "2", "3", "4", "5", "6");
	}

	@DisplayName("당첨 번호와 보너스 번호가 일치하면 예외가 발생한다")
	@Test
	void duplicate_exception_bonus() {
		assertThatThrownBy(() -> {
			WinningBalls.from(winningNumbers, "6");
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다");
	}

	@DisplayName("3개가 일치하면 5등을 반환한다")
	@Test
	void match_3_fifth() {
		WinningBalls winningBalls = WinningBalls.from(winningNumbers, "10");
		Lotto lotto = Lotto.fromManual(Arrays.asList("1", "2", "3", "7", "8", "9"));

		assertThat(winningBalls.getPrize(lotto)).isEqualTo(Prize.FIFTH);
	}

	@DisplayName("3개가 일치하고 보너스가 일치해도 5등을 반환한다")
	@Test
	void match_3_bonus_fifth() {
		WinningBalls winningBalls = WinningBalls.from(winningNumbers, "9");
		Lotto lotto = Lotto.fromManual(Arrays.asList("1", "2", "3", "7", "8", "9"));

		assertThat(winningBalls.getPrize(lotto)).isEqualTo(Prize.FIFTH);
	}

	@DisplayName("4개가 일치하면 4등을 반환한다")
	@Test
	void match_4_fourth() {
		WinningBalls winningBalls = WinningBalls.from(winningNumbers, "10");
		Lotto lotto = Lotto.fromManual(Arrays.asList("1", "2", "3", "4", "8", "9"));

		assertThat(winningBalls.getPrize(lotto)).isEqualTo(Prize.FOURTH);
	}

	@DisplayName("5개가 일치하면 3등을 반환한다")
	@Test
	void match_5_third() {
		WinningBalls winningBalls = WinningBalls.from(winningNumbers, "10");
		Lotto lotto = Lotto.fromManual(Arrays.asList("1", "2", "3", "4", "5", "9"));

		assertThat(winningBalls.getPrize(lotto)).isEqualTo(Prize.THIRD);
	}

	@DisplayName("5개가 일치하고 보너스가 일치하면 2등을 반환한다")
	@Test
	void match_5_bonus_second() {
		WinningBalls winningBalls = WinningBalls.from(winningNumbers, "9");
		Lotto lotto = Lotto.fromManual(Arrays.asList("1", "2", "3", "4", "5", "9"));

		assertThat(winningBalls.getPrize(lotto)).isEqualTo(Prize.SECOND);
	}

	@DisplayName("6개가 일치하면 1등을 반환한다")
	@Test
	void match_6_first() {
		WinningBalls winningBalls = WinningBalls.from(winningNumbers, "10");
		Lotto lotto = Lotto.fromManual(Arrays.asList("1", "2", "3", "4", "5", "6"));

		assertThat(winningBalls.getPrize(lotto)).isEqualTo(Prize.FIRST);
	}
}
