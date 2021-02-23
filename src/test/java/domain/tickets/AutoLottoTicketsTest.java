package domain.tickets;

import domain.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertAll;

class AutoLottoTicketsTest {
    @DisplayName("객체 생성 성공")
    @Test
    void create_validInput_success() {
        final LottoMoney lottoMoney = new LottoMoney("14000");
        final int expectedQuantity = 14;

        assertAll(
                () -> assertThatCode(() -> new AutoLottoTickets(lottoMoney))
                        .doesNotThrowAnyException(),

                () -> assertThat(new AutoLottoTickets(lottoMoney)
                        .isSameQuantity(expectedQuantity))
                        .isEqualTo(true)
        );
    }
}
