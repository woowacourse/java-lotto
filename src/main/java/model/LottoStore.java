package model;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import model.numbers.LottoNumbers;
import model.numbers.LottoNumbersGenerator;
import model.numbers.WinningLotto;
import model.rank.LottoRank;
import model.rank.LottoRankCalculator;
import model.rank.LottoRankResult;

public class LottoStore {
    private final LottoNumbersGenerator lottoNumbersGenerator;
    private final LottoRankCalculator lottoRankCalculator;

    public LottoStore(LottoNumbersGenerator lottoNumbersGenerator, LottoRankCalculator lottoRankCalculator) {
        this.lottoNumbersGenerator = lottoNumbersGenerator;
        this.lottoRankCalculator = lottoRankCalculator;
    }

    public List<LottoNumbers> purchase(PaidAmount paidAmount) {
        int purchasedAmount = paidAmount.getUnitCount();
        return IntStream.range(0, purchasedAmount)
                .mapToObj(count -> lottoNumbersGenerator.generate())
                .toList();
    }

    public LottoRankResult calculateRankMatchCount(List<LottoNumbers> lottoNumbers, WinningLotto winningLotto) {
        List<LottoRank> lottoRanks = calculateRank(lottoNumbers, winningLotto);

        LottoRankResult lottoRankResult = new LottoRankResult();
        for (LottoRank lottoRank : lottoRanks) {
            lottoRankResult.updateRankCount(lottoRank);
        }

        return lottoRankResult;
    }

    public double calculateProfitRate(PaidAmount paidAmount, LottoRankResult lottoRankResult) {
        int profit = lottoRankResult.getRanks().stream()
                .mapToInt(rank -> rank.getPrizeMoney() * lottoRankResult.getCountByRank(rank))
                .sum();

        return (double) profit / paidAmount.getAmount();
    }

    private List<LottoRank> calculateRank(List<LottoNumbers> lottoNumbers, WinningLotto winningLotto) {
        return lottoNumbers.stream().map(lottoTicket -> lottoRankCalculator.calculate(lottoTicket, winningLotto))
                .filter(Objects::nonNull).toList();
    }
}
