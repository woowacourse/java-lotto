package lotto.domain.lottogenerator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ManualLottoGeneratingStrategy implements LottoGeneratingStrategy {
    private final List<Integer> inputLottoNumbers;

    public ManualLottoGeneratingStrategy(List<Integer> inputLottoNumbers) {
        if (Objects.isNull(inputLottoNumbers) || inputLottoNumbers.isEmpty()) {
            throw new NullPointerException();
        }
        this.inputLottoNumbers = inputLottoNumbers;
    }

    @Override
    public List<Integer> generate() {
        Collections.sort(inputLottoNumbers);

        return Collections.unmodifiableList(inputLottoNumbers);
    }
}
