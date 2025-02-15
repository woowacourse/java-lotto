package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.winning.WinningLotto;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    void 당첨__번호와_보너스_번호가_중복되면_예외를_던진다() {
        List<Integer> winningLottoNumbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 1;

        assertThatThrownBy(() ->
                new WinningLotto(winningLottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
