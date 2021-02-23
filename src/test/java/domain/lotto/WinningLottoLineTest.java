package domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_LINE_NUMBER_BONUS_LOTTO_NUMBER_DUPLICATE_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.WinningLottoLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoLineTest {

    LottoLine lottoLine;
    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = Arrays.asList(
            new LottoNumber(1), new LottoNumber(2),
            new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(6)
        );
        lottoLine = new LottoLine(lottoNumbers);
    }

    @Test
    @DisplayName("로또 번호 매칭과 보너스 볼 매칭이 올바르게 동작한다.")
    void testCreateWinningLottoLine() {
        LottoNumber bonusLottoNumber = new LottoNumber(13);
        WinningLottoLine winningLottoLine = new WinningLottoLine(lottoLine, bonusLottoNumber);
        List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1), new LottoNumber(2),
            new LottoNumber(3), new LottoNumber(11),
            new LottoNumber(12), new LottoNumber(13)
        );

        assertThat(winningLottoLine.getLottoNumberMatchCount(lottoNumbers)).isEqualTo(3);
        assertThat(winningLottoLine.isContainBonusLottoNumber(lottoNumbers)).isEqualTo(true);
    }

    @Test
    @DisplayName("로또 번호와 보너스 번호가 중복될 경우 예외가 발생한다.")
    void testCreateWinningLottoLineException() {
        for (int i = 0; i < 6; i++) {
            LottoNumber bonusLottoNumber = new LottoNumber(i + 1);
            assertThatThrownBy(() -> new WinningLottoLine(lottoLine, bonusLottoNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(
                    LOTTO_LINE_NUMBER_BONUS_LOTTO_NUMBER_DUPLICATE_ERROR.getMessage());
        }
    }

}
