package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoGeneratorTest {
    private final LottoTicket lottoTicket = new LottoTicket(
        Arrays.asList(
            new LottoNumber(1),
            new LottoNumber(2),
            new LottoNumber(3),
            new LottoNumber(4),
            new LottoNumber(5),
            new LottoNumber(6)
        )
    );

    @DisplayName("구입 금액이 1000원 단위일 시 정상")
    @Test
    void Should_Not_ThrowException_When_PurchasePriceExactlyDividedByThousand() {
        assertThatCode(() -> new LottoGenerator(1000))
            .doesNotThrowAnyException();
    }

    @DisplayName("구입 금액이 1000원 단위가 아닐 시 에러")
    @Test
    void Should_ThrowException_When_PurchasePriceNotDividedByThousand() {
        Assertions.assertThatThrownBy(() ->
            new LottoGenerator(1200)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 개수만큼의 로또 생성")
    @Test
    void Should_EqualToExpected_When_Purchased() {
        LottoGenerator lottoGenerator = new LottoGenerator(14000);
        Assertions.assertThat(lottoGenerator.generatePurchasedTickets().size()).isEqualTo(14);
    }

    @DisplayName("당첨 번호와 보너스 번호 생성")
    @Test
    void Should_Return_WinningLottoNumbers_When_WinnerNumberDecided() {
        LottoGenerator lottoGenerator = new LottoGenerator(14000);
        LottoNumber bonusNumber = new LottoNumber(7);

        WinningLottoNumbers winningLottoNumbers = lottoGenerator
            .generateWinningLottoNumbers(lottoTicket, bonusNumber);
        assertThat(winningLottoNumbers.getWinningTicket().getLottoTicketNumbers())
            .containsAll(lottoTicket.getLottoTicketNumbers());
        assertThat(winningLottoNumbers.getBonusNumber()).isEqualTo(bonusNumber);
        assertThat(winningLottoNumbers.getWinningTicket().getLottoTicketNumbers().size())
            .isEqualTo(lottoTicket.getLottoTicketNumbers().size());
    }
}
