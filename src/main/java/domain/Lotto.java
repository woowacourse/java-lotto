package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

    private static final List<LottoNumber> allLottoNumbers = IntStream.range(1, 45)
            .boxed()
            .map(LottoNumber::of)
            .collect(Collectors.toList());

    private final List<LottoNumber> numbers;

    public Lotto() {
        Collections.shuffle(allLottoNumbers);
        List<LottoNumber> lottoNums = new ArrayList<>(allLottoNumbers.subList(0, 6));
        Collections.sort(lottoNums);

        this.numbers = lottoNums;
    }

    public Lotto(List<LottoNumber> lottoNums) {
        if (lottoNums.size() != 6) {
            throw new RuntimeException();
        }
        Collections.sort(lottoNums);
        this.numbers = lottoNums;
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "Lotto{" + "numbers=" + numbers + '}';
    }
}
