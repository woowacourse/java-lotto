package domain;

import static domain.Lotto.LOTTO_PRICE;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingLong;

import dto.LottoResultDto;
import java.util.EnumMap;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.function.Function;

public class LottoResultHandler {
    private LottoResultHandler() {
    }

    public static LottoResultDto getLottoResultDto(WinningLotto winningLotto, Lottos lottos) {
        Map<Rank, LongSummaryStatistics> lottoResult = getLottoResultGroupByRank(winningLotto, lottos);
        long totalReturn = getTotalReturnByLottoResult(lottoResult);
        double rateOfReturn = getRateOfReturn(lottos, totalReturn);

        return LottoResultDto.of(lottoResult, rateOfReturn);
    }

    private static Map<Rank, LongSummaryStatistics> getLottoResultGroupByRank(WinningLotto winningLotto,
                                                                              Lottos lottos) {
        return lottos.getLottos()
                .stream()
                .map(winningLotto::getRankByLotto)
                .collect(groupingBy(Function.identity(), () -> new EnumMap<>(Rank.class),
                        summarizingLong(Rank::getPrize)));
    }

    private static long getTotalReturnByLottoResult(Map<Rank, LongSummaryStatistics> lottoResult) {
        return lottoResult.values()
                .stream()
                .mapToLong(LongSummaryStatistics::getSum)
                .sum();
    }

    private static double getRateOfReturn(Lottos lottos, long totalReturn) {
        return (double) totalReturn / (lottos.getLottos().size() * LOTTO_PRICE);
    }
}
