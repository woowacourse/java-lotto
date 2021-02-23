package lotto.domain;

import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.LottoTickets;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Prize;
import lotto.domain.result.WinningLotto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {
    private LottoTicket firstPrizeTicket;
    private LottoTicket secondPrizeTicket;
    private LottoTickets lottoTickets;

    @BeforeEach
    void init() {
        firstPrizeTicket = new LottoTicket(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(7)));

        secondPrizeTicket = new LottoTicket(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(8)));

        lottoTickets = new LottoTickets(Arrays.asList(firstPrizeTicket, secondPrizeTicket));
    }

    @Test
    @DisplayName("당첨 티켓 분류")
    void checkWinningTicket() {
        LottoTicket winningTicket = new LottoTicket(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(7)));

        LottoNumber bonusNumber = LottoNumber.valueOf(8);
        WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        LottoResult lottoResult = winningLotto.checkPrizes(lottoTickets);
        assertThat(lottoResult.lottoResult().get(0)).isEqualTo(Prize.FIRST_PRIZE);
        assertThat(lottoResult.lottoResult().get(1)).isEqualTo(Prize.SECOND_PRIZE);
    }

    @Test
    @DisplayName("당첨번호와 보너스 번호가 중복되는 지 검증")
    void checkDuplicateBonusNumber() {
        LottoTicket winningTicket = new LottoTicket(Arrays.asList(
                LottoNumber.valueOf(1),
                LottoNumber.valueOf(2),
                LottoNumber.valueOf(3),
                LottoNumber.valueOf(4),
                LottoNumber.valueOf(5),
                LottoNumber.valueOf(7)));
        LottoNumber bonusNumber = LottoNumber.valueOf(3);
        assertThatThrownBy(() -> {
            WinningLotto winningLotto = new WinningLotto(winningTicket, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}