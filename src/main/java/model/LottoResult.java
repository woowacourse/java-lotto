package model;

import dto.LottoResultDetailResponse;
import dto.LottoResultsResponse;
import dto.ROIResultResponse;

import java.util.*;
import java.util.stream.Collectors;

public class LottoResult {

    private Map<RankType, Integer> results;

    public LottoResult(Lottos lottos, WinningLotto winningLotto) {
        results = winningLotto.evaluateRank(lottos.getLottos());
        sort();
    }

    public LottoResultsResponse createResponse() {
        List<LottoResultDetailResponse> detailResponses = results.entrySet().stream()
                .map(result -> new LottoResultDetailResponse(
                        result.getKey().createResponse(),
                        result.getValue()
                )).toList();

        return new LottoResultsResponse(
            detailResponses
        );
    }

    public void sort() { // TODO: 리팩토링 필요
        results = results.entrySet().stream()
                .sorted((r1, r2) -> Integer.compare(r2.getKey().getRank(), r1.getKey().getRank()))
                .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                ));
    }

    public int calculateTotalPrice() {
        return results.entrySet().stream()
                .mapToInt(result -> result.getKey().getPrice() * result.getValue())
                .sum();
    }
}
