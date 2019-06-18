package lotto.application.lottoresult;

import lotto.application.lottoticket.LottoTicketDAO;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoresult.LottoStatistics;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoresult.dto.LottoStatisticsDTO;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import lotto.domain.lottoticket.dto.LottoTicketsDTO;
import lotto.domain.lottoticket.dto.WinningLottoDTO;

import java.util.ArrayList;
import java.util.List;

public class LottoResultService {
    private static LottoResultService lottoResultService = null;

    private LottoResultService() {
    }

    public static LottoResultService getInstance() {
        if (lottoResultService == null) {
            lottoResultService = new LottoResultService();
        }
        return lottoResultService;
    }

    public void saveWinningLotto(WinningLottoDTO winningLottoDto) {
        int latestRoundNum = fetchLatestRoundNum();

        WinningLottoDAO winningLottoDAO = WinningLottoDAO.getInstance();
        winningLottoDAO.saveWinningLotto(latestRoundNum, winningLottoDto);
    }

    public int fetchLatestRoundNum() {
        LottoResultDAO lottoResultDAO = LottoResultDAO.getInstance();
        return lottoResultDAO.getLatestRoundNum();
    }

    public WinningLottoDTO getWinningLottoDto(WinningLotto winningLotto) {
        return LottoResultAssembler.getWinningLottoDto(winningLotto);
    }

    public LottoStatisticsDTO getLottoResult(String round) {
        return getLottoResult(Integer.parseInt(round));
    }

    public LottoStatisticsDTO getLottoResult(int round) {
        LottoResultDAO lottoResultDAO = LottoResultDAO.getInstance();
        return lottoResultDAO.fetchLottoStatisticsDto(round);
    }

    public LottoStatistics calculateLottoStatistics(WinningLotto winningLotto) {
        int round = fetchLatestRoundNum();
        LottoTicketDAO lottoTicketDAO = LottoTicketDAO.getInstance();
        LottoTicketsDTO lottoTicketsDTO = lottoTicketDAO.fetchPurchasedLottoTicketsOn(round);
        LottoTickets lottoTickets = makeLottoTicketsFrom(lottoTicketsDTO);
        return LottoStatistics.of(lottoTickets, winningLotto);
    }

    private LottoTickets makeLottoTicketsFrom(LottoTicketsDTO lottoTicketsDTO) {
        List<LottoTicketDTO> lottoTicketDTOs = lottoTicketsDTO.getLottoTicketDTOs();

        List<LottoTicket> tickets = new ArrayList<>();
        for (LottoTicketDTO lottoTicketDTO : lottoTicketDTOs) {
            List<LottoNumber> lottoNumbers = new ArrayList<>();
            lottoNumbers.add(LottoNumberPool.valueOf(lottoTicketDTO.getFirstNum()));
            lottoNumbers.add(LottoNumberPool.valueOf(lottoTicketDTO.getSecondNum()));
            lottoNumbers.add(LottoNumberPool.valueOf(lottoTicketDTO.getThirdNum()));
            lottoNumbers.add(LottoNumberPool.valueOf(lottoTicketDTO.getFourthNum()));
            lottoNumbers.add(LottoNumberPool.valueOf(lottoTicketDTO.getFifthNum()));
            lottoNumbers.add(LottoNumberPool.valueOf(lottoTicketDTO.getSixthNum()));
            tickets.add(new LottoTicket(lottoNumbers));
        }
        return new LottoTickets(tickets);
    }

    public void saveLottoStatistics(LottoStatistics lottoStatistics) {
        LottoStatisticsDTO lottoStatisticsDTO = LottoResultAssembler.getLottoStatisticsDTO(lottoStatistics);
        LottoResultDAO lottoResultDAO = LottoResultDAO.getInstance();
        lottoResultDAO.saveLottoStatistics(fetchLatestRoundNum(), lottoStatisticsDTO);
    }
}
