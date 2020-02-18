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

    public static LottoTicket getLottoTicketFixture() {
        Set<LottoNumber> lottoNumbers = getProperLottoNumbersFixture();
        return new LottoTicket(lottoNumbers);
    }

}
