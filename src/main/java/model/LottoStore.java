package model;

import common.NumbersGenerator;
import controller.dto.LottoDtoMapper;
import controller.dto.LottoRankResponse;
import controller.dto.LottoRankResultResponse;
import controller.dto.LottoTicketResponse;
import controller.dto.WinningLottoRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoStore {

    private static final int LOTTO_PRICE = 1_000;

    private final NumbersGenerator numbersGenerator;
    private final LottoRankFinder lottoRankFinder;
    private final LottoRankCounter lottoRankCounter;
    private final LottoDtoMapper lottoDtoMapper;

    public LottoStore(
            NumbersGenerator numbersGenerator,
            LottoRankFinder lottoRankFinder,
            LottoRankCounter lottoRankCounter,
            LottoDtoMapper lottoDtoMapper
    ) {
        this.numbersGenerator = numbersGenerator;
        this.lottoRankFinder = lottoRankFinder;
        this.lottoRankCounter = lottoRankCounter;
        this.lottoDtoMapper = lottoDtoMapper;
    }

    public int calculatePurchaseCount(int purchaseAmount) {
        validateAmountUnit(purchaseAmount);
        return purchaseAmount / LOTTO_PRICE;
    }

    public List<LottoTicketResponse> createLottoTickets(int purchaseCount) {
        return IntStream.range(0, purchaseCount)
                .mapToObj(count -> new LottoTicket(numbersGenerator.generate()))
                .map(lottoDtoMapper::toLottoTicketResponse)
                .toList();
    }

    public LottoRankResultResponse countLottoRankResult(
            List<LottoTicketResponse> lottoTicketResponses,
            WinningLottoRequest winningLottoRequest
    ) {
        List<LottoTicket> lottoTickets = lottoDtoMapper.toLottoTickets(lottoTicketResponses);
        WinningLotto winningLotto = lottoDtoMapper.toWinningLotto(winningLottoRequest);

        List<LottoRank> lottoRanks = lottoRankFinder.findAll(lottoTickets, winningLotto);
        Map<LottoRank, Integer> lottoRankCountResult = lottoRankCounter.countLottoRanks(lottoRanks);
        LottoRankResult lottoRankResult = new LottoRankResult(lottoRankCountResult);

        return lottoDtoMapper.toLottoRankResultResponse(lottoRankResult);
    }

    public List<LottoRankResponse> getLottoRankResults(LottoRankResultResponse lottoRankResultResponse) {
        return new ArrayList<>(lottoRankResultResponse.getKeys().stream()
                .map(rank -> {
                    int rankMatchCount = lottoRankResultResponse.getValue(rank);
                    return LottoRankResponse.of(rank, rankMatchCount);
                })
                .toList());
    }

    public double calculateProfitRate(int lottoTicketCount, LottoRankResultResponse lottoRankResultResponse) {
        int purchasedAmount = lottoTicketCount * LOTTO_PRICE;

        LottoRankResult lottoRankResult = lottoDtoMapper.toLottoRankResult(lottoRankResultResponse);

        int profit = lottoRankResult.getKeys().stream()
                .mapToInt(rank -> rank.getWinningAmount() * lottoRankResult.getValue(rank)).sum();
        return (double) profit / purchasedAmount;
    }

    private void validateAmountUnit(int purchaseAmount) {
        if (purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }
}
