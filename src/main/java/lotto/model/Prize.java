package lotto.model;

public class Prize {

    public static double sumPrize() {
        return LottoResultMap.resultCount.keySet().stream()
            .mapToDouble(lottoResult -> LottoResultMap.resultCount.get(lottoResult).prizeResult())
            .sum();
    }
}