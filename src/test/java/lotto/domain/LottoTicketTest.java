package lotto.domain;

import lotto.domain.exception.InvalidLottoTicketException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoTicketTest {
    private List<LottoNumber> lottoNumbers;

    @BeforeEach
    void setUp() {
        lottoNumbers = Arrays.asList(new LottoNumber(6), new LottoNumber(5), new LottoNumber(3)
                , new LottoNumber(4), new LottoNumber(1), new LottoNumber(2));
    }

    @Test
    void LottoNumber_list를_받아_LottoTicket_한_장_생성하기() {
        assertThat(new LottoTicket(lottoNumbers)).isEqualTo(new LottoTicket(lottoNumbers));
    }

    @Test
    void 로또_숫자가_6개인지_체크() {
        lottoNumbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3)
                , new LottoNumber(4), new LottoNumber(5));
        assertThrows(InvalidLottoTicketException.class, () -> {
            new LottoTicket(lottoNumbers);
        });
    }

    @Test
    void 중복된_로또_숫자_체크() {
        lottoNumbers = Arrays.asList(new LottoNumber(2), new LottoNumber(2), new LottoNumber(3)
                , new LottoNumber(4), new LottoNumber(5), new LottoNumber(6));
        assertThrows(InvalidLottoTicketException.class, () -> {
            new LottoTicket(lottoNumbers);
        });
    }

    @AfterEach
    void tearDown() {
        lottoNumbers = null;
    }
}