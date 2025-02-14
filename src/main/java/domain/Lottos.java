package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private static final int MIN_QUANTITY = 1;
    private static final int MAX_QUANTITY = 100;
    private static final String ERROR_INVALID_QUANTITY =
            "로또는 " + MIN_QUANTITY + "장부터 최대 " + MAX_QUANTITY + "장까지 구매 가능합니다.";

    private final List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos ofSize(final int quantity) {
        validateQuantity(quantity);
        List<Lotto> lottos = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            Lotto generatedLotto = Lotto.of(LottoGenerator.generate());
            lottos.add(generatedLotto);
        }
        return new Lottos(lottos);
    }

    private static void validateQuantity(final int quantity) {
        if (quantity <= 0 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(ERROR_INVALID_QUANTITY);
        }
    }

    public static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
    }

    public List<String> getPurchasedLottos() {
        return lottos.stream().map(Lotto::toString).toList();
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getQuantity() {
        return lottos.size();
    }
}
