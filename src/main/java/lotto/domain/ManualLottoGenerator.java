package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManualLottoGenerator implements LottoGenerator {

    private final List<Integer> manualLottoNumbers;

    public ManualLottoGenerator(List<Integer> manualLottoNumbers) {
        this.manualLottoNumbers = manualLottoNumbers;
    }

    @Override
    public Set<LottoNumber> generateNumbers() {
        Set<Integer> duplicationManualLottoNumbers = new HashSet<>(manualLottoNumbers);
        Set<LottoNumber> lotto = new HashSet<>();

        for (Integer duplicationManualLottoNumber : duplicationManualLottoNumbers) {
            lotto.add(new LottoNumber(duplicationManualLottoNumber));
        }
        return lotto;
    }

}