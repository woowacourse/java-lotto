package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @DisplayName("유효한 값이면 객체 생성 성공")
    @Test
    void valueOf_validPrice_success() {
        assertThatCode(() -> LottoMachine.valueOf(Price.valueOf("1000")))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 티켓 생성")
    @Test
    void generateLottoTickets_success() {
        LottoMachine lottoMachine = LottoMachine.valueOf(Price.valueOf("14000"));
        assertThat(lottoMachine.generateLottoTickets().size()).isEqualTo(14);
    }
    //
}
