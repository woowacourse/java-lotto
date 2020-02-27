package domain;

import domain.RandomTicketFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("티켓 생성 테스트")
class RandomTicketFactoryTest {

    @Test
    @DisplayName("랜덤 티켓 생성")
    void generateRandomTicket() {
        assertThatCode(RandomTicketFactory::createTicket)
                .doesNotThrowAnyException();
    }
}