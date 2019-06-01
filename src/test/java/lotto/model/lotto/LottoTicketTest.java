package lotto.model.lotto;

import lotto.model.lotto.exception.InvalidLottoTicketException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTicketTest {
    @Test
    void 중복되지_번호일_때_확인() {
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumberRepository.fromNumber(1)
                , LottoNumberRepository.fromNumber(2)
                , LottoNumberRepository.fromNumber(3)
                , LottoNumberRepository.fromNumber(4)
                , LottoNumberRepository.fromNumber(5)
                , LottoNumberRepository.fromNumber(6));
        assertDoesNotThrow(() -> LottoTicket.from(new TreeSet<>(lottoNumbers)));
    }

    @Test
    void 중복된_번호일_때_예외_발생() {
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumberRepository.fromNumber(45)
                , LottoNumberRepository.fromNumber(2)
                , LottoNumberRepository.fromNumber(3)
                , LottoNumberRepository.fromNumber(4)
                , LottoNumberRepository.fromNumber(5)
                , LottoNumberRepository.fromNumber(45));
        assertThrows(InvalidLottoTicketException.class, () -> LottoTicket.from(new TreeSet<>(lottoNumbers)));
    }

    @Test
    void 번호가_6개가_아닐_때_예외_발생() {
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumberRepository.fromNumber(45)
                , LottoNumberRepository.fromNumber(2)
                , LottoNumberRepository.fromNumber(3)
                , LottoNumberRepository.fromNumber(4)
                , LottoNumberRepository.fromNumber(45));
        assertThrows(InvalidLottoTicketException.class, () -> LottoTicket.from(new TreeSet<>(lottoNumbers)));
    }
}
