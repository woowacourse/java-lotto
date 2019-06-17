package lotto.application.lottoresult;

import lotto.application.lottoticket.LottoTicketService;
import lotto.domain.Rank;
import lotto.domain.lottoresult.LottoStatistics;
import lotto.domain.lottoresult.WinningLotto;
import lotto.domain.lottoresult.dto.LottoStatisticsDTO;
import lotto.domain.lottoticket.dto.LottoTicketDTO;
import lotto.domain.lottoticket.dto.WinningLottoDTO;

class LottoResultAssembler {
    static WinningLottoDTO getWinningLottoDto(WinningLotto winningLotto) {
        WinningLottoDTO winningLottoDto = new WinningLottoDTO();

        LottoTicketDTO lottoTicketDto = LottoTicketService.getLottoTicketDto(winningLotto.getWinningTicket());
        winningLottoDto.setLottoTicketDto(lottoTicketDto);
        winningLottoDto.setBonusBall(winningLotto.getBonusBall());

        return winningLottoDto;
    }

    static LottoStatisticsDTO getLottoStatisticsDTO(LottoStatistics lottoStatistics) {
        LottoStatisticsDTO lottoStatisticsDTO = new LottoStatisticsDTO();
        lottoStatisticsDTO.setCountsOfFirstRank(lottoStatistics.getCountsBy(Rank.FIRST));
        lottoStatisticsDTO.setCountsOfSecondRank(lottoStatistics.getCountsBy(Rank.SECOND));
        lottoStatisticsDTO.setCountsOfThirdRank(lottoStatistics.getCountsBy(Rank.THIRD));
        lottoStatisticsDTO.setCountsOfFourthRank(lottoStatistics.getCountsBy(Rank.FOURTH));
        lottoStatisticsDTO.setCountsOfFifthRank(lottoStatistics.getCountsBy(Rank.FIFTH));
        lottoStatisticsDTO.setProfitRatio(lottoStatistics.getProfitRatio());
        return lottoStatisticsDTO;
    }
}
