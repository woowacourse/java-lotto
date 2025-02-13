package controller.dto;

import java.util.ArrayList;
import java.util.List;
import model.LottoRankResult;
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

    public List<LottoRankResponse> toLottoRankResponses(LottoRankResult lottoRankResult) {
        return new ArrayList<>(lottoRankResult.getKeys().stream()
                .map(rank -> {
                    int rankMatchCount = lottoRankResult.getValue(rank);
                    return LottoRankResponse.of(rank, rankMatchCount);
                })
                .toList());
    }

}
