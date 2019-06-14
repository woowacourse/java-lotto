package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {
    public static Lotto createLotto(List<String> lottoNumberInput) {
        List<LottoNumber> lottoNumbers = LottoNumber.getLotto(lottoNumberInput.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));

        return new Lotto(lottoNumbers);
    }

    public static Lottoes createOnlyAutoLottoes(int count){
        List<Lotto> lottoes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoes.add(createAutoLotto());
        }
        return new Lottoes(lottoes);
    }

    public static Lottoes createLottoes(String[] customLottoNumbersInput, Money money) {
        System.out.println(customLottoNumbersInput.length);
        List<Lotto> lottoes = createCustomLottoes(customLottoNumbersInput);
        addAutoLottoes(customLottoNumbersInput, money, lottoes);
        return new Lottoes(lottoes);
    }

    private static List<Lotto> createCustomLottoes(String[] customLottoNumbersInput) {
        return Arrays.stream(customLottoNumbersInput)
                .map(s -> Arrays.asList(s.split(",")))
                .map(s -> createLotto(s))
                .collect(Collectors.toList());
    }

    private static void addAutoLottoes(String[] customLottoNumbersInput, Money money, List<Lotto> lottoes) {
        for (int i = 0; i < money.getAutoLottoCount(customLottoNumbersInput); i++) {
            lottoes.add(createAutoLotto());
        }
    }

    private static Lotto createAutoLotto() {
        List<LottoNumber> autoLottoNumbers = LottoNumber.getAutoLottoNumbers();
        return new Lotto(autoLottoNumbers);
    }

    public static WinningLotto createWinningLotto(Lotto lotto, int bonusBall) {
        return new WinningLotto(lotto, bonusBall);
    }
}
