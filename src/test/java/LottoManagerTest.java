import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoManager;
import domain.Money;
import domain.LottoNumber;
import domain.WinningInfo;
import domain.WinningLotto;
import domain.WinningResult;
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
    WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(7));

    @BeforeEach
    void setup() {
        lottoManager = new LottoManager();
    }

    @Test
    @DisplayName("요구하는 로또 개수만큼 로또를 생성하여 반환한다")
    void should_return_lotto_list_by_lotto_count() {
        // given
        int lottoCount = 2;

        // when
        List<Lotto> lottos = lottoManager.generateLottos(lottoCount);

        // then
        assertThat(lottos).hasSize(lottoCount);
    }

    @Test
    @DisplayName("로또와 당첨 번호가 주어졌을 때 로또 순위별 당첨 횟수를 정확히 계산한다")
    void check_each_winning_count_correctly() {
        // given
        LottoManager lottoManager = new LottoManager();

        // when
        WinningResult winningResult = lottoManager.calculateWinningResult(lottos, winningLotto);

        // then
        for (WinningInfo value : WinningInfo.values()) {
            assertThat(winningResult.getCount(value)).isEqualTo(1);
        }
    }

    @Test
    @DisplayName("구매 금액과 로또 결과가 주어졌을 때 수익률을 정확히 계산한다")
    void check_calculate_revenue_correctly() {
        // given
        LottoManager lottoManager = new LottoManager();
        WinningResult winningResult = lottoManager.calculateWinningResult(lottos, winningLotto);

        Money money = new Money(6000);
        long totalPrices = Arrays.stream(WinningInfo.values())
                .mapToInt(WinningInfo::getPrice)
                .sum();
        float expected = (float) totalPrices / 6000;

        // when
        float result = lottoManager.calculateRevenue(winningResult, money);

        // then
        assertThat(result).isEqualTo(expected);
    }

}