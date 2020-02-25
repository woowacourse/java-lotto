package lotto;

import lotto.domain.CollectionLottoNumberShuffler;
import lotto.domain.LottoGame;
import lotto.domain.LottoNumberShuffler;
import lotto.domain.LottosGenerator;

public class LottoApplication {
    public static void main(String[] args) {
        LottoNumberShuffler lottoNumberShuffler = new CollectionLottoNumberShuffler();
        LottosGenerator lottosGenerator = new LottosGenerator(lottoNumberShuffler);
        LottoGame lottoGame = new LottoGame(lottosGenerator);
        try {
            lottoGame.play();
        } catch (RuntimeException e) {
            System.out.println(String.format("다음과 같은 이유로 게임을 종료합니다. - %s", e.getMessage()));
            System.exit(-1);
        }
    }
}
