import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import domain.LottoManager;
import domain.LottoWallet;
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
    private final List<Lotto> lottos = List.of(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new Lotto(List.of(1, 2, 3, 4, 5, 7)),
            new Lotto(List.of(1, 2, 3, 4, 5, 8)),
            new Lotto(List.of(1, 2, 3, 4, 10, 11)),
            new Lotto(List.of(1, 2, 3, 10, 11, 12)),
            new Lotto(List.of(10, 11, 12, 13, 14, 15)));
    private final LottoWallet lottoWallet = new LottoWallet(lottos);
    private final WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new LottoNumber(7));


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
        LottoWallet lottos = lottoManager.generateLottos(lottoCount);

        // then
        assertThat(lottos.getLottoWallet()).hasSize(lottoCount);
    }

    @Test
    @DisplayName("구매 금액과 로또 결과가 주어졌을 때 수익률을 정확히 계산한다")
    void check_calculate_revenue_correctly() {
        // given
        WinningResult winningResult = winningLotto.calculateWinningResult(lottoWallet);

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