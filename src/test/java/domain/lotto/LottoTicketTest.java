package domain.lotto;

import static domain.lotto.LottoNumberTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class LottoTicketTest {

    @Test
    void testLottoTicket() {
        Set<LottoNumber> lottoNumbers = getProperLottoNumbersFixture();
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        assertThat(lottoTicket);
    }

    @Test
    void createLottoTicketThrowException() {
        Set<LottoNumber> lottoNumbers = getNotProperLottoNumbersFixture();
        assertThatThrownBy(() -> new LottoTicket(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void countNumberOfSameLottoNumber() {
       Set<LottoNumber> lottoNumbers = getProperLottoNumbersFixture();
       LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
       LottoTicket sameLottoTicket = new LottoTicket(lottoNumbers);
       int numberOfSameLottoNumber = 6;

       assertThat(lottoTicket.countMatches(sameLottoTicket)).isEqualTo(numberOfSameLottoNumber);
    }

    @Test
    void countMathcesWhenMachingFive() {
        Set<LottoNumber> lottoNumbers = getProperLottoNumbersFixture();
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        Set<LottoNumber> lottoNumberFromOneToSevenWithoutSix = getFromOneToSevenWithoutSixFixture();
        LottoTicket targetLottoTicket = new LottoTicket(lottoNumberFromOneToSevenWithoutSix);

        assertThat(lottoTicket.countMatches(targetLottoTicket)).isEqualTo(5);
    }

    public static LottoTicket getLottoTicketFixture() {
        Set<LottoNumber> lottoNumbers = getProperLottoNumbersFixture();
        return new LottoTicket(lottoNumbers);
    }
}
