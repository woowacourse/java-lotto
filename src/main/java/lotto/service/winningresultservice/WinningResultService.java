package lotto.service.winningresultservice;

import lotto.dao.lotto.LottoTicketDAO;
import lotto.dao.winninglotto.WinningResultDAO;
import lotto.dto.LottoTicketDTO;
import lotto.dto.WinningLottoDTO;
import lotto.dto.WinningResultDTO;
import lotto.model.lotto.LottoNumberRepository;
import lotto.model.lotto.LottoTicket;
import lotto.model.lotto.LottoTickets;
import lotto.model.winninglotto.WinningLotto;
import lotto.model.winninglotto.WinningResult;
import lotto.service.lottoticketservice.LottoTicketAssembler;

import java.sql.SQLException;
import java.util.List;

public class WinningResultService {
    public static void addWinningResult(WinningLottoDTO winningLottoDTO, int lottoRoundId) throws SQLException {
        List<LottoTicketDTO> lottoTicketDTOs = LottoTicketDAO.getInstance().selectLottoTicketsByLottoRoundId(lottoRoundId);

        LottoTickets lottoTickets = LottoTicketAssembler.toLottoTickets(lottoTicketDTOs);
        LottoTicket winningLottoTicket = LottoTicketAssembler.toLottoTicket(new LottoTicketDTO(winningLottoDTO.getWinningLotto()));

        WinningLotto winningLotto = WinningLotto.of(winningLottoTicket, LottoNumberRepository.fromNumber(winningLottoDTO.getBonusNumber()));
        WinningResult winningResult = WinningResult.of(lottoTickets, winningLotto);

        WinningResultDAO.getInstance().insertWinningResult(WinningResultAssembler.toDTO(winningResult, lottoRoundId));
    }

    public static WinningResultDTO getWinningResultByLottoRoundId(int lottoRoundId) throws SQLException {
        return WinningResultDAO.getInstance().selectWinningResultByLottoRoundId(lottoRoundId);
    }
}
