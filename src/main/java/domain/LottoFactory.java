package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.LottoConstants.LOTTO_NUMBER_COUNT;
import static domain.LottoConstants.LOTTO_PRICE;

public class LottoFactory {
    private static final List<Integer> randomNumbers = new ArrayList<>(
            IntStream.rangeClosed(1, 45)
                    .boxed()
                    .toList()
    );


    private static Lotto makeLotto() {
        Collections.shuffle(randomNumbers);
        List<Integer> numbers = randomNumbers
                .stream()
                .limit(LOTTO_NUMBER_COUNT)
                .sorted()
                .collect(Collectors.toList());
        return new Lotto(numbers);
    }

    public static List<Lotto> makeLotto(int purchaseAmount) {
        int lottoCount = purchaseAmount / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(LottoFactory.makeLotto());
        }
        return lottos;
    }
}
