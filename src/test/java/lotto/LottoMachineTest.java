package lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    private LottoMachine lottoMachine;

    @BeforeEach
    void init() {
        lottoMachine = new LottoMachine();
    }

    @DisplayName("로또는 6개의 번호를 가진다")
    @Test
    void 로또는_6개의_번호를_가진다() {
        Set<Integer> result = lottoMachine.createLotto();
        assertThat(result.size()).isEqualTo(6);
    }

    /**
     * 테스트의 멱등성을 고려하여 리팩토링 TODO
     */
    @Test
    void 로또는_1과_45_사이의_번호를_가진다() {
        for (int number : lottoMachine.createLotto()) {
            assertThat(number).isBetween(1, 45);
        }
    }
}