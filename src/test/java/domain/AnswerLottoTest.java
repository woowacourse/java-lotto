package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswerLottoTest {
    @DisplayName("")
    @Test
    void test() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new AnswerLotto(lotto, 6));
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> new AnswerLotto(lotto, 46));
    }
}