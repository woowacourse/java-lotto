package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoShopTest {
    private LottoShop lottoShop;
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
        lottoShop = new LottoShop();
    }

    @Test
    @DisplayName("로또 구매 금액으로 구매가능한 최대 개수만큼 로또를 생성하여 반환한다")
    void should_return_lottoWallet_by_lotto_count() {
        // given
        int moneyValue = 2000;
        Money money = new Money(moneyValue);

        // when
        LottoWallet lottoWallet = lottoShop.buyLottos(money);

        // then
        int excepted = 2;
        assertThat(lottoWallet.getLottoWallet()).hasSize(excepted);
    }
}