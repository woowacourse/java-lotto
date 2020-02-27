package lotto.generator;

import lotto.domain.*;

import java.util.*;

public class LottoGenerateManager {
    public static Lottos createLottos(Optional<ArrayList<String>> manualLottoNumbers, LottoSize autoLottoSize) {
        Lottos manuaLottos = createManualLottos(manualLottoNumbers);
        Lottos autoLottos = createRandomLottos(autoLottoSize);
        return manuaLottos.addLottos(autoLottos);
    }

    public static Lottos createRandomLottos(int lottoSize) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoSize; i++) {
            lottos.add(LottoGenerator.create());
        }
        return new Lottos(lottos);
    }

    public static Lottos createRandomLottos(LottoSize lottoSize) {
        return createRandomLottos(lottoSize.getLottoSize());
    }

    public static Lottos createManualLottos(Optional<ArrayList<String>> lottoNumbers) {
        List<Lotto> lottos = new ArrayList<>();

        int lottoSize = lottoNumbers.orElseGet(ArrayList<String>::new).size();
        for (int i = 0; i < lottoSize; i++) {
            String lottoNumber = lottoNumbers.get().get(i);
            lottos.add(LottoGenerator.create(lottoNumber));
        }
        return new Lottos(lottos);
    }

    public static WinningLotto createWinningLotto(String lottoNumbers, String bonusNumber) {
        Lotto lotto = LottoGenerator.create(lottoNumbers);
        return new WinningLotto(lotto, new LottoNumber(bonusNumber));
    }
}
