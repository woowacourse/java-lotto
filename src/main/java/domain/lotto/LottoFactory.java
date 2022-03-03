package domain.lotto;

import java.util.ArrayList;
import java.util.List;
import utils.NumsGenerator;

public class LottoFactory {
    public static WinNumbers createWinNums(final List<Integer> lottoNumbers, final int bonusNum) {
        LottoNumber bonus = LottoNumber.getInstance(bonusNum);
        return WinNumbers.of(Lotto.from(NumsGenerator.generate(lottoNumbers)), bonus);
    }

    public static List<Lotto> createLottos(final LottoTicketCount count, final List<List<Integer>> lottoNumsGroup) {
        final List<Lotto> lottos = new ArrayList<>(createManualLottos(lottoNumsGroup));
        lottos.addAll(createAutoLottos(count.ofAuto()));
        return lottos;
    }

    private static List<Lotto> createManualLottos(final List<List<Integer>> lottoNumsGroup) {
        final List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> nums : lottoNumsGroup) {
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