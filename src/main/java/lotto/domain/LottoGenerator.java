package lotto.domain;

import java.util.*;
import java.util.stream.Stream;

public class LottoGenerator {

    public static final int LOTTO_START_NUMBER = 1;
    public static final int LOTTO_END_NUMBER = 45;
    public static final int LOTTO_POSSESSION_NUMBER = 6;
    private static final List<Integer> candidateNumbers = new ArrayList<>();

    static {
        for (int number = LOTTO_START_NUMBER; number <= LOTTO_END_NUMBER; number++) {
            candidateNumbers.add(number);
        }
    }

    public static Lottos createManualLottos(List<String> manualLottoNumbers) {
        List<Lotto> createdManualLottos = new ArrayList<>();
        manualLottoNumbers.forEach(number -> createdManualLottos.add(Lotto.from(number)));
        return new Lottos(createdManualLottos);
    }

    public static Lottos createAutoLottos(int autoLottoCount) {
        List<Lotto> createdAutoLottos = new ArrayList<>();
        Set<Integer> lottoNumbers;
        for (int i = 0; i < autoLottoCount; i++) {
            Collections.shuffle(candidateNumbers);
            lottoNumbers = new TreeSet<>(candidateNumbers.subList(0, LOTTO_POSSESSION_NUMBER));
            createdAutoLottos.add(Lotto.from(new ArrayList<>(lottoNumbers)));
        }

        return new Lottos(createdAutoLottos);
    }
}