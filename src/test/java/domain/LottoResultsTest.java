package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
public class LottoResultsTest {

    @DisplayName("로또 당첨갯수 확인 메서드 테스트")
    @ParameterizedTest
    @CsvSource(value = {"MATCH_THREE,1"})
    void 로또_당첨갯수_확인_메서드(String lottoTypeStr, int expected) {

        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
        winningLottoTicket.initializeBonusBall("7");

        List<LottoTicket> originalLottoTickets = new ArrayList<>();
        originalLottoTickets.add(new LottoTicket(Arrays.asList(1, 2, 3, 12, 13, 14)));
        LottoTickets lottoTickets = new LottoTickets(originalLottoTickets);

        LottoResults lottoResults = lottoTickets.match(winningLottoTicket);
        HashMap<String, Integer> map = lottoResults.getCountMap();

        Assertions.assertThat(map.get(lottoTypeStr)).isEqualTo(expected);
    }
}
