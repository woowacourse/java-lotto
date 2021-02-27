package domain;

import domain.lotto.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.*;

class LottoMachineTest {

    public static final LottoMachine DUMMY_LOTTO_MACHINE = new LottoMachine();

    private InputView getInputView(String inputText) {
        InputStream inputStream = new ByteArrayInputStream(inputText.getBytes());
        System.setIn(System.in);
        Scanner scanner = new Scanner(inputStream);
        InputView inputView = new InputView(scanner);
        return inputView;
    }

    @DisplayName("로또 머신에게 구매 갯수를 넘겨주면 랜덤으로 만들어진 티켓을 반환한다.")
    @Test
    void lottoTicketMakeTest() {
        //given
        int ticketCount = 3;

        //when
        List<LottoTicket> lottoTickets = DUMMY_LOTTO_MACHINE.makeRandomTickets(ticketCount);

        //then
        assertThat(lottoTickets.size()).isEqualTo(ticketCount);
    }

    @DisplayName("수동으로 보장된 로또번호를 입력시 정상 동작한다(보장된 로또 번호 중복되지 않은 1~45의 숫자이며, 중복되지 않은 6개의 숫자)")
    @Test
    void manualLottoTicketTest() {
        //given
        List<Integer> manualNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        //when
        assertThatCode(() -> DUMMY_LOTTO_MACHINE.makeManualTickets(manualNumbers, 1))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장된 로또번호가 아닐시 입력시 에러가 발생한(보장된 로또 번호 중복되지 않은 1~45의 숫자이며, 중복되지 않은 6개의 숫자)")
    @Test
    void manualLottoTicketInputErrorTest() {
        //given
        List<Integer> duplicateManualNumber = Arrays.asList(1, 2, 3, 4, 5, 5);
        List<Integer> negativeManualNumbers2 = Arrays.asList(-1, 1, 2, 3, 4, 5);
        List<Integer> overManualNumbers3 = Arrays.asList(1, 2, 3, 4, 5, 46);

        //then
        assertThatThrownBy(() -> DUMMY_LOTTO_MACHINE.makeManualTickets(duplicateManualNumber, 1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DUMMY_LOTTO_MACHINE.makeManualTickets(negativeManualNumbers2, 1))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> DUMMY_LOTTO_MACHINE.makeManualTickets(overManualNumbers3, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}