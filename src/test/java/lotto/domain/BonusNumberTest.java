package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BonusNumberTest {

    @ParameterizedTest(name = "보너스 번호와 일치하는 티켓의 번호 개수 반환 - case : input {0} / count {1}")
    @CsvSource(value = {"1:true", "7:false"}, delimiter = ':')
    void getMatchCount(String input, boolean expected) {
        List<LottoNumber> lottoTicket = List.of(LottoNumber.NUMBER_1,
                LottoNumber.NUMBER_2,
                LottoNumber.NUMBER_3,
                LottoNumber.NUMBER_4,
                LottoNumber.NUMBER_5,
                LottoNumber.NUMBER_6);
        List<LottoNumber> winningNumbers = List.of(LottoNumber.NUMBER_10,
                LottoNumber.NUMBER_11,
                LottoNumber.NUMBER_12,
                LottoNumber.NUMBER_13,
                LottoNumber.NUMBER_14,
                LottoNumber.NUMBER_15);
        BonusNumber bonusNumber = new BonusNumber(input, winningNumbers);
        assertThat(bonusNumber.isMatch(lottoTicket)).isEqualTo(expected);
    }
}
