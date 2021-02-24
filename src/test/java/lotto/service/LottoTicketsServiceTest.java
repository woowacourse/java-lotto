package lotto.service;

import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.LottoTickets;
import lotto.domain.money.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static lotto.domain.money.Money.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketsServiceTest {

    private List<String> manualLottoNumbers;

    @BeforeEach
    public void setUp() {
        manualLottoNumbers = Arrays.asList("1,2,3,4,5,6", "2,3,4,5,6,7", "3,4,5,6,7,8");
    }

    @Test
    @DisplayName("돈을 주면 로또 티켓 여러장 만들어진다.")
    public void createLottoTicketsTest() {
        LottoTickets lottoTickets = LottoTicketsService.createLottoTickets(new Money("5000"), manualLottoNumbers);

        assertThat(lottoTickets).isInstanceOf(LottoTickets.class);
    }

    @Test
    @DisplayName("돈을 1000원 이하로 주면 로또 티켓 만들어주지 않는다.")
    public void notEnoughMoneyTest() {
        assertThatThrownBy(() -> {
            LottoTicketsService.createLottoTickets(new Money("500"), manualLottoNumbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(SHORT_MONEY_MESSAGE, LOTTO_PRICE));
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
                .hasMessage(String.format(LottoTicketService.NUMBER_FORMAT_ERROR_MESSAGE, LottoTicket.LOTTO_NUMBER_SIZE));
    }

    @Test
    @DisplayName("수동 로또 번호 입력값에 7자리 이상을 입력하면 IllegalArgumentException 발생한다.")
    public void invalidManualNumberOverSizeTest() {
        List<String> inputManualNumbers = Arrays.asList("1,2,3,4,5,6,7", "2,3,4,5,6,7", "3,4,5,6,7,8");

        assertThatThrownBy(() -> {
            LottoTicketsService.createManualLottoTickets(inputManualNumbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(LottoTicketService.COUNT_ERROR_MESSAGE, LottoTicket.LOTTO_NUMBER_SIZE));
    }

    @Test
    @DisplayName("수동 로또 번호 입력값에 5자리 이하를 입력하면 IllegalArgumentException 발생한다.")
    public void invalidManualNumberLessSizeTest() {
        List<String> inputManualNumbers = Arrays.asList("1,2,3,4,5", "2,3,4,5,6,7", "3,4,5,6,7,8");

        assertThatThrownBy(() -> {
            LottoTicketsService.createManualLottoTickets(inputManualNumbers);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format(LottoTicketService.COUNT_ERROR_MESSAGE, LottoTicket.LOTTO_NUMBER_SIZE));
    }
}