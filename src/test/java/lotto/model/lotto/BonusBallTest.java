package lotto.model.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusBallTest {
	private WinningBalls winningBalls;

	@BeforeEach
	public void initializeStandardNumbers() {
		List<String> winningNumbers = Arrays.asList("1", "2", "3", "4", "5", "6");
		this.winningBalls = WinningBalls.from(winningNumbers);
	}

	@DisplayName("보너스 번호가 1 미만이면 예외가 발생한다")
	@Test
	void bound_exception_under() {
		assertThatThrownBy(() -> {
			BonusBall.from(LottoBall.from("0"), winningBalls);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
	}

	@DisplayName("보너스 번호가 45 초과이면 예외가 발생한다")
	@Test
	void bound_exception_over() {
		assertThatThrownBy(() -> {
			BonusBall.from(LottoBall.from("46"), winningBalls);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
	}

	@DisplayName("보너스 번호가 당첨 번호들과 중복되면 예외가 발생한다")
	@Test
	void duplicate_exception() {
		assertThatThrownBy(() -> {
			BonusBall.from(LottoBall.from("1"), winningBalls);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다");
	}

	@DisplayName("로또에 보너스 볼이 포함된다면 true를 반환한다")
	@Test
	void match_true() {
		Lotto lotto = Lotto.fromManual(Arrays.asList("10", "11", "12", "13", "14", "15"));
		BonusBall bonusBall = BonusBall.from(LottoBall.from("10"), winningBalls);

		assertThat(bonusBall.match(lotto)).isTrue();
	}

	@DisplayName("로또에 보너스 볼이 포함된다면 true를 반환한다")
	@Test
	void match_false() {
		Lotto lotto = Lotto.fromManual(Arrays.asList("10", "11", "12", "13", "14", "15"));
		BonusBall bonusBall = BonusBall.from(LottoBall.from("16"), winningBalls);

		assertThat(bonusBall.match(lotto)).isFalse();
	}
}
