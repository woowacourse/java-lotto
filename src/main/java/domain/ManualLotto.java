package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ManualLotto {

    List<Integer> manualLottoNumbers;

    public ManualLotto(List<Integer> manualLottoNumbers) {
        this.manualLottoNumbers = manualLottoNumbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Integer> getManualLottoNumbers() {
        return Collections.unmodifiableList(manualLottoNumbers);
    }
}
