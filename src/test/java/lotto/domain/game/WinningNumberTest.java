package lotto.domain.game;

import lotto.domain.ticket.LottoNumber;
import lotto.domain.ticket.LottoTicket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningNumberTest {
    @Test
    public void 당첨_1등(){
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)),LottoNumber.of(7));
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(Rank.FIRST);
    }
    @Test
    public void 당첨_2등(){
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(7)),LottoNumber.of(6));
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(Rank.SECOND);
    }

    @Test
    public void 당첨_3등(){
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(7)),LottoNumber.of(45));
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(Rank.THIRD);
    }

    @Test
    public void 당첨_4등(){
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(7),LottoNumber.of(8)),LottoNumber.of(9));
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(Rank.FOURTH);
    }

    @Test
    public void 당첨_5등(){
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(7),LottoNumber.of(8),LottoNumber.of(9)),LottoNumber.of(10));
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(Rank.FIFTH);
    }

    @Test
    public void 당첨_실패(){
        WinningLotto winningLotto = WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(7)),LottoNumber.of(6));
        LottoTicket lottoTicket = new LottoTicket(Arrays.asList(LottoNumber.of(1),LottoNumber.of(2),LottoNumber.of(3),LottoNumber.of(4),LottoNumber.of(5),LottoNumber.of(6)));
        assertThat(winningLotto.getRank(lottoTicket)).isEqualTo(Rank.SECOND);
    }

    @Test
    public void 보너스_번호_당첨_번호_겹침_에러(){
        assertThrows(IllegalArgumentException.class,()->{
            WinningLotto.of(Arrays.asList(LottoNumber.of(1),LottoNumber.of(5),LottoNumber.of(10),LottoNumber.of(15),LottoNumber.of(20),LottoNumber.of(25)),LottoNumber.of(25));
        });
    }


}