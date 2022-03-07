package domain.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

    private static final int LOTTO_SIZE = 6;

    private static Lotto generateAutoLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(lottoNumbers);

        List<LottoNumber> completedLottoNumbers = lottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted(Comparator.comparing(LottoNumber::getNumber))
                .collect(Collectors.toList());

        return new Lotto(completedLottoNumbers);
    }

    private static Lotto generateManualLotto(List<Integer> numbers){
        List<LottoNumber> lottoNumbers = numbers.stream()
                .sorted()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }

    public static List<Lotto> generateAutoLottos(int autoLottoCount) {
        List<Lotto> autoLottos = new ArrayList<>();
        for (int i = 0; i < autoLottoCount; i++) {
            autoLottos.add(generateAutoLotto());
        }
        return autoLottos;
    }

    public static List<Lotto> generateManualLottos(List<List<Integer>> manualLottoNumbers) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (List<Integer> manualLottoNumber : manualLottoNumbers) {
            manualLottos.add(generateManualLotto(manualLottoNumber));
        }
        return manualLottos;
    }
}
