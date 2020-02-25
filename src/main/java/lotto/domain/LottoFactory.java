package lotto.domain;

import javafx.scene.effect.SepiaTone;

import java.util.*;

public class LottoFactory {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_LENGTH = 6;

    private static List<LottoNumber> lottoNumbers = new ArrayList<>();

    private LottoFactory() {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    private static class LottoFactoryHolder {
        public static final LottoFactory instance = new LottoFactory();
    }

    public static LottoFactory getInstance() {
        return LottoFactoryHolder.instance;
    }

    public static Set<LottoNumber> createRandomLottoNumbers() {
        Set<LottoNumber> lotto = new HashSet<>();
        Collections.shuffle(lottoNumbers);
        for (int i = 0; i < LOTTO_LENGTH; i++) {
            lotto.add(lottoNumbers.get(i));
        }
        return lotto;
    }

    public static Set<LottoNumber> createManualLottoNumbers(List<Integer> manualLottoNumbers) {
        Set<Integer> duplicationManualLottoNumbers = new HashSet<>(manualLottoNumbers);
        Iterator<Integer> iterator = duplicationManualLottoNumbers.iterator();
        Set<LottoNumber> lotto = new HashSet<>();
        while(iterator.hasNext()){
            lotto.add(new LottoNumber(iterator.next()));
        }
        return lotto;
    }
}
