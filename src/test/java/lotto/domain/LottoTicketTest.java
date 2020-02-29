package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {
    @Test
    @DisplayName("수동으로 티켓 생성할 경우 테스트")
    void generate_lotto_manual_ticket() {
        String input = "1,2,3,4,5,6";
        Assertions.assertThatCode(()->new LottoTicket(input)).doesNotThrowAnyException();
    }
    @Test
    @DisplayName("수동으로 티켓 생성할 경우 중복될경우 오류 테스트")
    void throw_generate_lotto_manual_ticket_duplicate() {
        String input = "1,2,3,4,6,6";
        Assertions.assertThatThrownBy(()->new LottoTicket(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수동으로 티켓 생성할 경우 로또볼 범위 오류 테스트")
    void throw_generate_lotto_manual_ticket_out_of_range() {
        String input = "1,2,3,4,6,46";
        Assertions.assertThatThrownBy(()->new LottoTicket(input)).isInstanceOf(IllegalArgumentException.class);
    }
}