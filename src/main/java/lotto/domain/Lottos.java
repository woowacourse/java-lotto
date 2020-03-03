package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lottos {
    private static final String INCORRECT_INDEX_MSG = "로또 번호에 잘못된 접근입니다.";
    private static final String EMPTY_INPUT_MSG = "%s가 입력되지 않았습니다.";
    public static final int PRICE_PER_LOTTO = 1_000;

    private List<Lotto> lottos;

    public Lottos() {
        this.lottos = new ArrayList<>();
    }

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public List<Lotto> getLottos() {
        return this.lottos;
    }

    public int getLottosSize() {
        return this.lottos.size();
    }

    public Lottos addLottos(Lottos lottos) {
        Objects.requireNonNull(lottos, String.format(EMPTY_INPUT_MSG, "로또"));
        this.lottos.addAll(lottos.getLottos());
        return this;
    }

    public Set<LottoNumber> getLottoNumbers(int index) {
        if (index < 0 || index >= this.lottos.size()) {
            throw new IllegalArgumentException(INCORRECT_INDEX_MSG);
        }
         return this.lottos.get(index).getLottoNumbers();
    }
}
