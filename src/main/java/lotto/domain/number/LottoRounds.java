package lotto.domain.number;

import lotto.domain.result.GameResult;
import lotto.domain.result.GameResults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoRounds {
    private static final String EMPTY_OR_NULL_INPUT_EXCEPTION_MESSAGE = "AllLootNumbers에 Null 혹은 빈 입력이 들어왔습니다.";
    private final List<LottoRound> allLottoNumbers;

    public LottoRounds(List<LottoRound> allLottoNumbers) {
        Objects.requireNonNull(allLottoNumbers);
        validateEmpty(allLottoNumbers);
        this.allLottoNumbers = allLottoNumbers;
    }

    private void validateEmpty(List<LottoRound> allLottoNumbers) {
        if (allLottoNumbers.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_OR_NULL_INPUT_EXCEPTION_MESSAGE);
        }
    }

    public GameResults calculateGameResult(WinningNumbers winningNumbers) {
        List<GameResult> gameResults = new ArrayList<>();
        for (LottoRound presentLottoRound : allLottoNumbers) {
            int correctNumber = winningNumbers.calculateCollectNumberCount(presentLottoRound);
            boolean isCorrectBonusNumber = winningNumbers.hasBonusNumber(presentLottoRound);
            GameResult gameResult = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
            gameResults.add(gameResult);
        }
        return new GameResults(gameResults);
    }

    public LottoRounds combineLottoRounds(LottoRounds anotherLottoRounds) {
        List<LottoRound> combinedLottoRounds = new ArrayList<>();
        combinedLottoRounds.addAll(allLottoNumbers);
        combinedLottoRounds.addAll(anotherLottoRounds.allLottoNumbers);
        return new LottoRounds(combinedLottoRounds);
    }

    public List<LottoRound> getAllLottoNumbers() {
        return Collections.unmodifiableList(allLottoNumbers);
    }
}
