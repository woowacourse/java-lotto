package domain.factory;

import domain.numberscontainer.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class LottoNumbersDtoFactoryTest {

    @Test
    @DisplayName("랜덤 티켓 생성")
    void generateRandomTicket() {
        RandomFactory randomFactory = new RandomFactory();
        assertThatCode(() -> new Ticket(randomFactory.generate(false)))
                .doesNotThrowAnyException();
    }
}