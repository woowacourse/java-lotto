package lotto.domain.lottoticket;

import lotto.domain.Rank;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoresult.WinningLotto;
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
        lottoNumbers = Arrays.asList(LottoNumberPool.valueOf(6), LottoNumberPool.valueOf(5)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(4), LottoNumberPool.valueOf(1)
                , LottoNumberPool.valueOf(2));
    }

    @Test
    void LottoNumber_list를_받아_LottoTicket_한_장_생성하기() {
        assertThat(new LottoTicket(lottoNumbers)).isEqualTo(new LottoTicket(lottoNumbers));
    }

    @Test
    void 로또_숫자가_6개인지_체크() {
        lottoNumbers = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(4), LottoNumberPool.valueOf(5));
        assertThrows(InvalidLottoTicketException.class, () -> {
            new LottoTicket(lottoNumbers);
        });
    }

    @Test
    void 중복된_로또_숫자_체크() {
        lottoNumbers = Arrays.asList(LottoNumberPool.valueOf(2), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(4), LottoNumberPool.valueOf(5)
                , LottoNumberPool.valueOf(6));
        assertThrows(InvalidLottoTicketException.class, () -> {
            new LottoTicket(lottoNumbers);
        });
    }

    @Test
    void 로또_숫자_하나가_Ticket_안에_있는지_체크() {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);
        assertThat(lottoTicket.match(LottoNumberPool.valueOf(1))).isTrue();
        assertThat(lottoTicket.match(LottoNumberPool.valueOf(7))).isFalse();
    }

    @Test
    void 파라미터로_입력된_lottoTicket과_비교하여_해당_Rank를_반환() {
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        List<LottoNumber> numbers = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(7), LottoNumberPool.valueOf(8)
                , LottoNumberPool.valueOf(9));
        LottoTicket winningTicket = new LottoTicket(numbers);
        LottoNumber bonusBall = LottoNumberPool.valueOf(4);
        WinningLotto winningLotto = WinningLotto.of(winningTicket, bonusBall);

        assertThat(lottoTicket.match(winningLotto)).isEqualTo(Rank.FIFTH);
    }

    @AfterEach
    void tearDown() {
        lottoNumbers = null;
    }
}