package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("당첨 번호 개수 만큼 당첨 번호를 생성한다.")
    @Test
    void 당첨_번호_생성_확인() {
        // given
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);
        int bonusBall = 7;

        // when & then
        assertThatCode(() -> new WinningNumbers(integers, bonusBall)).doesNotThrowAnyException();
    }
}