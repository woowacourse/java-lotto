package domain;

import java.util.*;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    /*
     XXX :: lottos는 변하지 않는데, 결과를 반환하기 위해 매번 Map과 LottoResults를 생성해야하는 문제
            LottoResults를 인스턴스 멤버로 갖게하고, Lottos 생성 시 한 회만 초기화해주고 싶은데
            Lottos가 일급 컬렉션이기 때문에 LottoResults를 인스턴스 변수로 가져도 될지..
     */
    public LottoResults getLottoResults(WinningLotto winningLotto) {
        Map<LottoRank, Long> results = new HashMap<>();

        for (LottoRank rank : LottoRank.values()) {
            results.put(rank, 0L);
        }

        for (Lotto lotto : lottos) {
            LottoRank rank = winningLotto.match(lotto);
            results.put(rank, results.get(rank) + 1);
        }
        return new LottoResults(results);
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}