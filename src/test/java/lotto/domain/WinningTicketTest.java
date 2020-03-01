package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class WinningTicketTest {

    @Test
    @DisplayName("몇개 맞추는지 test")
    void hitLottoBallTest() {
        LottoTicket lottoTicket = new LottoTicket("1,2,3,4,5,6");
        LottoBall lottoBall = new LottoBall("6");

        WinningTicket winningTicket = new WinningTicket(lottoTicket.getLottoTicket(),lottoBall);

        Assertions.assertThat(winningTicket.hitLottoBall(lottoTicket)).isEqualTo(6);
    }

    @Test
    @DisplayName("보너스볼을 판별하는지 테스")
    void hitBonusBall() {
        LottoTicket lottoTicket = new LottoTicket("1,2,3,4,5,6");
        LottoBall lottoBall = new LottoBall("6");

        WinningTicket winningTicket = new WinningTicket(lottoTicket.getLottoTicket(),lottoBall);

        Assertions.assertThat(winningTicket.hitBonusBall(lottoTicket)).isTrue();
    }

    @Test
    @DisplayName("보너스볼을 판별하는지 테스")
    void false_hitBonusBall() {
        LottoTicket lottoTicket = new LottoTicket("1,2,3,4,5,6");
        LottoBall lottoBall = new LottoBall("7");

        WinningTicket winningTicket = new WinningTicket(lottoTicket.getLottoTicket(),lottoBall);

        Assertions.assertThat(winningTicket.hitBonusBall(lottoTicket)).isFalse();
    }
}