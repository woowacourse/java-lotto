package lotto.domain;

import static lotto.domain.LottoTestDataGenerator.*;
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
        List<List<Integer>> manualNumbers = generateLottoTickets();

        // then
        assertDoesNotThrow(() -> lottoMachine.issueManual(manualNumbers));
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