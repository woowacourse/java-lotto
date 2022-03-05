package lotterymachine.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LotteryTicketsTest {
    @Test
    @DisplayName("6개의 숫자 리스트를 입력 받아 로또 티켓을 추가한다.")
    void add() {
        List<LotteryNumber> input = IntStream.range(7, 13)
                .mapToObj(LotteryNumber::from)
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
                .mapToObj(LotteryNumber::from)
                .collect(Collectors.toList());

        assertThatThrownBy(() -> {
            List<LotteryTicket> tickets = List.of(new LotteryTicket(input));
            LotteryTickets lotteryTickets = new LotteryTickets(tickets);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("로또 숫자는 여섯개를 입력해야합니다.");
    }

    @Test
    @DisplayName("두개의 로또 리스트를 입력 받아, LotteryTickets를 생성한다.")
    void create() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<LotteryTicket> lotteryTicket = LotteryTicket.from(List.of(input));

        LotteryTickets value = LotteryTickets.of(lotteryTicket, lotteryTicket);

        assertThat(value.getLotteryTickets().size()).isEqualTo(2);
    }
}