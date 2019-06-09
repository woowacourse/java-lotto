package lotto.domain.Factory;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.exception.DuplicatedInputException;
import lotto.exception.ExceptionMessage;
import lotto.exception.UnexpectedInputRangeException;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicketFactory {
    private static LottoTicketFactory INSTANCE;

    private LottoTicketFactory() { }

    public static LottoTicketFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LottoTicketFactory();
        }

        return INSTANCE;
    }

    public LottoTicket create(String input) {
        if (input == null) {
            return new AutoLottoFactory().makeLotto();
        }

        return new CustomLottoFactory(input).makeLotto();
    }

    private class AutoLottoFactory implements LottoTicketFactoryStrategy {
        @Override
        public LottoTicket makeLotto() {
            return new LottoTicket(LottoNumber.getRandomNumbers());
        }
    }

    private class CustomLottoFactory implements LottoTicketFactoryStrategy {
        private static final String NUMERIC_BUT_OUT_OF_BOUNDS = "0";

        private final String input;

        CustomLottoFactory(final String input) {
            this.input = input;
        }

        @Override
        public LottoTicket makeLotto() {
            List<LottoNumber> lottoNumbers = new ArrayList<>();
            String[] inputNumbers = input.split(",");

            for (String inputNumber : inputNumbers) {
                validateNumeric(inputNumber);
                lottoNumbers.add(LottoNumber.getInstance(Integer.parseInt(inputNumber)));
            }
            validateDistinctNumber(lottoNumbers);

            return new LottoTicket(lottoNumbers);
        }

        private void validateNumeric(String number) {
            if (!number.matches("(\\d+)?")) {
                throw new ArithmeticException(ExceptionMessage.ILLEGAL_LOTTO_NUMBER_EXCEPTION);
            }
            if (number.equals(NUMERIC_BUT_OUT_OF_BOUNDS)) {
                throw new UnexpectedInputRangeException(ExceptionMessage.ILLEGAL_LOTTO_NUMBER_EXCEPTION);
            }
        }

        private void validateDistinctNumber(List<LottoNumber> lottoNumbers) {
            boolean isDistinct = lottoNumbers.stream().distinct().collect(Collectors.toList()).size() != lottoNumbers.size();
            if (isDistinct) {
                throw new DuplicatedInputException(ExceptionMessage.DUPLICATED_NUMBER_EXCEPTION);
            }
        }
    }
}
