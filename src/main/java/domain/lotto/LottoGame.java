package domain.lotto;

import domain.RepeatCount;
import domain.lotto.lottoresult.LottoResult;
import domain.lotto.lottoresult.LottoWinner;
import domain.lotto.lottoresult.ResultCount;
import generator.NumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoGame {
    private static final String ERROR_NULL_MESSAGE = "null값이 입력되었습니다.";

    private List<LottoNumbers> lottoGame;

    public LottoGame(List<LottoNumbers> lottoGame) {
        validateNullValue(lottoGame);
        this.lottoGame = lottoGame;
    }

    public static LottoGame create(NumberGenerator numberGenerator, RepeatCount repeatCount) {
        return repeatCount.toIntStream()
                .mapToObj(i -> LottoNumbersFactory.createLottoNumbers(numberGenerator))
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoGame::new));
    }

    public static LottoGame merge(LottoGame... lottoGames) {
        return Arrays.stream(lottoGames)
                .flatMap(game -> game.lottoGame.stream())
                .collect(Collectors.collectingAndThen(Collectors.toList(), LottoGame::new));
    }

    private void validateNullValue(List<LottoNumbers> lottoGame) {
        if (Objects.isNull(lottoGame)) {
            throw new IllegalArgumentException(ERROR_NULL_MESSAGE);
        }
    }

    public LottoResult createGameResult(LottoWinner winner) {
        return new LottoResult(lottoGame.stream()
                .collect(Collectors.groupingBy(winner::createRank,
                        Collectors.collectingAndThen(Collectors.counting(),
                                ResultCount::new))));
    }

    public List<LottoNumbers> getLottoGame() {
        return lottoGame;
    }
}
