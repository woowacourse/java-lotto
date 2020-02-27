package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

public class LottoTicketsGeneratorTest {
    @DisplayName("로또티켓 입력받은만큼 생성되는지 테스트")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "10,10", "15,15"}, delimiter = ',')
    void generateAutoLottoTicketsTest(String input, String expected) {
        List<LottoTicket> lottoTickets = LottoTicketsGenerator.generateAutoLottoTickets(Integer.parseInt(input));
        Assertions.assertThat(lottoTickets.size()).isEqualTo(Integer.parseInt(expected));
    }

//    @DisplayName("수동로또티켓 String으로 입력받았을 때 잘 들어가는지 테스트")
//    @ParameterizedTest
//    @ValueSource(strings = {"1, 2, 3, 4, 5, 6", "23, 21, 33, 22, 29, 11"})
//    void generateManualLottoTicketTest(String input, String expected) {
//        LottoTicketsGenerator lottoTicketsGenerator = new LottoTicketsGenerator();
//        Assertions.assertThat(lottoTickets.size()).isEqualTo(Integer.parseInt(expected));
//    }
}
