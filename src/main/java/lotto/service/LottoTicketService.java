package lotto.service;

import lotto.dao.LottoTicketDAO;

import lotto.domain.Lotto;
import lotto.domain.LottoTicket;

import lotto.dto.LottoTicketDTO;

import java.util.List;

import static java.util.stream.Collectors.*;

public class LottoTicketService {

    public static int insertLottos(int round, LottoTicket lottoTicket) {
        LottoTicketDTO lottoTicketDTO = new LottoTicketDTO();
        lottoTicketDTO.setRound(round);
        lottoTicketDTO.setLottos(lottoTicket.getLottos().stream().map(Lotto::toString).collect(toList()));
        return LottoTicketDAO.getInstance().addLotto(lottoTicketDTO);
    }

    public static List<String> findByLottosTicket(int round) {
        LottoTicketDTO lottoTicketDTO = new LottoTicketDTO();
        lottoTicketDTO.setRound(round);
        return LottoTicketDAO.getInstance().findByLottoTicket(lottoTicketDTO);
    }

}
