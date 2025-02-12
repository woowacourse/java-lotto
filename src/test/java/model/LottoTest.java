package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Set;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 로또의_로또_번호는_6개이다() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(Set.of(
                    new Number(1),
                    new Number(2),
                    new Number(3),
                    new Number(4),
                    new Number(5),
                    new Number(6)
            ));
        });
    }
}
