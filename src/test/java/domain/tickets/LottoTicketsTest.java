package domain.tickets;

import domain.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoTicketsTest {
    @DisplayName("객체 생성 성공")
    @Test
    void create_validInput_success() {
        final LottoMoney lottoMoney = new LottoMoney("14000");
        final int expectedQuantity = 14;

        assertAll(
                () -> assertThatCode(() -> new LottoTickets(lottoMoney))
                        .doesNotThrowAnyException(),

                () -> assertThat(new LottoTickets(lottoMoney)
                        .isSameQuantity(expectedQuantity))
                        .isEqualTo(true)
        );
    }
}
