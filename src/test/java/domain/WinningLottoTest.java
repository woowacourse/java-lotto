package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {
    @DisplayName("당첨 번호와 보너스 볼을 묶은 객체를 생성한다.")
    @Test
    public void createWinningLotto() {
        WinningLotto winningLotto = new WinningLotto(
                LottoTicket.valueOf(Arrays.asList("3", "4", "7", "8", "10", "16")),
                new LottoNumber("22"));

        assertThat(winningLotto).isInstanceOf(WinningLotto.class);
    }

    @DisplayName("당첨 번호에 보너스 볼 번호가 있는 경우 예외를 발생시킨다.")
    @Test
    public void createWinningLottoException() {
        assertThatThrownBy(() -> {
            new WinningLotto(
                LottoTicket.valueOf(Arrays.asList("3", "4", "7", "8", "10", "16")),
                new LottoNumber("10"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("맞춘 개수에 맞는 Rank 객체가 리턴된다. - 1등")
    @Test
    public void calculateLottoFirstRank() {
        WinningLotto winningLotto = new WinningLotto(
                LottoTicket.valueOf(Arrays.asList("3", "4", "7", "8", "10", "16")),
                new LottoNumber("22"));

        Rank rank = winningLotto.calculateRank(LottoTicket.valueOf(Arrays.asList("3", "4", "7", "8", "10", "16")));

        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("맞춘 개수에 맞는 Rank 객체가 리턴된다. - 2등")
    @Test
    public void calculateLottoSecondRank() {
        WinningLotto winningLotto = new WinningLotto(
                LottoTicket.valueOf(Arrays.asList("3", "4", "7", "8", "10", "16")),
                new LottoNumber("22"));

        Rank rank = winningLotto.calculateRank(LottoTicket.valueOf(Arrays.asList("3", "4", "7", "8", "10", "22")));

        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("맞춘 개수에 맞는 Rank 객체가 리턴된다. - 없는 경우")
    @Test
    public void calculateLottoNoneRank() {
        WinningLotto winningLotto = new WinningLotto(
                LottoTicket.valueOf(Arrays.asList("3", "4", "7", "8", "10", "16")),
                new LottoNumber("22"));

        Rank rank = winningLotto.calculateRank(LottoTicket.valueOf(Arrays.asList("1", "2", "5", "7", "11", "15")));

        assertThat(rank).isEqualTo(Rank.NOTHING);
    }
}
