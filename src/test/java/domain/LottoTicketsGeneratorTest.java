package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class LottoTicketsGeneratorTest {
    @DisplayName("Should_로또티켓 입력받은만큼 생성_When_로또 티켓 갯수 입력")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "10,10", "15,15"})
    void generateAutoLottoTicketsTest(int input, int expected) {
        List<LottoTicket> lottoTickets = LottoTicketsGenerator.generateAutoLottoTickets(input);
        Assertions.assertThat(lottoTickets.size()).isEqualTo(expected);
    }
}
