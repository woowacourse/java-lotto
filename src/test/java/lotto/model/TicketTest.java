package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import lotto.exception.NotSixNumbersException;
import lotto.exception.OverlapException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketTest {

    @Test
    @DisplayName("6개가 입력됬는지 테스트")
    void validateLottoNumbersLength() {
        assertThatThrownBy(() -> {
            new Ticket("1, 3, 5, 7, 9, 11, 13");
        }).isInstanceOf(NotSixNumbersException.class)
            .hasMessage("숫자 6개를 입력해주세요.");
    }

    @Test
    @DisplayName("티켓안에 중복된 숫자를 가지고 있는지 테스트")
    void validateOverlap() {
        assertThatThrownBy(() -> {
            new Ticket("1, 1, 5, 7, 9, 11");
        }).isInstanceOf(OverlapException.class)
            .hasMessage("중복된 숫자를 가지고 있습니다.");
    }

    @Test
    @DisplayName("숫자를 가지고 있는지 판단")
    void isHasNumber() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        Ticket ticket = new Ticket(lottoNumbers);
        assertThat(ticket.contains(new LottoNumber(1))).isTrue();
    }
}
