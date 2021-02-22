package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class WinningStaticsTest {
    private List<Prize> prizes;

    @BeforeEach
    void setUp() {
        prizes = Arrays.asList(
                Prize.FIRST,
                Prize.FIRST,
                Prize.FIFTH,
                Prize.NOTHING
        );
    }

    @DisplayName("객체 생성 성공")
    @Test
    void create() {
        assertThatCode(() -> new WinningStatics(prizes))
                .doesNotThrowAnyException();
    }

    @DisplayName("총 수익률 계산 비교 성공")
    @Test
    void calculateProfitRate() {
        final int money = 100_000;
        final long totalProfit = prizes.stream()
                .mapToLong(Prize::getMoney)
                .sum();
        final WinningStatics winningStatics = new WinningStatics(prizes);

        final double profitRate = winningStatics.calculateProfitRate(new LottoMoney(String.valueOf(money)));
        assertThat(profitRate).isEqualTo((double) totalProfit / money);
    }
}
