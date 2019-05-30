package lotto.domain;

import java.util.*;

public class LottoFactory {
    // TODO depth check
    public static List<Lotto> createLottosAutomatically(int countOfLottos) {
        List<Lotto> lottos = new ArrayList<>();

        while (lottos.size() < countOfLottos) {
            Lotto lotto = createLottoAutomatically();
            if (!lottos.contains(lotto)) {
                lottos.add(lotto);
            }
        }
        return lottos;
    }

    private static Lotto createLottoAutomatically() {
        return new Lotto(LottoNumber.getShuffledNumber());
    }

    public static Lotto createLottoManually(List<Integer> userNumbers) {
        Set<LottoNumber> numbers = new TreeSet<>();
        for (Integer userNumber : userNumbers) {
            numbers.add(LottoNumber.get(userNumber));
        }
        return new Lotto(numbers);
    }
}