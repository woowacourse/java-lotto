package domain;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.WinningLottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    private List<LottoNumber> lottoNumbers;
    private Lotto lotto;
    private List<Integer> winningNumbers;
    private LottoGenerator lottoGenerator;

    @BeforeEach
    void setUp() {
        lottoGenerator = new WinningLottoGenerator();

        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        lotto = new Lotto(lottoNumbers);

        winningNumbers = new ArrayList<>();
        for (int i = 2; i <= 7; i++) {
            winningNumbers.add(i);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 7})
    @DisplayName("Lotto의 size가 6이 아닐 경우 예외를 발생한다.")
    public void lotto_사이즈_5일경우(int lastIndex) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= lastIndex; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.LOTTO_SIZE_IS_NOT_SIX);
    }

    @Test
    @DisplayName("Lotto의 숫자들과 당첨숫자를 비교하여 결과를 반환한다.")
    void judge_보너스볼_불일치() {
        WinningLotto winningLotto = new WinningLotto(lottoGenerator.generateWinningLotto(winningNumbers), new LottoNumber(10));
        Result actual = lotto.judge(winningLotto);
        Result expected = new Result(5, false);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Lotto의 숫자들과 당첨숫자를 비교하여 결과를 반환한다.")
    void judge_보너스볼_일치() {
        WinningLotto winningLotto = new WinningLotto(lottoGenerator.generateWinningLotto(winningNumbers), new LottoNumber(1));
        Result actual = lotto.judge(winningLotto);
        Result expected = new Result(5, true);
        assertThat(actual).isEqualTo(expected);
    }
}
