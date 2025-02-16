package controller;

import controller.dto.LottoRankResponse;
import controller.dto.LottoTicketResponse;
import controller.dto.WinningLottoRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.numbers.LottoNumber;
import model.numbers.LottoNumbers;
import model.numbers.WinningLotto;
import model.rank.LottoRank;
import model.rank.LottoRankResult;

public class LottoDtoMapper {

    public List<LottoTicketResponse> toLottoTicketResponses(List<LottoNumbers> lottoNumbers) {
        return lottoNumbers.stream()
                .map(ticket -> LottoTicketResponse.from(ticket.getNumbers()))
                .toList();
    }

    public WinningLotto toWinningLotto(WinningLottoRequest winningLottoRequest) {
        List<LottoNumber> lottoNumbers = winningLottoRequest.numbers()
                .stream()
                .map(LottoNumber::new)
                .toList();

        return new WinningLotto(new LottoNumbers(lottoNumbers),
                new LottoNumber(winningLottoRequest.bonusNumber()));
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
