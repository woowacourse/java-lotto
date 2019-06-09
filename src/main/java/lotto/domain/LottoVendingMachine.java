package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoVendingMachine {
    public static Lottos getLottos(LottoCount lottoCount, List<List<Integer>> customLottoNumbers) {
        if (customLottoNumbers.size() != lottoCount.custom()) {
            throw new InvalidCustomLottoNumbersException("수동으로 구매하기로 한 로또 수와 일치하지 않습니다.");
        }
        Lottos userLottos = createCustomLottos(customLottoNumbers);
        return userLottos.add(createRandomLottos(lottoCount.random()));
    }

    public static Lottos createRandomLottos(int numOfLottos) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numOfLottos; i++) {
            lottos.add(Lotto.create(new RandomLottoNumberGenerator()));
        }
        return new Lottos(lottos);
    }

    public static Lottos createCustomLottos(List<List<Integer>> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> integers : numbers) {
            lottos.add(Lotto.create(new ManualLottoNumberGenerator(integers)));
        }
        return new Lottos(lottos);
    }
}