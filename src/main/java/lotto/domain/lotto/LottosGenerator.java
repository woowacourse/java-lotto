package lotto.domain.lotto;

import lotto.domain.purchase.PurchaseCount;

import java.util.ArrayList;
import java.util.List;

public class LottosGenerator {
    public static Lottos generate(PurchaseCount purchaseCount, List<List<Integer>> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        lottos.addAll(generateLottos(numbers));
        lottos.addAll(generateLottos(purchaseCount.calculateAutoCount()));
        return Lottos.of(lottos);
    }

    private static List<Lotto> generateLottos(List<List<Integer>> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> lotto : numbers) {
            lottos.add(LottoMaker.generator(lotto));
        }
        return lottos;
    }

    private static List<Lotto> generateLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(LottoMaker.generator());
        }
        return lottos;
    }
}
