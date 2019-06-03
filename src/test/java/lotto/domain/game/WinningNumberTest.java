package lotto.domain.game;

import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningNumberTest {
    @Test
    public void 당첨_번호_6개_일치(){
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)),LottoNumber.of(7));
        LottoTicket lottoTicket = LottoTicket.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(winningLotto.countMatchedNumber(lottoTicket)).isEqualTo(6);
    }
    @Test
    public void 당첨_번호_2개_일치(){
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(5),LottoNumber.of(10),LottoNumber.of(15),LottoNumber.of(20),LottoNumber.of(25)),LottoNumber.of(26));
        LottoTicket lottoTicket = LottoTicket.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(winningLotto.countMatchedNumber(lottoTicket)).isEqualTo(2);
    }

    @Test
    public void 보너스_번호_포함(){
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(5),LottoNumber.of(10),LottoNumber.of(15),LottoNumber.of(20),LottoNumber.of(25)),LottoNumber.of(26));
        LottoTicket lottoTicket = LottoTicket.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(26)));
        assertThat(winningLotto.isMatchedBonus(lottoTicket)).isTrue();
    }

    @Test
    public void 보너스_번호_미포함(){
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(5),LottoNumber.of(10),LottoNumber.of(15),LottoNumber.of(20),LottoNumber.of(25)),LottoNumber.of(26));
        LottoTicket lottoTicket = LottoTicket.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(winningLotto.isMatchedBonus(lottoTicket)).isFalse();
    }

    @Test
    public void 보너스_번호_당첨_번호_겹침_에러(){
        assertThrows(IllegalArgumentException.class,()->{
            WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(5),LottoNumber.of(10),LottoNumber.of(15),LottoNumber.of(20),LottoNumber.of(25)),LottoNumber.of(25));
        });
    }


}