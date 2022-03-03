package lotto.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoGenerator {

    private static final int MIN_LOTTO_COUNT = 0;

    private final int manualCount;
    private final int autoCount;

    private LottoGenerator(final int manualCount, final int autoCount) {
        checkManualCountPositiveNumber(manualCount);
        checkInputMoneyEnough(autoCount);
        this.manualCount = manualCount;
        this.autoCount = autoCount;
    }

    public static LottoGenerator createLottoGeneratorByMoneyAndManualCount(final Money money, final int manualCount) {
        int totalCount = money.calculateLottoCount();
        return new LottoGenerator(manualCount, totalCount - manualCount);
    }

    private static void checkManualCountPositiveNumber(final int manualCount) {
        if (manualCount < MIN_LOTTO_COUNT) {
            throw new IllegalArgumentException("[ERROR] 구매할 수동 로또 수는 0이상을 입력해주세요.");
        }
    }

    private static void checkInputMoneyEnough(final int autoCount) {
        if (autoCount < MIN_LOTTO_COUNT) {
            throw new IllegalArgumentException("[ERROR] 자동 구매 개수는 0개 이상이어야 합니다.");
        }
    }

    public Lottos generateLottos(final List<List<Integer>> numbers) {
        checkRightManualNumbers(numbers);
        return new Lottos(concatManualAndAutoLottos(numbers));
    }

    private void checkRightManualNumbers(final List<List<Integer>> numbers) {
        if (numbers.size() != manualCount) {
            throw new IllegalArgumentException("[ERROR] 수동 번호가 올바르지 않습니다.");
        }
    }

    private List<Lotto> concatManualAndAutoLottos(final List<List<Integer>> numbers) {
        return Stream.concat(generateManualLottos(numbers).stream(),
                        generateRandomLottos().stream())
                .collect(Collectors.toList());
    }

    private List<Lotto> generateManualLottos(final List<List<Integer>> numbers) {
        List<Lotto> manualLottos = numbers.stream()
                .map(this::createLotto)
                .collect(Collectors.toList());
        return manualLottos;
    }

    private Lotto createLotto(final List<Integer> number) {
        return new Lotto(number.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toCollection(LinkedHashSet::new)));
    }

    private List<Lotto> generateRandomLottos() {
        return IntStream.range(0, autoCount)
                .mapToObj(index -> new Lotto(RandomLottoMachine.createRandomLottoNumbers()))
                .collect(Collectors.toList());
    }

    public int getManualCount() {
        return manualCount;
    }

    public int getAutoCount() {
        return autoCount;
    }
}
