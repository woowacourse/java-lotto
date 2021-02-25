package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumberGeneratorTest {

    @DisplayName("티켓 팩토리에서 생성한 티켓 넘버의 개수가 6개인지 확인")
    @Test
    void checkSizeOfTicketNumbers() {
        Lotto lotto = RandomNumberGenerator.makeTicket();
        assertThat(lotto.toSet().size()).isEqualTo(6);
    }
}
