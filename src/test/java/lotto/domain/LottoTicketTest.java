package lotto.domain;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoticket.InvalidLottoTicketException;
import lotto.domain.lottoticket.LottoTicket;
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

    @Test
    void 로또_숫자_하나가_Ticket_안에_있는지_체크() {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        assertThat(lottoTicket.match(new LottoNumber(1))).isTrue();
        assertThat(lottoTicket.match(new LottoNumber(7))).isFalse();
    }

    @Test
    void 파라미터로_입력된_lottoTicket과_비교하여_해당_Rank를_반환() {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        List<LottoNumber> numbers = Arrays.asList(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3)
                , new LottoNumber(7), new LottoNumber(8), new LottoNumber(9));
        LottoTicket winningLotto = new LottoTicket(numbers);

        LottoNumber bonusBall = new LottoNumber(4);

        assertThat(lottoTicket.match(winningLotto, bonusBall)).isEqualTo(Rank.FIFTH);
    }

    @AfterEach
    void tearDown() {
        lottoNumbers = null;
    }
}