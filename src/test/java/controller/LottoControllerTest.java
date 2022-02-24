package controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.GenerateStrategy;
import model.LottoTickets;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoControllerTest {
    private static final List<Integer> dummyLottoNumber = Arrays.asList(1, 2, 3, 4, 5, 6);

    private final LottoController controller = new LottoController();

    @Test
    @DisplayName("로또 티켓이 정상적으로 작동하는지 확인한다.")
    void initLottoTicketTest() {
        GenerateStrategy generateStrategy = () -> new ArrayList<>(dummyLottoNumber);
        LottoTickets lottoTickets = new LottoTickets(1000, generateStrategy);
        List<Integer> winningTicket = new ArrayList<>(dummyLottoNumber);
        controller.initLottoGame(lottoTickets, winningTicket, 7);
        assertThat(Math.round(controller.rateOfReturn())).isEqualTo(2000000);
    }

    @Test
    @DisplayName("로또 티켓이 잘 생성되는지 확인한다.")
    void createLottoTicketsTest() {
        GenerateStrategy generateStrategy = () -> new ArrayList<>(dummyLottoNumber);
        LottoTickets lottoTickets = controller.createLottoTickets(17000, generateStrategy);

        lottoTickets.getTickets()
                .forEach(lottoTicket -> assertThat(lottoTicket.lottoNumberValues()).isEqualTo(dummyLottoNumber));
    }
}
