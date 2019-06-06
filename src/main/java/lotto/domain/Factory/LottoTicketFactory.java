package lotto.domain.Factory;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.exception.UnmatchedLottoTicketAmountException;

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
        private String input;

        public CustomLottoFactory(String input) {
            this.input = input;
        }

        @Override
        public LottoTicket makeLotto() {
            List<LottoNumber> lottoNumbers = new ArrayList<>();
            List<String> inputNumbers = Arrays.asList(input.split(","));

            // TODO 이부분 WinningLotto와 겹치는데 하나로 묶어버리기
            for (String inputNumber : inputNumbers) {
                validateNumeric(inputNumber);
                lottoNumbers.add(LottoNumber.getNumber(Integer.parseInt(inputNumber)));
            }
            validateDistinctNumber(lottoNumbers);

            return new LottoTicket(lottoNumbers);
        }

        private void validateNumeric(String number) {
            if (!number.matches("(\\d+)?")) {
                throw new ArithmeticException("로또 번호가 유효하지 않습니다.");
            }
        }

        private void validateDistinctNumber(List<LottoNumber> lottoNumbers) {
            boolean isDistinct = lottoNumbers.stream().distinct().collect(Collectors.toList()).size() != lottoNumbers.size();
            if (isDistinct) {
                throw new UnmatchedLottoTicketAmountException("중복된 번호는 입력할 수 없습니다.");
            }
        }
    }
}
