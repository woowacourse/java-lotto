package domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoSimulatorTest {

    @Test
    void purchase_입력받은_금액만큼_구매() {
        int numLotto = 5;
        int remainder = 300;
        Money money = Money.from(Lotto.PRICE.times(numLotto).toInt() + remainder);

        LottoGroup lottoGroup = LottoSimulator.purchase(
                Arrays.asList(LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6))),
                money
        );

        assertThat(lottoGroup.totalSize()).isEqualTo(numLotto);
    }

    @Test
    void purchase_입력받은_금액만큼_구매_모두수동() {
        int numLotto = 2;
        int remainder = 300;
        Money money = Money.from(Lotto.PRICE.times(numLotto).toInt() + remainder);

        LottoGroup lottoGroup = LottoSimulator.purchase(
                Arrays.asList(
                        LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6)),
                        LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6))
                ),
                money
        );

        assertThat(lottoGroup.totalSize()).isEqualTo(numLotto);
    }

    @Test
    void purchase_입력받은_금액만큼_구매_모두자동() {
        int numLotto = 2;
        int remainder = 300;
        Money money = Money.from(Lotto.PRICE.times(numLotto).toInt() + remainder);

        LottoGroup lottoGroup = LottoSimulator.purchase(
                Arrays.asList(),
                money
        );

        assertThat(lottoGroup.totalSize()).isEqualTo(numLotto);
    }

    @Test
    void purchase_수동으로_구입한게_모두_포함되었는지() {
        int numLotto = 10;
        int remainder = 300;
        Money money = Money.from(Lotto.PRICE.times(numLotto).toInt() + remainder);
        List<Lotto> nonRandomLottos = Arrays.asList(
                LottoGenerator.from(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoGenerator.from(Arrays.asList(40, 41, 42, 43, 44, 45))
        );

        LottoGroup lottoGroup = LottoSimulator.purchase(nonRandomLottos, money);

        for (Lotto nonRandomLotto : nonRandomLottos) {
            assertThat(lottoGroup.contains(nonRandomLotto)).isTrue();
        }
    }
}