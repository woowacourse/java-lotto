package lotto.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.exception.LottoException;
import lotto.exception.LottoExceptionStatus;
import lotto.view.input.reader.Reader;
import lotto.view.utils.Delimiter;

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
        final String inputLine = reader.readLine();
        return parseNumber(inputLine, LottoExceptionStatus.MONEY_MUST_BE_NUMERIC);
    }

    public int requestTicketCount() {
        final String inputLine = reader.readLine();
        return parseNumber(inputLine, LottoExceptionStatus.TICKET_COUNT_MUST_BE_NUMERIC);
    }

    public List<Integer> requestTicketNumbers() {
        final String inputLine = reader.readLine();
        final List<String> inputValues = Delimiter.COMMA.splitWith(inputLine);
        return inputValues.stream()
                .map(String::trim)
                .map(text -> parseNumber(text, LottoExceptionStatus.BALL_NUMBER_MUST_BE_NUMERIC))
                .collect(Collectors.toUnmodifiableList());
    }

    public int requestBonusNumber() {
        final String inputLine = reader.readLine();
        return parseNumber(inputLine, LottoExceptionStatus.BALL_NUMBER_MUST_BE_NUMERIC);
    }

}
