package lotterymachine.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LotteryTicketsTest {
    @Test
    @DisplayName("6개의 숫자 리스트를 입력 받아 로또 티켓을 추가한다.")
    void add() {
        List<LotteryNumber> input = IntStream.range(7, 13)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toList());
        LotteryTicket lotteryTicket = new LotteryTicket(input);
        List<LotteryTicket> lotteryTicketList = List.of(lotteryTicket);
        LotteryTickets lotteryTickets = new LotteryTickets(lotteryTicketList);


        assertThat(lotteryTickets.getLotteryTickets().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("6개의 숫자가 아닌 리스트를 입력 받으면 예외를 throw 한다.")
    void validateAdd() {
        List<LotteryNumber> input = IntStream.range(6, 13)
                .mapToObj(LotteryNumber::new)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> {
            List<LotteryTicket> tickets = List.of(new LotteryTicket(input));
            LotteryTickets lotteryTickets = new LotteryTickets(tickets);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 숫자는 여섯개를 입력해야합니다.");
    }
}
