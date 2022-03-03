package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class User {

    private final long money;
    private final int countOfManualLotto;
    private final List<Lotto> lottos;

    private User(long money, int countOfManualLotto, List<Lotto> lottos) {
        this.money = money;
        this.countOfManualLotto = countOfManualLotto;
        this.lottos = new ArrayList<>(lottos);
    }

    public static User generate(long money, int countOfManualLotto, List<Lotto> manualLottos) {
        int countOfAutoLotto = calcualteCountOfAutoLotto(money, countOfManualLotto);
        return new User(money, countOfManualLotto, buyLottos(manualLottos, countOfAutoLotto));
    }

    private static int calcualteCountOfAutoLotto(long money, int countOfManualLotto) {
        return (int) (money / Lotto.LOTTO_PRICE) - countOfManualLotto;
    }

    private static List<Lotto> buyLottos(List<Lotto> manualLottos, int countOfAutoLotto) {
        List<Lotto> lottos = new ArrayList<>(manualLottos);
        lottos.addAll(generateLottosByAuto(countOfAutoLotto));
        return lottos;
    }

    private static List<Lotto> generateLottosByAuto(int countOfAutoLotto) {
        return IntStream.range(0, countOfAutoLotto)
                .mapToObj(i -> Lotto.generateByAuto())
                .collect(Collectors.toList());
    }

    public double calculateProfitRate(long totalWinningPrize) {
        return (double) totalWinningPrize / (double) money;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public int getCountOfManualLotto() {
        return countOfManualLotto;
    }

    public int getCountOfAutoLotto() {
        return calcualteCountOfAutoLotto(money, countOfManualLotto);
    }
}
