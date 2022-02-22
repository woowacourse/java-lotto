package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private final Money money = new Money(15000);
    private final Player player = new Player(money);

    @Test
    @DisplayName("로또를 최대한으로 구매한다.")
    void getNumberOfPurchases(){
        List<Lotto> actual = player.purchaseLotto();
        int expected = 15;
        assertThat(actual.size()).isEqualTo(expected);
    }
}