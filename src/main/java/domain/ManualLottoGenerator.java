package domain;

import java.util.Arrays;

import static java.util.stream.Collectors.toList;

public class ManualLottoGenerator implements LottoGenerator {
    private int[] manualLotto;

    public ManualLottoGenerator(final int[] manualLotto) {
        this.manualLotto = manualLotto;
    }

    @Override
    public Lotto generateLotto() {
        return new Lotto(Arrays.stream(manualLotto)
                .mapToObj(AllLottoNumbers::get)
                .collect(toList()));
    }
}
