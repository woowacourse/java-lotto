package lotto.domain.number;

import lotto.domain.result.GameResult;

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

    public List<GameResult> calculateGameResult(WinningNumbers winningNumbers) {
        List<GameResult> gameResultList = new ArrayList<>();
        for (int i = 0; i < allLottoNumbers.size(); i++) {
            LottoRound presentLottoRound = allLottoNumbers.get(i);
            int correctNumber = winningNumbers.calculateCollectNumberCount(presentLottoRound);
            boolean isCorrectBonusNumber = winningNumbers.hasBonusNumber(presentLottoRound);
            GameResult gameResult = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
            gameResultList.add(gameResult);
        }
        return gameResultList;
    }

    public List<LottoRound> getAllLottoNumbers() {
        return Collections.unmodifiableList(allLottoNumbers);
    }
}
