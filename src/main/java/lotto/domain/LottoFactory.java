package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoFactory {

    public static Lottoes generateLottoes(int lottoCount){
        List<Lotto> lottoes = new ArrayList<>();
        for(int i=0; i<lottoCount ; i++){
            lottoes.add(generateAutoLotto());
        }
        return new Lottoes(lottoes);
    }

    private static Lotto generateAutoLotto(){
        List<LottoNumber> autoLottoNumbers = LottoNumber.getAutoLottoNumbers();
        return new Lotto(autoLottoNumbers);
    }
}
