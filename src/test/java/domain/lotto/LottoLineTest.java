package domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_LINE_NUMBER_COUNT_ERROR;
import static lotto.view.messages.ErrorMessages.LOTTO_LINE_NUMBER_DUPLICATE_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.WinningLotto;
import lotto.domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoLineTest {

    @Test
    @DisplayName("로또 번호 6개로 로또 라인을 생성한다.")
    void testCreateLottoLine() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1), new LottoNumber(2),
            new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(6)
        );

        LottoLine lottoLine = new LottoLine(lottoNumbers, true);
        for (LottoNumber lottoNumber : lottoNumbers) {
            assertThat(lottoLine.hasLottoNumber(lottoNumber)).isEqualTo(true);
        }
    }

    @Test
    @DisplayName("중복된 로또 번호들로 로또 라인 생성시 예외가 발생한다.")
    void testExceptionThrownDuplicatedLottoNumber() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1), new LottoNumber(1),
            new LottoNumber(2), new LottoNumber(3),
            new LottoNumber(4), new LottoNumber(6)
        );

        assertThatThrownBy(() -> new LottoLine(lottoNumbers, true))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_LINE_NUMBER_DUPLICATE_ERROR.getMessage());
    }

    @Test
    @DisplayName("로또 번호 개수가 6개가 아니면 로또 라인 생성시 예외가 발생한다.")
    void testExceptionThrownLottoNumberCountExcess6() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1), new LottoNumber(2),
            new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(6),
            new LottoNumber(7)
        );
        assertThatThrownBy(() -> new LottoLine(lottoNumbers, true))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_LINE_NUMBER_COUNT_ERROR.getMessage());
    }

    @Test
    @DisplayName("로또 1등 당첨 결과가 제대로 나온다.")
    void testLottoNumberMatch() {
        List<LottoNumber> lottoNumbers = Arrays.asList(
            new LottoNumber(1), new LottoNumber(2),
            new LottoNumber(3), new LottoNumber(4),
            new LottoNumber(5), new LottoNumber(6)
        );

        LottoLine lottoLine = new LottoLine(lottoNumbers, true);
        LottoNumber bonusLottoNumber = new LottoNumber(7);

        assertThat(lottoLine.checkLottoLine(
            new WinningLotto(lottoLine, bonusLottoNumber)
        )).isEqualTo(Rank.FIRST);
    }

}
