package domain;

import static util.constant.Values.LOTTO_MAX_NUM;
import static util.constant.Values.LOTTO_MIN_NUM;
import static util.constant.Values.LOTTO_SIZE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {

    private LottoFactory() {
    }

    public static List<Lotto> createLottos(Money money) {
        List<Lotto> lottos = new ArrayList<>();

        int totalLotto = money.calculateTotalLotto();
        for (int i = 0; i < totalLotto; i++) {
            lottos.add(new Lotto(createNumbers()));
        }
        return lottos;
    }

    private static List<Integer> createNumbers() {
        List<Integer> numbers = IntStream.rangeClosed(LOTTO_MIN_NUM, LOTTO_MAX_NUM)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(numbers);

        List<Integer> selectedNumbers = numbers.subList(0, LOTTO_SIZE);
        Collections.sort(selectedNumbers);

        return selectedNumbers;
    }
}
