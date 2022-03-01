package model.winningresult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import model.money.Money;
import model.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningResultTest {

    @Test
    @DisplayName("수익률 계산이 당첨결과와 일치하게 되는지 확인한다.")
    void getRateOfReturn() {
        Map<Rank, Integer> result = new LinkedHashMap<>();
        result.put(Rank.FIRST, 0);
        result.put(Rank.SECOND, 0);
        result.put(Rank.THIRD, 0);
        result.put(Rank.FOURTH, 1);
        result.put(Rank.FIFTH, 3);

        WinningResult winningResult = new WinningResult(result);
        Money money = new Money(100000);
        assertThat(winningResult.getRateOfReturn(money)).isEqualTo(65000 / (double) 100000);
    }
}