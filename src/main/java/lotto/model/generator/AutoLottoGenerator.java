package lotto.model.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;

public class AutoLottoGenerator implements LottoGenerator {

    private static final int LOTTO_START_INDEX = 0;

    @Override
    public Lottos generateLottos(int lottoCount, int minimumNumber, int maximumNumber, int lottoLength)
            throws IllegalArgumentException {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            LottoNumbers lottoNumbers = makeLottoNumbers(minimumNumber, maximumNumber, lottoLength);
            lottos.add(makeLotto(lottoNumbers));
        }
        return new Lottos(lottos);
    }

    private LottoNumbers makeLottoNumbers(int minimumNumber, int maximumNumber, int lottoLength) {
        List<Integer> numberCollection = makeNumberCollection(minimumNumber, maximumNumber);
        Collections.shuffle(numberCollection);
        return new LottoNumbers(numberCollection.subList(LOTTO_START_INDEX, lottoLength).stream()
                .sorted()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private List<Integer> makeNumberCollection(int minimumNumber, int maximumNumber) {
        return IntStream.rangeClosed(minimumNumber, maximumNumber)
                .boxed()
                .collect(Collectors.toList());
    }

    private Lotto makeLotto(LottoNumbers lottoNumbers) {
        return new Lotto(lottoNumbers);
    }
}
