package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("티켓 객체 테스트")
public class TicketTest {
    @Test
    @DisplayName("티켓 생성")
    void ticketConstructor() {
        Set<LottoNumber> lottoNumberList = new HashSet<>(Arrays.asList(LottoNumber.getLottoNumber(1),
                LottoNumber.getLottoNumber(2),
                LottoNumber.getLottoNumber(3),
                LottoNumber.getLottoNumber(4),
                LottoNumber.getLottoNumber(5)));
        assertThatThrownBy(() -> new Ticket(lottoNumberList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 숫자를 입력해주세요.");
    }

//    @Test
//    @DisplayName("두 티켓의 같은 숫자 비교")
//    void compareTwoTickets() {
//        Set<LottoNumber> lottoNumberSet1 = createLottoNumber(1, 2, 3, 4, 5, 6);
//        Set<LottoNumber> lottoNumberSet2 = createLottoNumber(4, 5, 6, 7, 8, 9);
//        Ticket ticket1 = new Ticket(lottoNumberSet1);
//        Ticket ticket2 = new Ticket(lottoNumberSet2);
//        assertThat(ticket1.getWith(ticket2)).isEqualTo(3);
//    }
//
//    private Set<LottoNumber> createLottoNumber(int number1, int number2, int number3, int number4, int number5, int number6) {
//        Set<LottoNumber> lottoNumberList = new HashSet<>(Arrays.asList(LottoNumber.getLottoNumber(number1),
//                LottoNumber.getLottoNumber(number2),
//                LottoNumber.getLottoNumber(number3),
//                LottoNumber.getLottoNumber(number4),
//                LottoNumber.getLottoNumber(number5),
//                LottoNumber.getLottoNumber(number6)));
//        return lottoNumberList;
//    }
}
