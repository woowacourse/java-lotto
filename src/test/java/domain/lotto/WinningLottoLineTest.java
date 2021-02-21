package domain.lotto;

import static lotto.view.messages.ErrorMessages.LOTTO_LINE_NUMBER_BONUS_LOTTO_NUMBER_DUPLICATE_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.WinningLottoLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoLineTest {

    @Test
    @DisplayName("로또 번호와 로또 보너스 번호가 중복될 경우 예외를 발생시킨다.")
    void testLottoLineNumberLottoNumberDuplicated() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2),
            new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));

        LottoLine winningLottoLine = new LottoLine(lottoNumbers);
        LottoNumber bonusLottoNumber = new LottoNumber(1);

        assertThatThrownBy(() -> new WinningLottoLine(winningLottoLine, bonusLottoNumber))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(LOTTO_LINE_NUMBER_BONUS_LOTTO_NUMBER_DUPLICATE_ERROR.getMessage());
    }

}
