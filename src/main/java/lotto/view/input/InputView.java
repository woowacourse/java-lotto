package lotto.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;
import lotto.exception.ball.BallNumberExceptionStatus;
import lotto.exception.money.MoneyExceptionStatus;
import lotto.utils.Delimiter;
import lotto.view.input.reader.Reader;

public class InputView {

    private final Reader reader;

    public InputView(Reader reader) {
        this.reader = reader;
    }

    private int parseNumber(final String text, LottoExceptionStatus lottoExceptionStatus) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            throw new LottoException(lottoExceptionStatus);
        }
    }

    public int requestMoney() {
        return parseMoney(reader.readLine());
    }

    private int parseMoney(final String inputValue) {
        return parseNumber(inputValue, MoneyExceptionStatus.MONEY_IS_NOT_NUMERIC);
    }

    public List<Integer> requestWinningNumbers() {
        final String inputValue = reader.readLine();
        return Arrays.stream(Delimiter.splitWithComma(appendSpaceBeforeSplit(inputValue)))
                .map(String::trim)
                .map(this::parseBallNumber)
                .collect(Collectors.toUnmodifiableList());
    }

    private String appendSpaceBeforeSplit(final String targetString) {
        return Delimiter.appendSpaceBehind(targetString);
    }

    private int parseBallNumber(final String inputValue) {
        return parseNumber(inputValue, BallNumberExceptionStatus.BALL_IS_NOT_NUMERIC);
    }

    public int requestBonusNumber() {
        return parseBallNumber(reader.readLine());
    }

}
