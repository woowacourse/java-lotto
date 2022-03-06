package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottosFactory {

    public static List<Lotto> generate(List<Lotto> manualLottos, int countOfAutoLotto) {
        return List.copyOf(buyLottos(manualLottos, countOfAutoLotto));
    }

    private static List<Lotto> buyLottos(List<Lotto> manualLottos, int countOfAutoLotto) {
        List<Lotto> lottos = new ArrayList<>(manualLottos);
        lottos.addAll(buyLottosByAuto(countOfAutoLotto));
        return lottos;
    }

    private static List<Lotto> buyLottosByAuto(int countOfAutoLotto) {
        return IntStream.range(0, countOfAutoLotto)
                .mapToObj(i -> Lotto.generateByAuto())
                .collect(Collectors.toList());
    }
}
