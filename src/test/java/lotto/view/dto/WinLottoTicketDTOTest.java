package lotto.view.dto;

import lotto.exception.ConvertFailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinLottoTicketDTOTest {

    private static final int DONT_CARE_NUMBER = 45;

    @DisplayName("입력받은 우승번호에 숫자가 아닌 문자가 존재하는 경우")
    @Test
    void getWinningNumbers1() {
        String winningNumbers = "1,2,3,4,5,a";

        assertThatThrownBy(() -> new WinLottoTicketDTO(winningNumbers, DONT_CARE_NUMBER))
                .isInstanceOf(ConvertFailException.class)
                .hasMessage("%s : 숫자가 아닌 문자가 존재합니다.", winningNumbers);
    }

    @DisplayName("String으로 입력받은 우승번호를 List로 set / get 테스트")
    @Test
    void getWinningNumbers2() {
        //given
        String inputWinningNumbers = "1,2,3,4,5,6";
        List<Integer> expectedData = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        WinLottoTicketDTO winLottoTicketDTO = new WinLottoTicketDTO(inputWinningNumbers, DONT_CARE_NUMBER);
        List<Integer> winNumbers = winLottoTicketDTO.getWinNumbers();

        //then
        assertThat(winNumbers).isEqualTo(expectedData);
    }
}
