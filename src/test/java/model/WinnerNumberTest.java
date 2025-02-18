package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class WinnerNumberTest {
    @Test
    void 당첨_번호_생성_테스트(){
        List<Integer> expect = Arrays.asList(1,2,3,4,5,6);
        int bonusBall = 40;

        WinnerNumber winnerNumber = new WinnerNumber(expect, bonusBall);
        List<Integer> result = winnerNumber.getWinnerNumbers();

        Assertions.assertThat(result).isEqualTo(expect);
    }

    @Test
    void 보너스_볼_생성_테스트(){
        List<Integer> number = Arrays.asList(1,2,3,4,5,6);
        int expect = 40;

        WinnerNumber winnerNumber = new WinnerNumber(number, expect);
        int result = winnerNumber.getBonusBall();

        Assertions.assertThat(result).isEqualTo(expect);
    }

    @Test
    void 당첨_번호_중복_예외_테스트(){
        List<Integer> winnerNumber = Arrays.asList(1,1,3,4,5,6);
        int bonusBall = 40;

        Assertions.assertThatThrownBy(() -> new WinnerNumber(winnerNumber, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 보너스_볼_번호_중복_예외_테스트(){
        List<Integer> winnerNumber = Arrays.asList(1,2,3,4,5,6);
        int bonusBall = 1;

        Assertions.assertThatThrownBy(() -> new WinnerNumber(winnerNumber, bonusBall))
                .isInstanceOf(IllegalArgumentException.class);
    }
}