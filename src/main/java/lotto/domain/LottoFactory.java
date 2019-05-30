package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

    public static Lottoes createAutoLottoes(int lottoCount){
        List<Lotto> lottoes = new ArrayList<>();
        for(int i=0; i<lottoCount ; i++){
            lottoes.add(createAutoLotto());
        }
        return new Lottoes(lottoes);
    }

    private static Lotto createAutoLotto(){
        List<LottoNumber> autoLottoNumbers = LottoNumber.getAutoLottoNumbers();
        return new Lotto(autoLottoNumbers);
    }

    public static Lotto createLotto(List<String> winningNumberInput) {
        List<LottoNumber> lottoNumbers = LottoNumber.getLotto(winningNumberInput.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));

        return new Lotto(lottoNumbers);
    }

    public static WinningLotto createWinningLotto(Lotto lotto, int bonusBall) {
        return new WinningLotto(lotto,bonusBall);
    }
}
