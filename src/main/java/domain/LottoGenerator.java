package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final int MIN_RANGE = 0;
    private static final int MAX_RANGE = 6;

    private static final int START_INDEX = 0;
    private static final int FIRST_INDEX = 0;

    private static final List<Integer> LOTTO_NUMBER_POOL = IntStream.rangeClosed(MIN_LOTTO_NUMBER,
            MAX_LOTTO_NUMBER)
        .boxed()
        .collect(Collectors.toList());


    private final List<List<Integer>> manualLottoNumbers;
    private final int autoAmount;


    public LottoGenerator(List<List<Integer>> manualLottoNumbers, int autoAmount) {
        this.manualLottoNumbers = new ArrayList<>(manualLottoNumbers);
        this.autoAmount = autoAmount;
    }

    public List<Lotto> generate() {
        List<Lotto> lottos = new ArrayList<>();
        if (!isEmptyManualLottoNumbers()) {
            lottos.addAll(generateManualLottos(manualLottoNumbers));
        }
        lottos.addAll(generateAutoLottos());
        return new ArrayList<>(lottos);
    }

    private List<Lotto> generateManualLottos(List<List<Integer>> lottoNumbers) {

        List<Lotto> lottos = new ArrayList<>();

        for (List<Integer> lottoNumber : lottoNumbers) {
            lottos.add(
                new Lotto(lottoNumber.stream().map(LottoNumber::new).collect(Collectors.toList())));
        }

        return new ArrayList<>(lottos);
    }

    private List<Lotto> generateAutoLottos() {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = START_INDEX; i < autoAmount; ++i) {
            lottos.add(generateAutoLotto());
        }
        return new ArrayList<>(lottos);
    }

    private Lotto generateAutoLotto() {
        Collections.shuffle(LOTTO_NUMBER_POOL);
        List<Integer> lottoNumbers = new ArrayList<>(
            LOTTO_NUMBER_POOL.subList(MIN_RANGE, MAX_RANGE));
        Collections.sort(lottoNumbers);
        return new Lotto(lottoNumbers.stream().map(LottoNumber::new).collect(Collectors.toList()));
    }

    private boolean isEmptyManualLottoNumbers() {
        return manualLottoNumbers.get(FIRST_INDEX).isEmpty();
    }


}
