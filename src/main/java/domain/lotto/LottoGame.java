package domain.lotto;

import domain.lotto.generator.NumberGenerator;
import domain.lotto.lottoresult.LottoResult;
import domain.lotto.lottoresult.LottoWinner;
import domain.lotto.lottoresult.ResultCount;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGame {
    private static final int COUNT_NUMBER = 1;

    List<LottoNumbers> lottoGame;

    public LottoGame(List<LottoNumbers> lottoGame) {
        this.lottoGame = lottoGame;
    }

    public LottoResult createGameResult(LottoWinner winner) {
        return new LottoResult(lottoGame.stream()
                .collect(Collectors.toMap(winner::createRank, count -> new ResultCount(COUNT_NUMBER), ResultCount::sum)));
    }

    public static LottoGame create(NumberGenerator numberGenerator, int repeat) {
        List<LottoNumbers> lotto = new ArrayList<>();
        for (int i = 0; i < repeat; i++) {
            lotto.add(LottoNumbersFactory.createLottoNumbers(numberGenerator));
        }
        return new LottoGame(lotto);
    }

    public List<LottoNumbers> getLottoGame() {
        return lottoGame;
    }
}
