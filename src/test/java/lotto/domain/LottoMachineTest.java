package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {

    @DisplayName("필요한 로또 개수 만큼 수동 구매가 가능하다.")
    @Test
    void 로또_머신_수동_발급() {
        // given
        LottoMachine lottoMachine = new LottoMachine();

        // when
        int manualCount = 3;
        List<List<Integer>> manualNumbers = List.of(
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44));

        // then
        assertDoesNotThrow(() -> lottoMachine.issueManual(manualCount, manualNumbers));
    }

    @DisplayName("필요한 로또 개수 만큼 자동 구매가 가능하다.")
    @Test
    void 로또_머신_자동_발급() {
        // given
        LottoMachine lottoMachine = new LottoMachine();

        // when
        int autoCount = 3;

        // then
        assertDoesNotThrow(() -> lottoMachine.issueAuto(autoCount));
    }
}