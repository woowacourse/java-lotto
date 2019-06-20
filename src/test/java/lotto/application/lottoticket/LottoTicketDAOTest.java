package lotto.application.lottoticket;

import lotto.application.lottoresult.LottoResultDAO;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketDAOTest {
    private LottoTicketDAO lottoTicketDAO;

    @BeforeEach
    void setUp() {
        lottoTicketDAO = LottoTicketDAO.getInstance();
    }

    @Test
    void lottoTicket_save하기() {
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
        LottoTicketService lottoTicketService = LottoTicketService.getInstance();
        LottoTicket lottoTicket = lottoTicketService.makeLottoTicket(numbers);
        LottoTicketDTO lottoTicketDto = lottoTicketService.getLottoTicketDto(lottoTicket);


        LottoResultDAO lottoResultDAO = LottoResultDAO.getInstance();
        int latestRoundNum = lottoResultDAO.getLatestRoundNum();

        int countOfRoundByGroup = lottoTicketDAO.calculateCountOfRoundByGroup(latestRoundNum);
        lottoTicketDAO.savePurchasedLotto(latestRoundNum, lottoTicketDto);
        int currentCountOfRoundByGroup = lottoTicketDAO.calculateCountOfRoundByGroup(latestRoundNum);

        assertThat(countOfRoundByGroup + 1 == currentCountOfRoundByGroup).isTrue();

        lottoTicketDAO.deletePurchasedLotto(latestRoundNum, currentCountOfRoundByGroup);
    }
}