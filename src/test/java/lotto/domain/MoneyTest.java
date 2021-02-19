package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.utils.CustomException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    @DisplayName("돈으로 살 수 있는 티켓 갯수 반환 - 나누어 떨어지는 경우")
    void getPossibleTicketCount() {
        assertThat(new Money("3000").getPossibleTicketCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("돈으로 살 수 있는 티켓 갯수 반환 - 나누어 떨어지지 않는 경우")
    void getPossibleTicketCount1() {
        assertThat(new Money("3100").getPossibleTicketCount()).isEqualTo(3);
    }

    @Test
    @DisplayName("실패 - 돈이 정수가 아닌 경우")
    void generate() {
        assertThatThrownBy(() -> new Money("3100.1"))
            .isInstanceOf(CustomException.class);
    }

    @Test
    @DisplayName("실패 - 돈이 숫자가 아닌 경우")
    void generate1() {
        assertThatThrownBy(() -> new Money("12ls"))
            .isInstanceOf(CustomException.class);
    }

}