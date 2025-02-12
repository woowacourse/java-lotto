package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 로또의_로또_번호는_6개이다() {
        assertThatThrownBy(() -> {
            Lotto lotto = new Lotto(List.of(
                    1, 2, 3, 4, 5, 6, 7
            ));
        });
    }
}
