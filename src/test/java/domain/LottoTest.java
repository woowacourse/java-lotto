package domain;

import org.junit.jupiter.api.Test;
import testUtil.StaticNumberPicker;
import util.NumberPicker;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    
    @Test
    void 구입_금액만큼_로또를_구입한다() {
        // given
        int money = 4000;
        NumberPicker numberPicker = new StaticNumberPicker(List.of(
                List.of(1, 2, 3, 4, 5, 6),
                List.of(14, 15, 16, 13, 12, 9),
                List.of(43, 41, 40, 23, 35, 22),
                List.of(9, 7, 13, 14, 16, 2)
        ));
        
        // when
        List<Lotto> result = Lotto.purchase(money, numberPicker);
        
        // then
        
        assertThat(result).hasSize(4);
        assertThat(result.stream().map(Lotto::getNumbers).toList())
                .containsAll(List.of(
                        List.of(1, 2, 3, 4, 5, 6),
                        List.of(14, 15, 16, 13, 12, 9),
                        List.of(43, 41, 40, 23, 35, 22),
                        List.of(9, 7, 13, 14, 16, 2)
                ));
    }
}
