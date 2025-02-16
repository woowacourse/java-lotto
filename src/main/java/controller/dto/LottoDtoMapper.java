package controller.dto;

import java.util.List;
import model.LottoRankResult;
import model.LottoTicket;
import model.WinningLotto;

public class LottoDtoMapper {

    public LottoTicketResponse toLottoTicketResponse(LottoTicket lottoTicket) {
        return LottoTicketResponse.from(lottoTicket.getNumbers());
    }

    public WinningLotto toWinningLotto(WinningLottoRequest request) {
        return new WinningLotto(request.numbers(), request.bonusNumber());
    }

    public LottoRankResultResponse toLottoRankResultResponse(LottoRankResult lottoRankResult) {
        return new LottoRankResultResponse(lottoRankResult.getRankCount());
    }

    public List<LottoTicket> toLottoTickets(List<LottoTicketResponse> responses) {
        return responses.stream()
                .map(response -> new LottoTicket(response.numbers()))
                .toList();
    }

    public LottoRankResult toLottoRankResult(LottoRankResultResponse response) {
        return new LottoRankResult(response.rankCount());
    }
}
