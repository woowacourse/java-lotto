package lotto.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

    @Test
    @DisplayName("돈을 입력받은 만큼 로또 생성")
    void create_lottos_money() {
        Lottos lottos = Lottos.from(new Money(3650));

        assertEquals(lottos.getLottos().size(), 3);
    }

    @Test
    @DisplayName("남은 돈을 가지고 자동으로 로또 생성")
    void get_auto_size() {
        Lotto lotto1 = new Lotto(List.of(8, 21, 23, 41, 42, 43));
        Lotto lotto2 = new Lotto(List.of(3, 5, 11, 16, 32, 38));
        Lotto lotto3 = new Lotto(List.of(7, 11, 16, 35, 36, 44));

        Lottos lottos = Lottos.of(List.of(lotto1, lotto2, lotto3), new Money(11000));

        assertEquals(lottos.getAutoLottos(3).size(), 11);
    }

    @Test
    @DisplayName("수동으로 고른 로또와 남은 돈을 가지고 로또 생성")
    void get_total_size() {
        Lotto lotto1 = new Lotto(List.of(8, 21, 23, 41, 42, 43));
        Lotto lotto2 = new Lotto(List.of(3, 5, 11, 16, 32, 38));
        Lotto lotto3 = new Lotto(List.of(7, 11, 16, 35, 36, 44));

        Lottos lottos = Lottos.of(List.of(lotto1, lotto2, lotto3), new Money(11000));

        assertEquals(lottos.getLottos().size(), 14);
    }
}
