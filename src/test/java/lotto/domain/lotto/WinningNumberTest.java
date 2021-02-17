package lotto.domain.lotto;

import java.util.Arrays;
import java.util.List;
import lotto.Money;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumberTest {

    @Test
    void 당첨_번호_갯수_체크() {
//        String inputNumber = "1, 2, 3, 4, 5, 6";
//        int collectNumber = (int) Arrays.stream(inputNumber.split(",")).count();

    }

    @Test
    void 너무_많은_당첨_번호() {
        String inputNumber = "1, 2, 3, 4, 5, 6, 7, 8";
        assertThatIllegalArgumentException().isThrownBy(() -> {
        });
    }

    @Test
    void 당첨_번호_중복_금지() {

    }

    @Test
    void 당첨_번호_범위_체크() {

    }


}
