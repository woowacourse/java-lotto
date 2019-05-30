package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    public static List<Lotto> createLottos(List<String[]> manuals, Money money) {
        List<Lotto> lottos = new ArrayList<>();

        for (String[] manual : manuals) {
            lottos.add(new ManualLottoCreator(manual).create());
        }

        for (int i = 0; i < money.getLottoSize() - manuals.size(); i++) {
            lottos.add(new AutoLottoCreator(Number.getNumberList()).create());
        }

        return lottos;
    }
}
