package lotto;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class LottoTest {
    @Test
    void 로또_생성_테스트() {
        assertDoesNotThrow(() -> new Lotto("1,2,3,4,5,6"));
    }
    @Test
    public void 숫자_세개_일치() {
        Lotto lotto = new Lotto("1,2,3,4,5,6");
        WinningLotto winningLotto = new WinningLotto("3,4,5,7,8,9");

        assertThat(winningLotto.prize(lotto)).isEqualTo(Prize.THIRD);

    }

    @Test
    public void 숫자_중복_로또() {

        assertThrows(IllegalNumberCombinationException.class, () -> {
            new Lotto("1,2,3,3,4,5");
        });
    }

    @Test
    public void 로또_숫자_6개_아닐_때() {
        assertThrows(IllegalNumberCombinationException.class, () -> {
            new Lotto("1,2,3,5,4,");
        });
    }

}
