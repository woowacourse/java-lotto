package domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoPurchaseInfo {
    private static final String ERROR_MESSAGE_MANUAL_LOTTO_NUMBERS_NULL = "수동 로또들의 값이 null 입니다.";
    private static final String ERROR_MESSAGE_LOTTO_PURCHASE_COUNT_NULL = "수동, 자동 로또의 갯수의 값이 null 입니다.";

    private final List<List<Integer>> lottoNumbers;
    private final LottoPurchaseCount lottoPurchaseCount;

    public LottoPurchaseInfo(List<List<Integer>> lottoNumbers, LottoPurchaseCount lottoPurchaseCount) {
        Objects.requireNonNull(lottoNumbers, ERROR_MESSAGE_MANUAL_LOTTO_NUMBERS_NULL);
        Objects.requireNonNull(lottoPurchaseCount, ERROR_MESSAGE_LOTTO_PURCHASE_COUNT_NULL);
        this.lottoNumbers = lottoNumbers;
        this.lottoPurchaseCount = lottoPurchaseCount;
    }

    public List<List<Integer>> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public int getAutomaticCount() {
        return lottoPurchaseCount.getAutomaticCount();
    }
}
