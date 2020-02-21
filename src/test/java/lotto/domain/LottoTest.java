package lotto.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/19
 */
public class LottoTest {

	@Test
	void Lotto_올바른_동작_확인() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(10),
						LottoNumber.of(8),
						LottoNumber.of(44)
				)
		);

		assertThat(new Lotto(lottoNumbers)).isInstanceOf(Lotto.class);
	}

	@ParameterizedTest
	@NullSource
	void Lotto_null_예외처리(List<LottoNumber> input) {
		assertThatThrownBy(() -> new Lotto(input))
				.isInstanceOfAny(NullPointerException.class)
				.hasMessage("입력이 null 입니다.");
	}

	@ParameterizedTest
	@EmptySource
	void Lotto_빈_리스트_예외처리(List<LottoNumber> input) {
		assertThatThrownBy(() -> new Lotto(input))
				.isInstanceOfAny(IllegalArgumentException.class)
				.hasMessage("리스트에 요소가 없습니다.");
	}

	@Test
	void Lotto_로또번호가_6개_미만인_경우_예외처리() {
		List<LottoNumber> inputLengthNotEnough = new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(3),
						LottoNumber.of(2),
						LottoNumber.of(1),
						LottoNumber.of(10),
						LottoNumber.of(8)
				)
		);

		assertThatThrownBy(() -> new Lotto(inputLengthNotEnough))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("입력 리스트의 길이가 6이어야 합니다.");
	}

	@Test
	void Lotto_로또번호가_6개_초과인_경우_예외처리() {
		List<LottoNumber> inputLengthOverSix = new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(3),
						LottoNumber.of(2),
						LottoNumber.of(1),
						LottoNumber.of(10),
						LottoNumber.of(8),
						LottoNumber.of(21),
						LottoNumber.of(22),
						LottoNumber.of(20),
						LottoNumber.of(44),
						LottoNumber.of(3)
				)
		);

		assertThatThrownBy(() -> new Lotto(inputLengthOverSix))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("입력 리스트의 길이가 6이어야 합니다.");
	}

	@Test
	void Lotto_중복번호_입력시_예외처리() {
		List<LottoNumber> overlappedInput = new ArrayList<>(
				Arrays.asList(
						LottoNumber.of(3),
						LottoNumber.of(2),
						LottoNumber.of(1),
						LottoNumber.of(10),
						LottoNumber.of(10),
						LottoNumber.of(3)
				)
		);

		assertThatThrownBy(() -> new Lotto(overlappedInput))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("입력 리스트에 중복이 있습니다.");
	}
}
