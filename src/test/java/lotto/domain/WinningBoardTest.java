package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningBoardTest {

    @DisplayName("맞춘 로또 번호 갯수와 보너스볼 맞춤 여부를 확인해 해당하는 보상을 반환한다.")
    @Test
    void checkBoard(){
        WinningBoard winningBoard;
        winningBoard = WinningBoard.findWinnings(5,true);
        assertThat(winningBoard).isEqualTo(WinningBoard.SECOND);
        winningBoard = WinningBoard.findWinnings(5,false);
        assertThat(winningBoard).isEqualTo(WinningBoard.THIRD);
        winningBoard = WinningBoard.findWinnings(2,true);
        assertThat(winningBoard).isEqualTo(WinningBoard.ZERO);
        winningBoard = WinningBoard.findWinnings(4,false);
        assertThat(winningBoard).isEqualTo(WinningBoard.FOURTH);
    }

}