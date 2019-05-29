package lotto.model.lotto;

import lotto.model.lotto.exception.InvalidLottoTicketException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTicketTest {
    @Test
    void 중복되지_번호일_때_확인() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(1)
                , new LottoNumber(2)
                , new LottoNumber(3)
                , new LottoNumber(4)
                , new LottoNumber(5)
                , new LottoNumber(6));
        assertDoesNotThrow(() -> new LottoTicket(lottoNumbers));
    }

    @Test
    void 중복된_번호일_때_예외_발생() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(45)
                , new LottoNumber(2)
                , new LottoNumber(3)
                , new LottoNumber(4)
                , new LottoNumber(5)
                , new LottoNumber(45));
        assertThrows(InvalidLottoTicketException.class, () -> new LottoTicket(lottoNumbers));
    }

    @Test
    void 번호가_6개가_아닐_때_예외_발생() {
        List<LottoNumber> lottoNumbers = Arrays.asList(new LottoNumber(45)
                , new LottoNumber(2)
                , new LottoNumber(3)
                , new LottoNumber(4)
                , new LottoNumber(45));
        assertThrows(InvalidLottoTicketException.class, () -> new LottoTicket(lottoNumbers));
    }
}
