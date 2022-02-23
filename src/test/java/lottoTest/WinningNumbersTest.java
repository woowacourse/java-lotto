package lottoTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import lotto.WinningNumbers;
import org.junit.jupiter.api.Test;


@SuppressWarnings("NonAsciiCharacters")
class WinningNumbersTest {

    @Test
    void 당첨번호가_서로_중복인_경우() {
        assertThatThrownBy(() -> new WinningNumbers(Arrays.asList(1, 1, 3, 4, 5, 6), 7))
                .hasMessageContaining("당첨 번호가 서로 중복되었습니다.")
                .isInstanceOf(RuntimeException.class);
    }
}
