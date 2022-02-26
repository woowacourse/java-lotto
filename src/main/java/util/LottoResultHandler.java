package util;

import static controller.LottoController.LOTTO_PRICE;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import domain.Lottos;
import domain.Rank;
import domain.WinningLotto;
import dto.LottoResultDto;
import java.util.EnumMap;
import java.util.Map;

public class LottoResultHandler {
    private LottoResultHandler() {
    }

    public static LottoResultDto getLottoResultDto(WinningLotto winningLotto, Lottos lottos) {
        Map<Rank, Long> lottoResult = getLottoResultGroupByRank(winningLotto, lottos);
        long totalReturn = getTotalReturnByLottoResult(lottoResult);
        double rateOfReturn = getRateOfReturn(lottos, totalReturn);

        return LottoResultDto.of(lottoResult, rateOfReturn);
    }

    private static Map<Rank, Long> getLottoResultGroupByRank(WinningLotto winningLotto, Lottos lottos) {
        return lottos.getLottos()
                .stream()
                .map(winningLotto::getRankByLotto)
                .collect(groupingBy(rank -> rank, () -> new EnumMap<>(Rank.class), counting()));
    }

    private static long getTotalReturnByLottoResult(Map<Rank, Long> lottoResult) {
        return lottoResult.entrySet()
                .stream()
                .mapToLong(map -> map.getKey().getPrize() * map.getValue())
                .sum();
    }

    private static double getRateOfReturn(Lottos lottos, long totalReturn) {
        return (double) totalReturn / (lottos.getLottos().size() * LOTTO_PRICE);
    }
}
