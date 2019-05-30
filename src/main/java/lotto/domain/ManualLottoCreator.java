package lotto.domain;

import java.util.List;
import java.util.ArrayList;

public class ManualLottoCreator implements LottoCreator {

    private final List<String[]> manuals;

    public ManualLottoCreator(List<String[]> manuals) {
        this.manuals = manuals;
    }

    @Override
    public List<Lotto> create() {
        List<Lotto> lottos = new ArrayList<>();
        for (String[] manual : manuals) {
            lottos.add(createLotto(manual));
        }
        return lottos;
    }

    public Lotto createLotto(String[] numbers) {
        List<Number> lotto = new ArrayList();
        for (String number : numbers) {
            lotto.add(Number.valueOf(Integer.parseInt(number)));
        }

        return new Lotto(lotto);
    }
}
