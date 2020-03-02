package domain.lottonumbers;

import domain.lottonumbers.lottonumber.LottoNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoTicketTest {

    @Test
    void contains() {
        LottoTicket myTicket = generateLottoTicket(1,2,3,4,5,6);

        assertThat(myTicket.contains(LottoNumber.of(1))).isTrue();
        assertThat(myTicket.contains(LottoNumber.of(6))).isTrue();
        assertThat(myTicket.contains(LottoNumber.of(7))).isFalse();
    }

    @Test
    void findDuplicatedNumbers() {
        LottoTicket myTicket = generateLottoTicket(1,2,3,4,5,6);
        LottoTicket comparingTicket = generateLottoTicket(4,5,6,7,8,9);

        assertThat(myTicket.findDuplicatedNumbers(comparingTicket)).isEqualTo(3);
    }

    LottoTicket generateLottoTicket(int number1, int number2, int number3, int number4, int number5, int number6) {
        List<Integer> integers = Arrays.asList(number1, number2, number3, number4, number5, number6);

        return integers.stream()
                .map(LottoNumber::of)
                .collect(collectingAndThen(toSet(), LottoTicket::new));
    }
}