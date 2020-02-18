package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoNumbersTest {
	private LottoNumbers lottoNumbers;

	@BeforeEach
	void setup() {
		lottoNumbers = new LottoNumbers(Arrays.asList(
				LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(5),
				LottoNumber.of(43),
				LottoNumber.of(44),
				LottoNumber.of(45)));
	}

	@Test
	@DisplayName("당첨 로또가 정상적으로 생성되는지 확인")
	void constructor() {
		assertThat(new WinningLottoNumbers(lottoNumbers, LottoNumber.of(6))).isInstanceOf(WinningLottoNumbers.class);
	}

	@Test
	@DisplayName("로또 번호와 보너스가 중복될 경우")
	void constructor_로또_번호와_보너스가_중복() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
				() -> new WinningLottoNumbers(lottoNumbers, LottoNumber.of(45)));
	}

	@Test
	@DisplayName("로또 결과 확인")
	void match() {
		WinningLottoNumbers winningLottoNumbers = new WinningLottoNumbers(lottoNumbers, LottoNumber.of(9));
		assertThat(winningLottoNumbers.match(new LottoNumbers(Arrays.asList(
				LottoNumber.of(1),
				LottoNumber.of(2),
				LottoNumber.of(5),
				LottoNumber.of(43),
				LottoNumber.of(44),
				LottoNumber.of(45)
		)))).isEqualTo(LottoRank.FIRST);
	}
}
