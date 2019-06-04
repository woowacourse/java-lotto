package lotto.domain;

import java.util.*;

public class LottoFactory {
    public static Lottos createRandomLottos(int numOfLottos) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < numOfLottos; i++) {
            lottos.add(new Lotto(createRandomLottoNumbers()));
        }
        return new Lottos(lottos);
    }

    private static List<Integer> createRandomLottoNumbers() {
        Set<Integer> lottoNumbers = new TreeSet<>();
        while (lottoNumbers.size() < Lotto.getSizeOfLottoNumbers()) {
            lottoNumbers.add(getRandomIntBetween(LottoNumber.getMinLottoNumber(),
                    LottoNumber.getMaxLottoNumber()));
        }
        return new ArrayList<>(lottoNumbers);
    }

    private static int getRandomIntBetween(int min, int max) {
        return (int)(Math.random() * (max - min)) + min + 1;
    }

    public static Lottos createCustomLottos(List<List<Integer>> numbers) {
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> integers : numbers) {
            lottos.add(new Lotto(integers));
        }
        return new Lottos(lottos);
    }
}
