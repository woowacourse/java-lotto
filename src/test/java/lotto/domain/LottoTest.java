package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * 선택한 로또 하나를 테스트하는 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/19
 */
public class LottoTest {
	static Stream<List<LottoNumber>> invalidSize() {
		return Stream.of(
				null,
				new ArrayList<>(),
				Arrays.asList(LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(43),
						LottoNumber.of(44)),
				Arrays.asList(LottoNumber.of(1),
						LottoNumber.of(2),
						LottoNumber.of(3),
						LottoNumber.of(4),
						LottoNumber.of(43),
						LottoNumber.of(44),
						LottoNumber.of(45)));
	}

	@Test
	@DisplayName("선택된 로또 번호 리스트가 정상적으로 생성된 경우")
	void constructor() {
		List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(3),
				LottoNumber.of(43),
				LottoNumber.of(44),
				LottoNumber.of(45));
		assertThat(new Lotto(lottoNumbers)).isInstanceOf(Lotto.class);
	}

	@ParameterizedTest
	@DisplayName("로또 번호 리스트의 크기가 올바르지 않은 경우")
	@MethodSource("invalidSize")
	void constructor_로또_번호_리스트의_크기가_올바르지_않은_경우(List<LottoNumber> lottoNumbers) {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Lotto(lottoNumbers));
	}

	@Test
	@DisplayName("중복된 수가 있는 경우")
	void constructor_중복된_수가_있는_경우() {
		List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(2),
				LottoNumber.of(43),
				LottoNumber.of(44),
				LottoNumber.of(45));
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Lotto(lottoNumbers));
	}

	@Test
	@DisplayName("수가 정렬되어 있는지 확인")
	void constructor_수가_정렬되어_있는지_확인() {
		Lotto lotto = new Lotto(Arrays.asList(LottoNumber.of(45),
				LottoNumber.of(44),
				LottoNumber.of(43),
				LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(3)));
		Lotto expected = new Lotto(Arrays.asList(LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(3),
				LottoNumber.of(43),
				LottoNumber.of(44),
				LottoNumber.of(45)));
		assertThat(lotto).isEqualTo(expected);
	}
}
