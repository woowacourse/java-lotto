package domain.lotto;

import domain.lotto.generator.NumberGenerator;
import domain.lotto.lottoresult.LottoResult;
import domain.lotto.lottoresult.LottoWinner;
import domain.lotto.lottoresult.ResultCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoGame {
    private static final int COUNT_NUMBER = 1;
    private static final String ERROR_NULL_MESSAGE = "1부터 45 사이의 숫자만 입력 가능합니다.";

    List<LottoNumbers> lottoGame;

    public LottoGame(List<LottoNumbers> lottoGame) {
        validateNullValue(lottoGame);
        this.lottoGame = lottoGame;
    }

    private void validateNullValue(List<LottoNumbers> lottoGame) {
        if (Objects.isNull(lottoGame)) {
            throw new IllegalArgumentException(ERROR_NULL_MESSAGE);
        }
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
