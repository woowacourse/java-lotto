package lotto.service;

import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.LottoTickets;
import lotto.domain.lottos.amount.LottoAmount;
import lotto.domain.money.Money;
import lotto.util.ManualNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.lottos.LottoTicket.COUNT_ERROR_MESSAGE;
import static lotto.domain.lottos.LottoTicket.LOTTO_NUMBER_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketsServiceTest {

    private List<String> manualLottoNumbers;
    private LottoAmount lottoAmount;

    @BeforeEach
    public void setUp() {
        Money money = new Money("3000");
        manualLottoNumbers = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7", "3,4,5,6,7,8");
        lottoAmount = new LottoAmount(money, "3");
    }

    @Test
    @DisplayName("구매할 개수를 알려주면 로또 티켓 여러장 만들어진다.")
    public void createLottoTicketsTest() {
        LottoTickets lottoTickets = LottoTicketsService.createLottoTickets(lottoAmount, manualLottoNumbers);

        assertThat(lottoTickets).isInstanceOf(LottoTickets.class);
    }

    @Test
    @DisplayName("수동 로또 티켓들을 생성한다.")
    public void createManualLottoTickets() {
        List<String> inputManualNumbers = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7", "3,4,5,6,7,8");

        List<LottoTicket> lottoTickets = LottoTicketsService.createManualLottoTickets(inputManualNumbers);

        assertThat(lottoTickets.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("수동 로또 번호 입력값에 문자가 있으면 NumberFormatException 발생한다.")
    public void invalidManualNumberTest() {
        List<String> inputManualNumbers = Arrays.asList("1,2,3,4,5,이건문자열이다", "2,3,4,5,6,7", "3,4,5,6,7,8");

        assertThatThrownBy(() -> {
            LottoTicketsService.createManualLottoTickets(inputManualNumbers);
        }).isInstanceOf(NumberFormatException.class)
                .hasMessage(String.format(ManualNumberGenerator.NUMBER_FORMAT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE));
    }

    @Test
    @DisplayName("수동 로또 번호 입력값에 7자리 이상을 입력하면 IllegalArgumentException 발생한다.")
    public void invalidManualNumberOverSizeTest() {
        List<String> inputManualNumbers = Arrays.asList("1,2,3,4,5,6,7", "2,3,4,5,6,7", "3,4,5,6,7,8");

        assertThatThrownBy(() -> {
            LottoTicketsService.createManualLottoTickets(inputManualNumbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(COUNT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE));
    }

    @Test
    @DisplayName("수동 로또 번호 입력값에 5자리 이하를 입력하면 IllegalArgumentException 발생한다.")
    public void invalidManualNumberLessSizeTest() {
        List<String> inputManualNumbers = Arrays.asList("1,2,3,4,5", "2,3,4,5,6,7", "3,4,5,6,7,8");

        assertThatThrownBy(() -> {
            LottoTicketsService.createManualLottoTickets(inputManualNumbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(COUNT_ERROR_MESSAGE, LOTTO_NUMBER_SIZE));
    }
}