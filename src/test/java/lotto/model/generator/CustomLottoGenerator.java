package lotto.model.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.number.LottoNumber;
import lotto.model.number.LottoNumbers;
import org.opentest4j.TestAbortedException;

public class CustomLottoGenerator implements LottoGenerator {

    private static final String LOTTO_TEST_ERROR = "[ERROR] 테스트 시에는 로또 갯수를 40개를 넘을 수 없습니다.";

    @Override
    public Lottos generateLottos(int lottoCount, int minimumNumber, int maximumNumber, int lottoLength) {
        return makeLottos(lottoCount, minimumNumber, maximumNumber, lottoLength);
    }

    private Lottos makeLottos(int lottoCount, int minimumNumber, int maximumNumber, int lottoLength) {
        if (lottoCount > maximumNumber - lottoLength) {
            throw new TestAbortedException(LOTTO_TEST_ERROR);
        }
        List<Integer> numberCollection = makeNumberCollection(minimumNumber, maximumNumber);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = makeLotto(
                    makeLottoNumbers(makeLottoNumber(numberCollection.subList(0 + i, lottoLength + i))));
            lottos.add(lotto);
        }
        return new Lottos(lottos);
    }

    private Lotto makeLotto(LottoNumbers lottoNumbers) {
        return new Lotto(lottoNumbers);
    }

    private LottoNumbers makeLottoNumbers(List<LottoNumber> lottoNumbers) {
        return new LottoNumbers(lottoNumbers);
    }

    private List<LottoNumber> makeLottoNumber(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private List<Integer> makeNumberCollection(int minimumNumber, int maximumNumber) {
        return IntStream.rangeClosed(minimumNumber, maximumNumber)
                .boxed()
                .collect(Collectors.toList());
    }
}
