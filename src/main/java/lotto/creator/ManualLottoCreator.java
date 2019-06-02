package lotto.creator;

import lotto.domain.Lotto;
import lotto.domain.Number;
import lotto.util.StringUtil;

import java.util.List;
import java.util.ArrayList;

public class ManualLottoCreator implements LottoCreator {

    private final List<String> manuals;

    public ManualLottoCreator(List<String> manuals) {
        this.manuals = manuals;
    }

    @Override
    public List<Lotto> create() {
        List<Lotto> lottos = new ArrayList<>();
        for (String manual : manuals) {
            lottos.add(createLotto(manual));
        }
        return lottos;
    }

    public Lotto createLotto(String numbers) {
        List<Number> lotto = new ArrayList<>();

        for (String number : StringUtil.parseString(numbers)) {
            lotto.add(Number.valueOf(Integer.parseInt(number)));
        }

        return new Lotto(lotto);
    }
}
