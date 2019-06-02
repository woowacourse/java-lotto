package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    void 당첨번호와_중복() {
        List<Number> lottos = new ArrayList<>();

        for (int i = 1; i < 7; i++) {
            lottos.add(new Number(i));
        }

        Lotto lotto = new Lotto(lottos);

        assertThrows(IllegalArgumentException.class, () -> {
            new WinningLotto(lotto, new Number(1));
        });
    }
}
