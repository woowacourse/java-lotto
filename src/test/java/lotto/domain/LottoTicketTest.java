package lotto.domain;

import lotto.exception.IllegalLottoNumbersException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTicketTest {
    @Test
    @DisplayName("매개변수로 넘어온 리스트의 길이가 6을 넘을 경우")
    void checkMemberNames() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.createLottoNumber("1"),
                LottoNumber.createLottoNumber("4"),
                LottoNumber.createLottoNumber("5"),
                LottoNumber.createLottoNumber("8"),
                LottoNumber.createLottoNumber("10"),
                LottoNumber.createLottoNumber("9"),
                LottoNumber.createLottoNumber("40"));
        assertThatThrownBy(() -> {
            LottoTicket lottoTicket = new LottoTicket(numbers);
        }).isInstanceOf(IllegalLottoNumbersException.class)
        .hasMessage("로또 티켓의 길이가 맞지 않습니다.");
    }

    @Test
    @DisplayName("매개변수로 넘어온 리스트의 길이가 6이 되지 않는 경우")
    void wrongArgumentCreate() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.createLottoNumber("1"),
                LottoNumber.createLottoNumber("4"),
                LottoNumber.createLottoNumber("5"),
                LottoNumber.createLottoNumber("8"),
                LottoNumber.createLottoNumber("10"));
        assertThatThrownBy(() -> {
            LottoTicket lottoTicket = new LottoTicket(numbers);
        }).isInstanceOf(IllegalLottoNumbersException.class)
        .hasMessage("로또 티켓의 길이가 맞지 않습니다.");
    }

    @Test
    @DisplayName("매개변수로 넘어온 리스트 안에 중복되는 수가 있는 검사")
    void duplicateNumbers() {
        List<LottoNumber> numbers = Arrays.asList(
                LottoNumber.createLottoNumber("1"),
                LottoNumber.createLottoNumber("4"),
                LottoNumber.createLottoNumber("8"),
                LottoNumber.createLottoNumber("8"),
                LottoNumber.createLottoNumber("8"),
                LottoNumber.createLottoNumber("10"));
        assertThatThrownBy(() -> {
            LottoTicket lottoTicket = new LottoTicket(numbers);
        }).isInstanceOf(IllegalLottoNumbersException.class)
        .hasMessage("티켓 내 중복되는 숫자들이 있습니다.");
    }
}
