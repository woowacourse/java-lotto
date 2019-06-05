package lotto.domain.lottoresult;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoticket.LottoTicket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        List<LottoNumber> winningTicketNumbers = Arrays.asList(LottoNumberPool.valueOf(1), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(10), LottoNumberPool.valueOf(11)
                , LottoNumberPool.valueOf(12));
        LottoTicket winningTicket = new LottoTicket(winningTicketNumbers);
        LottoNumber bonusBall = LottoNumberPool.valueOf(4);
        winningLotto = WinningLotto.of(winningTicket, bonusBall);
    }

    @Test
    void WinningTicket에_번호_하나가_포함되는지_체크() {
        assertThat(winningLotto.matchWinningTicket(LottoNumberPool.valueOf(1))).isTrue();
    }

    @Test
    void LottoNumber_List중에_BonusBall이_있는지_체크() {
        List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumberPool.valueOf(4), LottoNumberPool.valueOf(2)
                , LottoNumberPool.valueOf(3), LottoNumberPool.valueOf(10), LottoNumberPool.valueOf(11)
                , LottoNumberPool.valueOf(12));
        assertThat(winningLotto.isBonusBallIn(lottoNumbers)).isTrue();
    }

    @AfterEach
    void tearDown() {
        winningLotto = null;
    }
}