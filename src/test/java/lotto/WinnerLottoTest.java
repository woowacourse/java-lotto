package lotto;

import static org.assertj.core.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinnerLottoTest {

    @Test
    @DisplayName("로또 번호 일치하는 갯수를 반환한다.")
    void countEqualsLottoNumbers() {
        WinnerLotto winner = new WinnerLotto(new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6))), number(1));
        Lotto userLotto = new Lotto(List.of(number(1), number(2), number(3), number(4), number(5), number(6)));

        assertThat(winner.countMatchNumbers(userLotto)).isEqualTo(6);
    }

    private Number number(int number) {
        return new Number(number);
    }
}
