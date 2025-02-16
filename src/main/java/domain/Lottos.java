package domain;

import domain.properties.LottoProperties;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos of(List<Lotto> lottos) {
        return new Lottos(lottos);
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
        if (quantity <= 0 || quantity > LottoProperties.MAX_QUANTITY) {
            throw new IllegalArgumentException("로또는 1장부터 최대 " + LottoProperties.MAX_QUANTITY + "장까지 구매 가능합니다.");
        }
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
