package controller;

import controller.dto.LottoRankResponse;
import controller.dto.LottoTicketResponse;
import controller.dto.WinningLottoRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.numbers.LottoNumbers;
import model.rank.LottoRank;
import model.rank.LottoRankResult;
import model.numbers.WinningLotto;

public class LottoDtoMapper {

    public List<LottoTicketResponse> toLottoTicketResponses(List<LottoNumbers> lottoNumbers) {
        return lottoNumbers.stream()
                .map(ticket -> LottoTicketResponse.from(ticket.getNumbers()))
                .toList();
    }

    public WinningLotto toWinningLotto(WinningLottoRequest winningLottoRequest) {
        return new WinningLotto(new LottoNumbers(winningLottoRequest.numbers()), winningLottoRequest.bonusNumber());
    }

    public List<LottoRankResponse> toLottoRankResponses(LottoRankResult lottoRankResult) {
        return new ArrayList<>(Arrays.stream(LottoRank.values())
                .map(rank -> {
                    int rankMatchCount = lottoRankResult.getCountByRank(rank);
                    return LottoRankResponse.of(rank, rankMatchCount);
                })
                .toList());
    }

}
