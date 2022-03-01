package model.lottonumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import model.generator.Generator;
import model.money.Money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    @DisplayName("투입 금액만큼의 로또를 생성하는지 확인한다.")
    void generateLottos_Test() {
        final Money money = new Money(100000);
        final Generator generator = () -> Arrays.asList(1, 2, 3, 4, 5, 6);

        final Lottos lottos = new Lottos(money, generator);
        assertThat(lottos.getLottos().size()).isEqualTo(100);
    }
}