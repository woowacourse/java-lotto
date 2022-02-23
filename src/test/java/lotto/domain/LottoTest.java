package lotto.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoTest {

    @Test
    void getRank() {
        List<LottoNumber> lottoTicket = List.of(LottoNumber.NUMBER_1,
                LottoNumber.NUMBER_2,
                LottoNumber.NUMBER_3,
                LottoNumber.NUMBER_4,
                LottoNumber.NUMBER_5,
                LottoNumber.NUMBER_6);
        List<LottoNumber> winningNumbers = List.of(LottoNumber.NUMBER_1,
                LottoNumber.NUMBER_2,
                LottoNumber.NUMBER_3,
                LottoNumber.NUMBER_4,
                LottoNumber.NUMBER_5,
                LottoNumber.NUMBER_45);
        WinningNumbers winningNumbersInstance = new WinningNumbers("1,2,3,4,5,45");
        BonusNumber bonusNumber = new BonusNumber("6", winningNumbers);
        Lotto lotto = new Lotto(lottoTicket);
        Assertions.assertThat(lotto.getRank(winningNumbersInstance, bonusNumber)).isEqualTo(Rank.RANK_2);
    }
}
