package lotto.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private final int manualCount;
    private final int autoCount;

    public LottoGenerator(final int manualCount, final int autoCount) {
        this.manualCount = manualCount;
        this.autoCount = autoCount;
    }

    public static LottoGenerator of(final Money money, final int manualCount) {
        int totalCount = money.calculateLottoCount();
        checkInputMoneyEnough(manualCount, totalCount);
        return new LottoGenerator(manualCount, totalCount - manualCount);
    }

    private static void checkInputMoneyEnough(final int manualCount, final int totalCount) {
        if (totalCount < manualCount) {
            throw new IllegalArgumentException("[ERROR] 금액이 부족합니다.");
        }
    }

    public Lottos generateLottos(final List<List<Integer>> numbers) {
        checkRightManualNumbers(numbers);
        List<Lotto> manualLottos = generateManualLottos(numbers);
        manualLottos.addAll(generateRandomLottos());
        return new Lottos(manualLottos);
    }

    private void checkRightManualNumbers(List<List<Integer>> numbers) {
        if (numbers.size() != manualCount) {
            throw new IllegalArgumentException("[ERROR] 수동 번호가 올바르지 않습니다.");
        }
    }

    private List<Lotto> generateManualLottos(List<List<Integer>> numbers) {
        List<Lotto> manualLottos = numbers.stream()
                .map(number -> new Lotto(number.stream()
                        .map(LottoNumber::valueOf)
                        .collect(Collectors.toCollection(LinkedHashSet::new))))
                .collect(Collectors.toList());
        return manualLottos;
    }

    private  List<Lotto> generateRandomLottos() {
        return IntStream.range(0, autoCount)
                .mapToObj(index -> new Lotto(RandomLottoMachine.createRandomLottoNumbers()))
                .collect(Collectors.toList());
    }
}
