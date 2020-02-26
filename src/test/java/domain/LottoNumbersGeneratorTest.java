package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

public class LottoNumbersGeneratorTest {
    @DisplayName("로또티켓 입력받은만큼 생성되는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "10,10", "15,15"}, delimiter = ',')
    void generateLottoTicketsTest(String input, String expected) {
        List<LottoTicket> lottoTickets = LottoNumbersGenerator.generateLottoTickets(Integer.parseInt(input));
        Assertions.assertThat(lottoTickets.size()).isEqualTo(Integer.parseInt(expected));
    }
}
