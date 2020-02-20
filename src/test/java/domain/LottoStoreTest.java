package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoStoreTest {

    @Test
    @DisplayName("티켓들 생성")
    void generateRandomTicket() {
        assertThat(LottoStore.generateTickets(10).size()).isEqualTo(10);
    }
}
