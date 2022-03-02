package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.number.LottoBall;

class LottoTest {

	@DisplayName("Lotto에 LottoBall이 6개가 아니면 예외를 반환한다")
	@Test
	void count_exception() {
		List<LottoBall> lottoBalls = new ArrayList<>();
		lottoBalls.add(LottoBall.from("1"));
		lottoBalls.add(LottoBall.from("1"));
		lottoBalls.add(LottoBall.from("1"));
		lottoBalls.add(LottoBall.from("1"));

		assertThatThrownBy(() -> {
			new Lotto(lottoBalls);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 6개로 입력해야 합니다");
	}

	@DisplayName("Lotto 의 LottoBall 에 중복된 값이 있으면 예외를 반환한다")
	@Test
	void duplicate_exception() {
		List<LottoBall> lottoBalls = new ArrayList<>();
		lottoBalls.add(LottoBall.from("1"));
		lottoBalls.add(LottoBall.from("1"));
		lottoBalls.add(LottoBall.from("2"));
		lottoBalls.add(LottoBall.from("3"));
		lottoBalls.add(LottoBall.from("4"));
		lottoBalls.add(LottoBall.from("5"));

		assertThatThrownBy(() -> {
			new Lotto(lottoBalls);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 한 개의 로또 내에서 숫자가 중복될 수 없습니다");
	}

}