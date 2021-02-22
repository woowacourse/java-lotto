package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class ManualLottoGenerator implements LottoGenerator {
    private final List<int[]> lottoNumberStored;
    private int index = 0;

    public ManualLottoGenerator(List<int[]> numbersSequence) {
        this.lottoNumberStored = numbersSequence;
    }

    @Override
    public Lotto createLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number : lottoNumberStored.get(index++)) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return new Lotto(lottoNumbers);
    }
}
