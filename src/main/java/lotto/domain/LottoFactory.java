package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    public static List<Lotto> createLottos(List<String[]> manuals, Money money) {
        List<Lotto> lottos = new ArrayList<>();

        for (String[] manual : manuals) {
            lottos.add(new ManualLottoCreator(manual).create());
        }

        LottoCreator lottoCreator = new AutoLottoCreator(Number.getNumberList());

        for (int i = 0; i < money.getLottoSize() - manuals.size(); i++) {
            lottos.add(lottoCreator.create());
        }

        return lottos;
    }
}
