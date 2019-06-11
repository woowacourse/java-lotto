package lotto.domain.lotto;

import lotto.domain.creator.AutoLottoCreator;
import lotto.domain.creator.ManualLottoCreator;
import lotto.domain.util.CustomStringUtils;
import lotto.exception.InvalidLottoNumbersException;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {
    public static List<Lotto> createAutoLottos(int lottoQuantity) {
        List<Lotto> lottos = new ArrayList<>();
        AutoLottoCreator creator = new AutoLottoCreator();

        for (int i = 0; i < lottoQuantity; i++) {
            lottos.add(creator.createLotto());
        }
        return lottos;
    }

    public static List<Lotto> createManualLottos(int lottoQuantity, List<String> inputs) {
        List<Lotto> lottos = new ArrayList<>();

        for (String input : inputs) {
            ManualLottoCreator creator = new ManualLottoCreator(CustomStringUtils.parseInts(input));
            lottos.add(creator.createLotto());
        }
        checkNumberOfInputLotto(lottos, lottoQuantity);
        return lottos;
    }

    private static void checkNumberOfInputLotto(List<Lotto> lottos, int lottoQuantity) {
        if (lottos.size() != lottoQuantity) {
            throw new InvalidLottoNumbersException("입력하신 수동 로또 수와 입력하신 번호 셋트의 수가 일치하지 않습니다.");
        }
    }
}
