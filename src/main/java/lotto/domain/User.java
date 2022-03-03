package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class User {

    private long money;
    private int countOfManualLotto;
    private final List<Lotto> lottos;

    private User(long money, int countOfManualLotto, List<Lotto> lottos) {
        this.money = money;
        this.countOfManualLotto = countOfManualLotto;
        this.lottos = new ArrayList<>(lottos);
    }

    public static User generateWithManualLottos(long money, int countOfManualLotto, List<Lotto> manualLottos) {
        int countOfAutoLotto = calcualteCountOfAutoLotto(money, countOfManualLotto);
        return new User(money, countOfManualLotto, buyLottosWithManualLottos(manualLottos, countOfAutoLotto));
    }

    private static int calcualteCountOfAutoLotto(long money, int countOfManualLotto) {
        return (int) (money / Lotto.LOTTO_PRICE) - countOfManualLotto;
    }

    private static List<Lotto> buyLottosWithManualLottos(List<Lotto> manualLottos, int countOfAutoLotto) {
        List<Lotto> lottos = new ArrayList<>(manualLottos);
        lottos.addAll(buyLottosByAuto(countOfAutoLotto));
        return lottos;
    }

    private static List<Lotto> buyLottosByAuto(int countOfAutoLotto) {
        return IntStream.range(0, countOfAutoLotto)
                .mapToObj(i -> Lotto.generateByAuto())
                .collect(Collectors.toList());
    }

    public double calculateProfitRate(long totalPrize) {
        return (double) totalPrize / (double) money;
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
