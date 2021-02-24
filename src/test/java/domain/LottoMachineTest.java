package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class LottoMachineTest {

    @DisplayName("유효한 값이면 객체 생성 성공")
    @Test
    void valueOf_validPrice_success() {
        assertThatCode(() -> LottoMachine.generateAutoLottoTickets(3))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 티켓 생성")
    @Test
    void generateLottoTickets_success() {
        assertThat(LottoMachine.generateAutoLottoTickets(14).size()).isEqualTo(14);
    }
}
