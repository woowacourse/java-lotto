package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketFactoryTest {

//    @DisplayName("유효한 값이면 객체 생성 성공")
//    @Test
//    void valueOf_validPrice_success() {
//        assertThatCode(() -> LottoMachine.valueOf(Price.valueOf("1000")))
//                .doesNotThrowAnyException();
//    }

    @DisplayName("로또 티켓 생성")
    @Test
    void generateAuto_successful() {
        Wallet wallet = new Wallet(14000);
        assertThat(LottoTicketFactory.generateAuto(wallet).size()).isEqualTo(14);
    }
}
