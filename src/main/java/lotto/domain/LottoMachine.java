package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public static final int LOTTO_UNIT_PRICE = 1000;
    private static final int MAX_PURCHASE_AMOUNT = 100000;

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoMachine(final LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public List<Lotto> purchase(final int purchaseAmount) {
        validatePurchaseAmount(purchaseAmount);
        int lottoAmount = purchaseAmount / LOTTO_UNIT_PRICE;
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoAmount; i++) {
            Lotto lotto = createLotto();
            lottos.add(lotto);
        }
        return lottos;
    }

    private void validatePurchaseAmount(final int purchaseAmount) {
        if (purchaseAmount % LOTTO_UNIT_PRICE != 0) {
            throw new IllegalArgumentException(
                    "구입금액은 %d원으로 나누어져야 합니다. [입력: %d]".formatted(LOTTO_UNIT_PRICE, purchaseAmount));
        }

        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("구입금액은 양수여야 합니다. [입력: %d]".formatted(purchaseAmount));
        }

        if (purchaseAmount > MAX_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(
                    "구입금액은 최대 %d원까지입니다. [입력: %d]".formatted(MAX_PURCHASE_AMOUNT, purchaseAmount));
        }
    }

    private Lotto createLotto() {
        return new Lotto(lottoNumberGenerator.generate());
    }
}
