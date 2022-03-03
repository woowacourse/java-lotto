package model.lottonumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import model.generator.Generator;
import model.totalmoney.TotalPurchaseMoney;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    @DisplayName("투입 금액만큼의 로또를 생성하는지 확인한다.")
    void generateLottos_Test() {
        final TotalPurchaseMoney totalPurchaseMoney = new TotalPurchaseMoney(100000);
        final Generator generator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);

        final Lottos lottos = new Lottos(totalPurchaseMoney, generator);
        assertThat(lottos.getLottos().size()).isEqualTo(100);
    }
}