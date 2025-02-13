package controller.dto;

import java.util.List;
import java.util.Map;
import model.LottoRank;
import model.LottoTicket;
import model.WinningLotto;

public class LottoDtoMapper {

    public List<LottoTicketResponse> toLottoTicketResponse(List<LottoTicket> lottoTickets) {
        return lottoTickets.stream()
                .map(ticket -> LottoTicketResponse.from(ticket.getNumbers()))
                .toList();
    }

    public WinningLotto toWinningLotto(WinningLottoRequest winningLottoRequest) {
        return new WinningLotto(winningLottoRequest.numbers(), winningLottoRequest.bonusNumber());
    }

    public List<LottoRankResponse> toLottoRankResponses(Map<LottoRank, Integer> rankCount) {
        return rankCount.keySet().stream()
                .map(rank -> {
                    int rankMatchCount = rankCount.get(rank);
                    return LottoRankResponse.of(rank, rankMatchCount);
                })
                .toList();
    }
}
