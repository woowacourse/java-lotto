package lotto.domain.lottoTicket;

import lotto.util.ConvertInput;
import lotto.util.DuplicationLottoException;
import lotto.util.InvalidSizeLottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {

    static Stream<Arguments> generateLottoNumbers() {
        return Stream.of(
                Arguments.of(new Lotto(ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 6")), 6),
                Arguments.of(new Lotto(ConvertInput.convertLottoNumbers("1, 8, 3, 4, 5, 2")), 5),
                Arguments.of(new Lotto(ConvertInput.convertLottoNumbers("1, 2, 7, 4, 3, 8")), 4));
    }

    @Test
    @DisplayName("로또번호 중복검사")
    void validateDuplication() {
        List<LottoNumber> input1 = ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 5");
        List<LottoNumber> input2 = ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 6");

        assertThatThrownBy(() -> Lotto.validateDuplication(input1))
                .isInstanceOf(DuplicationLottoException.class);
        assertThatCode(() -> Lotto.validateDuplication(input2))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 숫자 6개인지 검사")
    void validateSizeTest() {
        List<LottoNumber> input = ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 6, 7");

        assertThatThrownBy(() -> Lotto.validateSize(input))
                .isInstanceOf(InvalidSizeLottoException.class);
    }

    @ParameterizedTest
    @DisplayName("로또 일치 개수 계산")
    @MethodSource("generateLottoNumbers")
    void checkCorrectNumberTest(Lotto input, int result) {
        WinningLotto winningLotto = new WinningLotto(ConvertInput.convertLottoNumbers("1, 2, 3, 4, 5, 6"), 7);
        assertThat(input.countCorrectNumber(winningLotto)).isEqualTo(result);
    }

}
