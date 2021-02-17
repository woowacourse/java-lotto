package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidLottoNumberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @DisplayName("WinningLotto 생성 테스트")
    @Test
    void create() {
        WinningLotto winningLotto
            = new WinningLotto(Arrays.asList(1, 3, 5, 7, 9, 11), 45);
        assertThat(winningLotto)
            .isEqualTo(new WinningLotto(Arrays.asList(1, 3, 5, 7, 9, 11), 45));
    }

    @DisplayName("보너스 번호 에러테스트")
    @Test
    void boundException() {
        assertThatThrownBy(() -> {
            new WinningLotto(Arrays.asList(1, 3, 5, 7, 9, 11), 46);
        }).isInstanceOf(InvalidLottoNumberException.class);
    }

    @DisplayName("번호 중복 에러 테스트")
    @Test
    void duplicateException() {
        assertThatThrownBy(() -> {
            new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6), 1);
        }).isInstanceOf(DuplicateLottoNumberException.class);
    }
}
