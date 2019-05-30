package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTicketTest {
    private static List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            lottoNumbers.add(LottoNumber.createLottoNumber(i));
        }
    }

    @Test
    void 중복_번호() {
        lottoNumbers.add(LottoNumber.createLottoNumber(5));
        assertThrows(IllegalArgumentException.class, () -> {
            LottoTicket.createLottoTicket(lottoNumbers);
        });
    }

    @Test
    void 번호_개수_6개_아닌경우() {
        assertThrows(IllegalArgumentException.class, () -> {
            LottoTicket.createLottoTicket(lottoNumbers);
        });
    }
}