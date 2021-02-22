package lotto.domain;

import lotto.util.LottoGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutoLotto {

    private List<Lotto> autoLotto;

    public AutoLotto(int autoCount){
        this.autoLotto = buyAutoLotto(autoCount);
    }

    private List<Lotto> buyAutoLotto(int count) {
        return Stream.generate(()->new Lotto(LottoGenerator.make()))
                .limit(count)
                .collect(Collectors.toList());

    }

    public List<Lotto> getAutoLotto() {
        return autoLotto;
    }
}
