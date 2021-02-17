package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {

    @DisplayName("우승 로또를 생성하는 기능")
    @Test
    void generate() {
        // given
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        int bonusBall = 7;

        // when
        WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);

        // then
        assertThat(winningLotto).isNotNull();
    }
}