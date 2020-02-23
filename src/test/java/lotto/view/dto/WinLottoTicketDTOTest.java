package lotto.view.dto;

import lotto.exception.ConvertFailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinLottoTicketDTOTest {

    private static final int DONT_CARE_NUMBER = 45;

    @DisplayName("입력받은 우승번호가 6개가 아닌경우")
    @ParameterizedTest
    @CsvSource(value = {"1,2,3,4,5|5", "1,2,3,4,5,6,7|7"}, delimiter = '|')
    void getWinningNumbers(String winningNumbers, int size) {
        assertThatThrownBy(() -> new WinLottoTicketDTO(winningNumbers, DONT_CARE_NUMBER))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("볼 %d개, 입력한 볼의 갯수가 6개가 아닙니다.", size);
    }

    @DisplayName("입력받은 우승번호에 숫자가 아닌 문자가 존재하는 경우")
    @Test
    void getWinningNumbers1() {
        String winningNumbers = "1,2,3,4,5,a";

        assertThatThrownBy(() -> new WinLottoTicketDTO(winningNumbers, DONT_CARE_NUMBER))
                .isInstanceOf(ConvertFailException.class)
                .hasMessage("%s : 숫자가 아닌 문자가 존재합니다.", winningNumbers);
    }

    @DisplayName("입력받은 우승번호를 Set 으로 반환")
    @Test
    void getWinningNumbers2() {
        //given
        String inputWinningNumbers = "1,2,3,4,5,6";

        //when
        WinLottoTicketDTO winLottoTicketDTO = new WinLottoTicketDTO(inputWinningNumbers, DONT_CARE_NUMBER);
        Set<Integer> winningNumbers = winLottoTicketDTO.getWinningNumbers();

        //then
        assertThat(winningNumbers).contains(1, 2, 3, 4, 5, 6);
    }
}
