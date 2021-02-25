package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    @DisplayName("LottoTickets 객체 생성")
    @Test
    void create() {
//        assertThatCode(LottoTickets::new).doesNotThrowAnyException();
    }

    @DisplayName("로또 티켓 자동 생성 성공")
    @Test
    void generateAuto_successful() {
        Transaction transaction = new Transaction(14000);
//        assertThat(generateAuto(wallet).size()).isEqualTo(14);
    }

    @DisplayName("티켓 수동 생성 성공")
    @Test
    void generateSemiAuto_successful() {

    }
}