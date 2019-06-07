package lotto.domain;

import java.util.*;

public class LottoFactory {
    private static final String DELIMITER = ",";

    public static Lotto createLottoAutomatically() {
        return new Lotto(LottoNumber.getShuffledNumbers());
    }

    public static Lotto createLottoManually(List<String> manualNumbers) {
        Set<LottoNumber> numbers = new TreeSet<>();
        for (String manualNumber : manualNumbers) {
            numbers.add(LottoNumber.get(Integer.parseInt(manualNumber)));
        }
        return new Lotto(numbers);
    }

    public static Lottos createLottos(List<String> manualLottoNumbers, int countOfPurchase) {
        List<Lotto> lottos = new ArrayList<>();

        for (String manualNumber : manualLottoNumbers) {
            lottos.add(LottoFactory.createLottoManually(Arrays.asList(manualNumber.split(DELIMITER))));
        }
        while (lottos.size() < countOfPurchase) {
            lottos.add(LottoFactory.createLottoAutomatically());
        }

        return new Lottos(lottos);
    }
}