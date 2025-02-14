import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoManagerTest {
    private LottoManager lottoManager;

    List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 5, 8)),
            new Lotto(List.of(1, 2, 3, 4, 10, 11)),
            new Lotto(List.of(1, 2, 3, 10, 11, 12)),
            new Lotto(List.of(10, 11, 12, 13, 14, 15)));
    WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new Number(7));

    @BeforeEach
    void setup() {
        lottoManager = new LottoManager();
    }

    @DisplayName("로또와 당첨 번호를 토대로 로또 순위별 당첨 횟수를 알 수있다.")
    @Test
    void test1() {
        // given
        LottoManager lottoManager = new LottoManager();

        // when
        WinningResult winningResult = lottoManager.getWinningResult(lottos, winningLotto);

        // then
        for (LottoPrize value : LottoPrize.values()) {
            assertThat(winningResult.getCount(value)).isEqualTo(1);
        }
    }

    @DisplayName("수익률을 올바르게 계산할 수 있다.")
    @Test
    void test2() {
        // given
        LottoManager lottoManager = new LottoManager();
        WinningResult winningResult = lottoManager.getWinningResult(lottos, winningLotto);

        Money money = new Money(6000);
        long totalPrices = Arrays.stream(LottoPrize.values()).mapToInt(LottoPrize::getPrice).sum();
        float expected = (float) totalPrices / 6000;

        // when
        float result = lottoManager.getRevenue(winningResult, money);

        // then
        assertThat(result).isEqualTo(expected);
    }

}