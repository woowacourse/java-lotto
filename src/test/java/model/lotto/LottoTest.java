package model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.lottonumber.LottoNumber;
import model.lottonumber.LottoNumbers;
import model.result.Rank;
import model.winningnumber.WinningLottoNumberDTO;
import strategy.LottoNumbersGenerationStrategy;

public class LottoTest {

	@Test
	@DisplayName("당첨 번호와 로또의 비교값이 5인 경우")
	void compareWinningNumberWithLottoFive() {
		LottoNumbersGenerationStrategy lottoNumbersGenerationStrategy = new LottoNumbersGenerationStrategy() {
			@Override
			public List<LottoNumber> generate(int size) {
				return Arrays.asList(1, 2, 3, 4, 5, 6).stream()
					.map(number -> LottoNumber.valueOf(number))
					.collect(Collectors.toList());
			}
		};
		LottoNumbersGenerationStrategy lottoNumbersGenerationStrategy2 = new LottoNumbersGenerationStrategy() {
			@Override
			public List<LottoNumber> generate(int size) {
				return Arrays.asList(1, 2, 3, 4, 5, 7).stream()
					.map(number -> LottoNumber.valueOf(number))
					.collect(Collectors.toList());
			}
		};
		Lotto lotto = new Lotto(LottoNumbers.from(lottoNumbersGenerationStrategy));
		WinningLottoNumberDTO winningLottoNumberDTO = new WinningLottoNumberDTO(
			LottoNumbers.from(lottoNumbersGenerationStrategy2),
			LottoNumber.valueOf(8));
		assertThat(lotto.match(winningLottoNumberDTO)).isEqualTo(Rank.THIRD);
	}

	@Test
	@DisplayName("당첨 번호와 로또의 비교값이 5이고, 보너스가 존재하는 경우")
	void compareWinningNumberWithLotto() {
		LottoNumbersGenerationStrategy lottoNumbersGenerationStrategy = new LottoNumbersGenerationStrategy() {
			@Override
			public List<LottoNumber> generate(int size) {
				return Arrays.asList(1, 2, 3, 4, 5, 6).stream()
					.map(number -> LottoNumber.valueOf(number))
					.collect(Collectors.toList());
			}
		};
		LottoNumbersGenerationStrategy lottoNumbersGenerationStrategy2 = new LottoNumbersGenerationStrategy() {
			@Override
			public List<LottoNumber> generate(int size) {
				return Arrays.asList(1, 2, 3, 4, 5, 7).stream()
					.map(number -> LottoNumber.valueOf(number))
					.collect(Collectors.toList());
			}
		};
		Lotto lotto = new Lotto(LottoNumbers.from(lottoNumbersGenerationStrategy));

		WinningLottoNumberDTO winningLottoNumberDTO = new WinningLottoNumberDTO(
			LottoNumbers.from(lottoNumbersGenerationStrategy2),
			LottoNumber.valueOf(6));
		assertThat(lotto.match(winningLottoNumberDTO)).isEqualTo(Rank.SECOND);
	}
}
