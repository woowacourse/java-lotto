package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumbersTest {
    private WinningNumbers winningNumbers;

    @BeforeEach
    public void setUp() {
        winningNumbers = WinningNumbers.of(
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoNumber.from(7));
    }

    @DisplayName("당첨 번호와 보너스 볼을 묶은 객체를 생성한다")
    @Test
    public void createWinningNumbers() {
        WinningNumbers winningNumberTest = WinningNumbers.of(
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoNumber.from(7));
        assertThat(winningNumberTest).isInstanceOf(WinningNumbers.class);
    }

    @DisplayName("당첨 번호에 보너스 볼 번호가 있는 경우 예외를 발생시킨다")
    @Test
    public void createWinningNumbersException() {
        assertThatThrownBy(() -> {
            WinningNumbers.of(
                    LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
                    LottoNumber.from(6));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("맞춘 개수에 맞는 Rank객체가 리턴된다. - 1등")
    @Test
    public void calculateLottoFirstRank() {
        Rank rank = winningNumbers.calculateRank(LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)));
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @DisplayName("맞춘 개수에 맞는 Rank객체가 리턴된다. - 2등")
    @Test
    public void calculateLottoSecondRank() {
        Rank rank = winningNumbers.calculateRank(LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 7)));
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @DisplayName("맞춘 개수에 맞는 Rank객체가 리턴된다. - 없는 경우")
    @Test
    public void calculateLottoNoneRank() {
        Rank rank = winningNumbers.calculateRank(LottoTicket.valueOf(Arrays.asList(8, 9, 10, 11, 12, 13)));
        assertThat(rank).isEqualTo(Rank.NOTHING);
    }
}
