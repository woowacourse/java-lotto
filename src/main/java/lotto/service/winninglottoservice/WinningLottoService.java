package lotto.service.winninglottoservice;

import lotto.dao.winninglotto.WinningLottoDAO;
import lotto.dto.LottoTicketDTO;
import lotto.model.lotto.LottoNumberRepository;
import lotto.model.lotto.LottoTicket;
import lotto.model.winninglotto.WinningLotto;
import lotto.service.lottoticketservice.LottoTicketAssembler;

import java.sql.SQLException;

public class WinningLottoService {
    public static void addWinningLottoByLottoRoundId(String lottoNumbers, int bonusNumber, int lottoRoundId) throws SQLException {
        LottoTicketDTO lottoTicketDTO = new LottoTicketDTO(lottoNumbers);
        LottoTicket lottoTicket = LottoTicketAssembler.toLottoTicket(lottoTicketDTO);
        WinningLotto winningLotto = WinningLotto.of(lottoTicket, LottoNumberRepository.fromNumber(bonusNumber));
        WinningLottoDAO.getInstance().insertWinningLotto(WinningLottoAssembler.toDTO(winningLotto), lottoRoundId);
    }
}