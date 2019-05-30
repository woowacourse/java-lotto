package lotto.domain.lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoGenerateLotto {

    private final Lotto lotto;

    public AutoGenerateLotto(int price){
        this.lotto = makeAutoLotto(createNumber());
    }

    private Lotto makeAutoLotto(List<LottoNumber> createNumber){
        Collections.shuffle(createNumber);
        return new Lotto(createNumber.stream()
                .limit(6)
                .collect(Collectors.toList()));
    }

    private List<LottoNumber> createNumber(){
        return IntStream.rangeClosed(1,45)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public Lotto getAutoLotto() {
        return lotto;
    }
    
}
