package controller.dto;

import java.util.ArrayList;
import java.util.List;
import model.LottoRankResult;
import model.LottoTicket;
import model.WinningLotto;

public class LottoDtoMapper {

    public List<LottoTicketResponse> toLottoTicketResponses(List<LottoTicket> lottoTickets) {
        return lottoTickets.stream()
                .map(ticket -> LottoTicketResponse.from(ticket.numbers()))
                .toList();
    }

    public WinningLotto toWinningLotto(WinningLottoRequest winningLottoRequest) {
        return new WinningLotto(winningLottoRequest.numbers(), winningLottoRequest.bonusNumber());
    }

    public List<LottoRankResponse> toLottoRankResponses(LottoRankResult lottoRankResult) {
        return new ArrayList<>(lottoRankResult.getRanks().stream()
                .map(rank -> {
                    int rankMatchCount = lottoRankResult.getCountByRank(rank);
                    return LottoRankResponse.of(rank, rankMatchCount);
                })
                .toList());
    }

}
