package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.utils.FixedLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MachineTest {
    @Test
    @DisplayName("구입 금액에 맞는 티켓 장수 확인")
    void buyTickets() {
        assertThat(new Machine("4000", new FixedLottoGenerator()).getTickets())
            .size().isEqualTo(4);
    }

    @Test
    @DisplayName("구입 금액이 나누어 떨어지지 않는 경우 최대 티켓 장수 확인")
    void buyTickets2() {
        assertThat(new Machine("4100", new FixedLottoGenerator()).getTickets())
            .size().isEqualTo(4);
    }

    @Test
    @DisplayName("결과 값 테스트 - 1등 3개, 2등 0개")
    void getResult() {
        Machine machine = new Machine("3000", new FixedLottoGenerator());

        final Map<Rank, Integer> resultMap = machine.getResult("1,2,3,4,5,6", "7")
            .getResultMap();
        int firstPlaces = resultMap.getOrDefault(Rank.FIRST_PLACE, 0);
        int secondPlaces = resultMap.getOrDefault(Rank.SECOND_PLACE, 0);

        assertThat(firstPlaces).isEqualTo(3);
        assertThat(secondPlaces).isEqualTo(0);
    }

    @Test
    @DisplayName("수동구매 테스트 - 포함여부 확인")
    void generate_analog1() {
        List<String> lottoNumbers = Arrays
            .asList("11,12,13,14,15,16", "21,22,23,24,25,26", "31,32,33,34,35,36");
        final Machine machine = new Machine("14000", lottoNumbers, new FixedLottoGenerator());
        assertThat(machine.getTickets()).contains(
            new LottoTicket("11,12,13,14,15,16"),
            new LottoTicket("21,22,23,24,25,26"),
            new LottoTicket("31,32,33,34,35,36")
        );
    }

}