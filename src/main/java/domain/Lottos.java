package domain;

import static domain.properties.LottoProperties.MAX_PURCHASABLE_LOTTOS;

import domain.lottogenerator.LottoGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    private Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos ofSize(final int quantity, LottoGenerator lottoGenerator) {
        validateQuantity(quantity);
        List<Lotto> lottos = new ArrayList<>(quantity);
        for (int i = 0; i < quantity; i++) {
            Lotto generatedLotto = Lotto.of(lottoGenerator.generate());
            lottos.add(generatedLotto);
        }
        return new Lottos(lottos);
    }

    private static void validateQuantity(final int quantity) {
        if (quantity <= 0 || quantity > MAX_PURCHASABLE_LOTTOS) {
            throw new IllegalArgumentException(
                    "로또는 1장부터 최대 " + MAX_PURCHASABLE_LOTTOS + "장까지 구매 가능합니다.");
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
