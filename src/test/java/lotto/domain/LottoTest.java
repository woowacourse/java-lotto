package lotto.domain;

import lotto.exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThatThrownBy;

public class LottoTest {
	static Stream<Arguments> generateInput_wrongAmount() {
		return Stream.of(Arguments.of(Arrays.asList(1, 2, 3, 4, 5)),
				Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
	}

	static Stream<Arguments> generateInput_winningNumber() {
		return Stream.of(Arguments.of(Arrays.asList("1", "2", "3", "4", "5", "6"), 7, "1등"),
				Arguments.of(Arrays.asList("1", "3", "4", "5", "6", "7"), 2, "2등(보너스볼 일치)"),
				Arguments.of(Arrays.asList("1", "3", "4", "5", "6", "7"), 14, "3등"),
				Arguments.of(Arrays.asList("1", "4", "5", "6", "7", "8"), 12, "4등"),
				Arguments.of(Arrays.asList("1", "5", "6", "7", "8", "9"), 43, "5등"),
				Arguments.of(Arrays.asList("1", "6", "7", "8", "9", "10"), 34, "미당첨"));
	}

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("로또가 null이나 빈값인 경우")
    void checkIfLottoIsNullOrEmpty(List<LottoNumber> value) {
        assertThatThrownBy(() -> new Lotto(value))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining("null이나 빈 값");
    }

	@Test
	@DisplayName("숫자가 중복되는 경우")
	void checkDuplicateNumber() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(1));
        lottoNumbers.add(new LottoNumber(2));
        lottoNumbers.add(new LottoNumber(3));
        lottoNumbers.add(new LottoNumber(4));
        lottoNumbers.add(new LottoNumber(5));

        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining("중복");
    }

    @ParameterizedTest
    @MethodSource("generateInput_wrongAmount")
    @DisplayName("로또 공 갯수가 맞지 않는 경우")
    void checkLottoBallAmountMismatch(List<LottoNumber> value) {
        assertThatThrownBy(() -> new Lotto(value))
                .isInstanceOf(LottoException.class)
                .hasMessageContaining("개여야 합니다");
    }

    @ParameterizedTest
    @MethodSource("generateInput_winningNumber")
    @DisplayName("당첨 등수를 제대로 맞추는지 체크")
    void checkWiningPrize(List<String> winningNumbers, LottoNumber bonusNumber, String expectedPrize) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        Lotto lotto = new Lotto(lottoNumbers);
        WinningLotto winningNumber = new WinningLotto(winningNumbers, bonusNumber);
        assertThat(lotto.findLottoPrize(winningNumber).getDescription()).isEqualTo(expectedPrize);
    }
}
