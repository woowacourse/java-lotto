package domain;

import domain.Lotto.Lotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.ManualLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessage;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoGeneratorTest {

    @Test
    @DisplayName("AutoLottoGenerator에서 생성된 로또의 길이가 6인지 확인한다.")
    void generateAutoLotto() {
        Lotto actual = new AutoLottoGenerator().generateLotto();
        int expected = 6;
        assertThat(actual.getLotto().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("WinningLottoGenerator에서 생성된 로또의 길이가 6인지 확인한다.")
    void generateWinningLotto() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto actual = new ManualLottoGenerator().generateLotto(lottoNumbers);
        int expected = 6;
        assertThat(actual.getLotto().size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("AutoLottoGenreator에서 수동 생성하는 메서드를 사용하면 예외를 발생한다.")
    void generateAutoExceptionTest() {
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoGenerator lottoGenerator = new AutoLottoGenerator();
        assertThatThrownBy(() -> lottoGenerator.generateLotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.MANUAL_LOTTO_GENERATE_LIMIT);
    }

    @Test
    @DisplayName("ManualLottoGenerator에서 자동 생성하는 메서드를 사용하면 예외를 발생한다.")
    void generateManualExceptionTest() {
        LottoGenerator lottoGenerator = new ManualLottoGenerator();
        assertThatThrownBy(() -> lottoGenerator.generateLotto())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.AUTO_LOTTO_GENERATE_LIMIT);
    }
}