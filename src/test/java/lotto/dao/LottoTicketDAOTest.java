package lotto.dao;

import lotto.dto.LottoTicketDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoTicketDAOTest {
    private LottoTicketDAO lottoTicketDAO;

    @BeforeEach
    void setUp() {
        lottoTicketDAO = LottoTicketDAO.getInstance();
    }

    @Test
    void 로또티켓이_잘들어가는지_테스트() {
        LottoTicketDTO lottoTicketDTO = new LottoTicketDTO();
        lottoTicketDTO.setRound(2);
        lottoTicketDTO.setLottos(Arrays.asList("2,3,4,5,6,7","3,4,5,6,7,8","4,5,6,7,8,9"));

        assertEquals(lottoTicketDAO.addLotto(lottoTicketDTO), 3);
    }

    @Test
    public void 로또_해당회차_찾기_테스트() {
        LottoTicketDTO lottoTicketDTO = new LottoTicketDTO();
        lottoTicketDTO.setRound(2);
        assertEquals(lottoTicketDAO.findByLottoTicket(lottoTicketDTO), Arrays.asList("1,2,3,4,5,6","2,3,4,5,6,7","3,4,5,6,7,8","4,5,6,7,8,9"));
    }

    @AfterEach
    void tearDown() {
        lottoTicketDAO = null;
    }
}
