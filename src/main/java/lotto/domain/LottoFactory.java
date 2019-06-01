package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {
    public static Lotto createLotto(List<String> winningNumberInput) {
        List<LottoNumber> lottoNumbers = LottoNumber.getLotto(winningNumberInput.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));

        return new Lotto(lottoNumbers);
    }

    public static Lottoes createLottoes(String[] customLottoNumbersInput,Money money) {
        List<Lotto> lottoes = Arrays.stream(customLottoNumbersInput)
                .map(s -> {
                    return Arrays.stream(s.split(","))
                            .collect(Collectors.toList());
                })
                .map(s -> createLotto(s))
                .collect(Collectors.toList());

        for (int i = 0; i < money.getAutoLottoCount(customLottoNumbersInput); i++) {
            lottoes.add(createAutoLotto());
        }
        return new Lottoes(lottoes);
    }

    private static Lotto createAutoLotto() {
        List<LottoNumber> autoLottoNumbers = LottoNumber.getAutoLottoNumbers();
        return new Lotto(autoLottoNumbers);
    }

    public static WinningLotto createWinningLotto(Lotto lotto, int bonusBall) {
        return new WinningLotto(lotto, bonusBall);
    }
}
