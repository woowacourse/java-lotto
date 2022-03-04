package lotto.model.ball;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningBallsTest {

	@DisplayName("숫자가 7개일 때 예외가 발생한다")
	@Test
	public void size_7_exception() {
		assertThatThrownBy(() -> {
			WinningBalls.from(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7")));
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 6개로 입력해야 합니다");
	}

	@DisplayName("숫자가 5개일 때 예외가 발생한다")
	@Test
	public void size_5_exception() {
		assertThatThrownBy(() -> {
			WinningBalls.from(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5")));
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 6개로 입력해야 합니다");
	}

	@DisplayName("당첨 번호끼리 숫자 5가 중복될 때 예외가 발생한다")
	@Test
	public void duplicate_exception() {
		assertThatThrownBy(() -> {
			WinningBalls.from(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "5")));
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 한 개의 로또 내에서 숫자가 중복될 수 없습니다");
	}

	@DisplayName("당첨된 번호에 포함된 숫자일 때 true를 반환한다")
	@Test
	public void match_true() {
		WinningBalls winningBalls = WinningBalls.from(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6")));

		assertThat(winningBalls.match(LottoBall.from("1"))).isTrue();
	}

	@DisplayName("당첨된 번호에 포함된 숫자가 아닐 때 false를 반환한다")
	@Test
	public void match_false() {
		WinningBalls winningBalls = WinningBalls.from(new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6")));

		assertThat(winningBalls.match(LottoBall.from("7"))).isFalse();
	}
}
