package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoStoreTest {

    @Test
    @DisplayName("티켓들 생성")
    void generateRandomTicket() {
        assertThat(LottoStore.createTickets(new Money("5000"), 2,
                Arrays.asList("1, 2, 3, 4, 5, 6", "1, 2, 3, 7, 8, 9")).size()).isEqualTo(5);
    }

    @Test
    @DisplayName("수동 티켓 사이즈 검증")
    void test1() {
        assertThatThrownBy(() -> LottoStore.createTickets(new Money("5000"), 10, Arrays.asList("1, 2, 3, 4, 5, 6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 5장 구매 가능합니다.");
    }

    @Test
    @DisplayName("수동 티켓 사이즈 검증")
    void test2() {
        assertThatThrownBy(() -> LottoStore.createTickets(new Money("5000"), 0, Arrays.asList("1, 2, 3, 4, 5, 6")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("1장 이상 구매 가능합니다.");
    }
}
