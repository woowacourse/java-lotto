package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class WinningLottoTest {
    @Test
    void 일치하는_번호_개수가_4개인_경우_테스트() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(winningLotto.matchNumbersOfLotto(Arrays.asList(1, 2, 3, 4, 10, 11))).isEqualTo(4);
    }

    @Test
    void 일치하는_번호_개수가_0개인_경우_테스트() {
        WinningLotto winningLotto = new WinningLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(winningLotto.matchNumbersOfLotto(Arrays.asList(7, 8, 9, 10, 11, 12))).isEqualTo(0);
    }

    @Test
    void 잘못된_당첨번호가_들어온_경우_테스트() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            new WinningLotto(Arrays.asList(1, 2, 3, 4, 5));
        }).withMessage("당첨 번호의 개수는 6개 입니다.");
    }
}
