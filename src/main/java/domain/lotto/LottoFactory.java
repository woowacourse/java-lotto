package domain.lotto;

import java.util.ArrayList;
import java.util.List;
import utils.NumsGenerator;

public class LottoFactory {
    public static WinningLotto createWinNums(final List<Integer> rawLottoNumbers, final int bonusNumber) {
        LottoNumber bonus = LottoNumber.getInstance(bonusNumber);
        return WinningLotto.of(Lotto.from(NumsGenerator.generate(rawLottoNumbers)), bonus);
    }

    public static List<Lotto> createLottos(final LottoTicketCount count, final List<List<Integer>> rawLottoNumsGroup) {
        final List<Lotto> lottos = new ArrayList<>(createManualLottos(rawLottoNumsGroup));
        lottos.addAll(createAutoLottos(count.ofAuto()));
        return lottos;
    }

    private static List<Lotto> createManualLottos(final List<List<Integer>> rawLottoNumsGroup) {
        final List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> nums : rawLottoNumsGroup) {
            lottos.add(Lotto.from(NumsGenerator.generate(nums)));
        }
        return lottos;
    }

    private static List<Lotto> createAutoLottos(final int count) {
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.from(NumsGenerator.generateByRandom()));
        }
        return lottos;
    }
}