package lotto.domain.ticketgenerator;

import static org.assertj.core.api.Assertions.assertThat;

import lotto.domain.ticketpurchase.LottoTickets;
import lotto.domain.ticketpurchase.ManualTicketsSize;
import lotto.domain.ticketpurchase.PurchasePrice;
import lotto.domain.ticketpurchase.UserPurchase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoTicketsRandomGeneratorTest {
    @DisplayName("구매한 랜덤 로또티켓 개수만큼 발급하는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"5:3", "4:0", "1:1", "3:2"}, delimiter = ':')
    void Should_Return_ExpectedRandomTicketsSize_When_Generate(String allTicketsSizeInputStr,
        String manualTicketsSizeInputStr) {
        // given
        int allTicketsSizeInput = Integer.parseInt(allTicketsSizeInputStr);
        int manualTicketsSizeInput = Integer.parseInt(manualTicketsSizeInputStr);
        int expectedRandomTicketsSize = allTicketsSizeInput - manualTicketsSizeInput;

        PurchasePrice purchasePrice = new PurchasePrice(allTicketsSizeInput * 1_000);
        ManualTicketsSize manualTicketsSize
            = new ManualTicketsSize(manualTicketsSizeInput, purchasePrice);
        UserPurchase userPurchase = new UserPurchase(purchasePrice, manualTicketsSize);

        // when
        LottoTickets lottoTickets = new LottoTicketsRandomGenerator().generate(userPurchase);

        // then
        assertThat(lottoTickets.size()).isEqualTo(expectedRandomTicketsSize);
    }
}
