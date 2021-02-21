package domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_LINE_NUMBER_COUNT_DUPLICATE_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.WinningLottoLine;
import lotto.domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoLineTest {

    @Test
    @DisplayName("중복된 로또 번호가 있을 경우 예외를 발생 시킨다.")
    void testExceptionThrownDuplicatedLottoNumber() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(1),
            new LottoNumber(2), new LottoNumber(2), new LottoNumber(5), new LottoNumber(6));

        assertThatThrownBy(() -> new LottoLine(lottoNumbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_LINE_NUMBER_COUNT_DUPLICATE_ERROR.getMessage());
    }


    @Test
    @DisplayName("로또 번호가 6개보다 많을때 예외를 발생 시킨다.")
    void testExceptionThrownLottoNumberCountExcess6() {
        List<LottoNumber> lottoNumbers = Arrays
            .asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6), new LottoNumber(7));

        assertThatThrownBy(() -> new LottoLine(lottoNumbers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_LINE_NUMBER_COUNT_DUPLICATE_ERROR.getMessage());
    }

    @Test
    @DisplayName("로또 1등 당첨 결과가 제대로 나온다.")
    void testLottoNumberMatch() {
        List<LottoNumber> lottoNumbers = Arrays
            .asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3),
                new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        LottoLine lottoLine = new LottoLine(lottoNumbers);
        LottoNumber bonusLottoNumber = new LottoNumber(7);

        assertThat(lottoLine.checkLottoLine(new WinningLottoLine(new LottoLine(lottoNumbers), bonusLottoNumber)))
            .isEqualTo(Rank.FIRST);
    }

}
