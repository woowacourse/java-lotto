package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.domain.lotto.Lotto.*;

public class AutoGenerateLotto {
    public AutoGenerateLotto(int numberOfPrice, List<Lotto> lottos){
        for (int i = 0; i < numberOfPrice; i++) {
            lottos.add(makeAutoLotto(createNumber()));
        }
    }

    private Lotto makeAutoLotto(List<LottoNumber> createNumber){
        Collections.shuffle(createNumber);
        return new Lotto(createNumber.stream()
                .limit(NUMBER_OF_LOTTO_NUMBER)
                .collect(Collectors.toList()));
    }

    private List<LottoNumber> createNumber(){
        return IntStream.rangeClosed(MIN_LOTTO_NUMBER,MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }
}