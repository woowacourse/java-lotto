package lotto.model;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.number.LottoBall;

public class LottoTest {

	@DisplayName("Lotto에 LottoBall이 6개가 아니면 예외를 반환한다")
	@Test
	void count_exception() {
		String[] manualLottoNumbers = {"1", "2", "3", "4"};

		assertThatThrownBy(() -> {
			new Lotto(Lotto.generateManual(manualLottoNumbers));
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 6개로 입력해야 합니다");
	}

	@DisplayName("Lotto 의 LottoBall 에 중복된 값이 있으면 예외를 반환한다")
	@Test
	void duplicate_exception() {
		String[] manualLottoNumber = {"1", "1", "2", "3", "4", "5"};

		assertThatThrownBy(() -> {
			new Lotto(Lotto.generateManual(manualLottoNumber));
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 한 개의 로또 내에서 숫자가 중복될 수 없습니다");
	}

	@DisplayName("Lotto 의 LottoBall 에 1 미만 값이 있으면 예외를 반환한다")
	@Test
	void bound_exception_under() {
		String[] manualLottoNumber = {"-100", "1", "2", "3", "4", "5"};

		assertThatThrownBy(() -> {
			new Lotto(Lotto.generateManual(manualLottoNumber));
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
	}

	@DisplayName("Lotto 의 LottoBall 에 45 초과 값이 있으면 예외를 반환한다")
	@Test
	void bound_exception_over() {
		String[] manualLottoNumber = {"100", "1", "2", "3", "4", "5"};

		assertThatThrownBy(() -> {
			new Lotto(Lotto.generateManual(manualLottoNumber));
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
	}

	@DisplayName("같은 숫자 리스트로 생성된 로또여도, 다른 값을 갖는다")
	@Test
	void generate_two_lottos() {
		List<LottoBall> lottoBalls1 = Arrays.asList(
				LottoBall.from("1"),
				LottoBall.from("2"),
				LottoBall.from("3"),
				LottoBall.from("4"),
				LottoBall.from("5"),
				LottoBall.from("6")
		);
		Lotto lotto1 = new Lotto(lottoBalls1);

		List<LottoBall> lottoBalls2 = Arrays.asList(
				LottoBall.from("6"),
				LottoBall.from("4"),
				LottoBall.from("1"),
				LottoBall.from("5"),
				LottoBall.from("2"),
				LottoBall.from("3")
		);
		Lotto lotto2 = new Lotto(lottoBalls2);

		assertThat(lotto1).isNotEqualTo(lotto2);
	}

	@DisplayName("6개의 숫자를 생성한다")
	@Test
	void generate_six_number() {
		Lotto lotto = new Lotto(Lotto.generateAuto());

		assertThat(lotto.getLotto().size()).isEqualTo(6);
	}

	@DisplayName("6개의 숫자가 오름차순으로 정렬된다")
	@Test
	void sort_ascending() {
		Lotto lotto = new Lotto(Lotto.generateAuto());
		List<LottoBall> numbers = lotto.getLotto();

		for (int index = 0; index < (numbers.size() - 1); index++) {
			assertThat(numbers.get(index).getNumber() < numbers.get(index + 1).getNumber()).isTrue();
		}
	}
}
