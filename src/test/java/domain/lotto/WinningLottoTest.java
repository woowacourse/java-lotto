package domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatCode;

public class WinningLottoTest {

    @DisplayName("WinningLotto 정상 생성된다.")
    @Test
    void winningLotto_생성_테스트() {
        assertThatCode(() -> new WinningLotto(LottoNumbers.generate(Arrays.asList(1, 2, 3, 4, 5, 6))))
                .doesNotThrowAnyException();
    }
}
