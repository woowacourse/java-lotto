package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("구입 개수만큼의 로또 생성")
    @Test
    void Should_EqualToExpected_When_Purchased() {
        LottoGenerator lottoGenerator = new LottoGenerator();
        Assertions.assertThat(lottoGenerator.generatePurchasedTickets(14).size()).isEqualTo(14);
    }

    @DisplayName("당첨 번호와 보너스 번호 생성")
    @Test
    void Should_Return_WinningLottoNumbers_When_WinnerNumberDecided() {
        LottoGenerator lottoGenerator = new LottoGenerator();
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
