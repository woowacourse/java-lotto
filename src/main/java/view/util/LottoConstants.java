package view.util;

public interface LottoConstants {
    int LOTTO_PRICE_PER_ONE = 1000;
    int START_NUMBER_OF_LOTTO_RANGE = 1;
    int END_NUMBER_OF_LOTTO_RANGE = 45;
    int BALL_NUMBER_OF_ONE_LOTTO = 6;

    String NUMERIC_VALIDATE_REGEX = "[+-]?\\d*(\\.\\d+)?";
    String DELIMITER_VALIDATE_REGEX = "([0-9]+)(, [0-9]+)+";
    String COMMA_REGEX = ", ";

}
