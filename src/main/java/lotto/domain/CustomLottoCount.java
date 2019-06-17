package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class CustomLottoCount {
    private static final int MIN_CUSTOM_LOTTO_COUNT = 0;
    private final int customLottoCount;

    public CustomLottoCount(final int customLottoCount, Money money) {
        if (customLottoCount < MIN_CUSTOM_LOTTO_COUNT) {
            throw new IllegalArgumentException("수동로또 개수로 음수는 안됩니다.");
        }

        if (money.isOverPrice(customLottoCount)) {
            throw new IllegalArgumentException("금액에 맞는 수동로또 개수를 입력해 주세요.");
        }

        this.customLottoCount = customLottoCount;
    }

    public int getCustomLottoCount() {
        return customLottoCount;
    }

    public List<Integer> getCustomLottoCountInOrder() {
        List<Integer> customLottoCountOrders = new ArrayList<>();
        for (int i = 1; i <= customLottoCount; i++) {
            customLottoCountOrders.add(i);
        }
        return customLottoCountOrders;
    }
}
