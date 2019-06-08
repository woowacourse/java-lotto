package lotto.domain.Factory;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.exception.DuplicatedInputException;
import lotto.exception.ExceptionMessage;
import lotto.exception.UnexpectedInputRangeException;

import java.util.*;
import java.util.stream.Collectors;

public class LottoTicketFactory {
    public LottoTicketFactory() { }

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
        private final String input;

        public CustomLottoFactory(final String input) {
            this.input = input;
        }

        @Override
        public LottoTicket makeLotto() {
            List<LottoNumber> lottoNumbers = new ArrayList<>();
            String[] inputNumbers = input.split(",");

            // TODO 이부분 WinningLotto와 겹치는데 하나로 묶어버리기
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
            if (number.equals("0")) {
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
