package lotto.domain;

import java.util.*;

public class UserLottos {
    private final List<Lotto> lottos;

    public UserLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public UserLottos(String inputMoney) {
        int lottoCount = Integer.parseInt(inputMoney) / 1000;
        lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(LottoGenerator.lotto());
        }
    }

    public LottoResult match(WinningLotto winningLotto) {
        LottoResult lottoResult = new LottoResult();
        for (Lotto lotto : lottos) {
            lottoResult.plus(winningLotto.match(lotto));
        }
        return lottoResult;
    }

    public int size() {
        return lottos.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(size()).append("개 구매했습니다.\n");
        for (Lotto lotto : lottos) {
            stringBuilder.append(lotto.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
