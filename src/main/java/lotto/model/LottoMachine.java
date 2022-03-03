package lotto.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.util.RandomLottoGenerator;

public class LottoMachine {

    public static final String POSITIVE_ERROR_MESSAGE = "0보다 큰 정수를 입력해 주세요.";

    private final int autoCount;
    private final int manualCount;

    public LottoMachine(int count, int manualCount) {
        this.autoCount = count - manualCount;
        this.manualCount = validatePositive(manualCount);
    }

    private int validatePositive(int manualCount) {
        if (manualCount < 0) {
            throw new IllegalArgumentException(POSITIVE_ERROR_MESSAGE);
        }
        return manualCount;
    }

    public List<Lotto> createLottos(List<Lotto> lottos) {
        lottos.addAll(createAutoLottos());
        return lottos;
    }

    private List<Lotto> createAutoLottos() {
        return IntStream.range(manualCount, count())
            .mapToObj(i -> RandomLottoGenerator.generateAutoLotto())
            .collect(Collectors.toList());
    }

    private int count() {
        return autoCount + manualCount;
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
