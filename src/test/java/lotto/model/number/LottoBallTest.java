package lotto.model.number;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoBallTest {

	@DisplayName("같은 숫자의 LottoBall은 equals()에 의해 같은 값으로 인지된다")
	@Test
	void equals_override() {
		LottoBall lottoBall1 = LottoBall.from("1");
		LottoBall lottoBall2 = LottoBall.from("1");

		assertThat(lottoBall1).isEqualTo(lottoBall2);
	}

	@DisplayName("LottoBall 을 sort()를 이용해 정렬하면 숫자 크기 순으로 정렬된다")
	@Test
	void compareTo_override() {
		List<LottoBall> lottoBalls = new ArrayList<>(Arrays.asList(
				LottoBall.from("8"),
				LottoBall.from("1"),
				LottoBall.from("5"),
				LottoBall.from("2"),
				LottoBall.from("9"),
				LottoBall.from("3")
		));

		Collections.sort(lottoBalls);

		for (int i = 0; i < lottoBalls.size() - 1; i++) {
			assertThat(lottoBalls.get(i).getNumber() < lottoBalls.get(i + 1).getNumber()).isTrue();
		}
	}

	@DisplayName("로또 번호가 숫자가 아니면 예외가 발생한다")
	@Test
	void type_exception() {
		assertThatThrownBy(() -> {
			LottoBall.from("일");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 숫자로만 입력해주세요");
	}

	@DisplayName("로또 번호가 1 이상 45 이하가 아니면 예외가 발생한다")
	@Test
	void bound_exception() {
		assertThatThrownBy(() -> {
			LottoBall.from("100");
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
	}

	@DisplayName("같은 숫자일 경우 equals()를 사용하면 같은 값으로 인식된다")
	@Test
	void equals_test() {
		assertThat(LottoBall.from("1")).isEqualTo(LottoBall.from("1"));
	}
}
