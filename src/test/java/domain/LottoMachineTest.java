package domain;

import domain.lotto.LottoTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import view.InputView;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
        List<LottoTicket> lottoTickets = DUMMY_LOTTO_MACHINE.makeTickets(ticketCount);

        //then
        assertThat(lottoTickets.size()).isEqualTo(ticketCount);
    }

    @DisplayName("수동으로 보장된 로또번호를 입력시 정상 동작한다(보장된 로또 번호 중복되지 않은 1~45의 숫자이며, 중복되지 않은 6개의 숫자)")
    @Test
    void manualLottoTicketTest() {
        //given
        String manualLottoNumber = "1,2,3,4,5,6";
        InputView inputView = getInputView(manualLottoNumber);

        //when
        assertThatCode(() -> DUMMY_LOTTO_MACHINE.makeManualTicket(inputView, 1))
                .doesNotThrowAnyException();
    }

    @DisplayName("보장된 로또번호가 아닐시 입력시 에러가 발생한(보장된 로또 번호 중복되지 않은 1~45의 숫자이며, 중복되지 않은 6개의 숫자)")
    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,5", "-1,1,2,3,4,5", "1,2,3,4,5,46"})
    void manualLottoTicketInputErrorTest(String manualLottoNumber) {
        //given
        InputView inputView = getInputView(manualLottoNumber);

        //when
        assertThatThrownBy(() -> DUMMY_LOTTO_MACHINE.makeManualTicket(inputView, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}