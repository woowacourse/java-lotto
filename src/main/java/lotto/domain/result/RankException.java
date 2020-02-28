package lotto.domain.result;

public class RankException extends IllegalArgumentException {

    private static String countOfMatchesUnderZeroOrOverSix = "당첨 번호 일치 수는 0이상 6이하로만 가능합니다.";

    RankException() {
        super(countOfMatchesUnderZeroOrOverSix);
    }
}
