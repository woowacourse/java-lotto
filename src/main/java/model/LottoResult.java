package model;

import dto.LottoResultDetailResponse;
import dto.LottoResultsResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResult {

    private Map<RankType, Integer> results;

    public LottoResult(Lottos lottos, WinningLotto winningLotto) {
        results = sort(winningLotto.evaluateRank(lottos.getLottos()));
    }

    public LottoResultsResponse createResponse() {
        List<LottoResultDetailResponse> detailResponses = results.entrySet().stream()
                .filter(result -> result.getKey() != RankType.NONE)
                .map(result -> new LottoResultDetailResponse(
                        result.getKey().createResponse(),
                        result.getValue()
                )).toList();

        return new LottoResultsResponse(
                detailResponses
        );
    }

    public Map<RankType, Integer> sort(Map<RankType, Integer> rankResult) {
        return rankResult.entrySet()
                .stream()
                .sorted((r1, r2) -> Integer.compare(r2.getKey().getRank(), r1.getKey().getRank()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public int calculateTotalPrice() {
        return results.entrySet()
                .stream()
                .mapToInt(result -> result.getKey().getPrice() * result.getValue())
                .sum();
    }
}
