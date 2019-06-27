package lotto.service;

import lotto.domain.exceptions.ExceptionMessages;
import lotto.domain.exceptions.LottoTicketException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserLottoTranslator {
    private static final String DELIMITER = ",";
    private final int lottoMoney;
    private final int manualCount;
    private final List<List<Integer>> manualNumbers;

    public UserLottoTranslator(String lottoMoney, String manualCount, List<String> manualNumbers) {
        this.lottoMoney = toInt(lottoMoney);
        this.manualCount = toInt(manualCount);
        this.manualNumbers = manualLottoNumber(this.manualCount, manualNumbers);
    }

    private int toInt(String numberString) {
        return Integer.parseInt(numberString);
    }

    private List<Integer> manualLottoNumber(String number) {
        try {
            return Arrays.stream(number.split(DELIMITER)).map(Integer::parseInt).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            return new ArrayList<>();
        }
    }

    private List<List<Integer>> manualLottoNumber(int manualCount, List<String> numbers) {
        validate(manualCount, numbers);
        List<List<Integer>> lottoNumber = new ArrayList<>();
        for (String number : numbers) {
            lottoNumber.add(manualLottoNumber(number));
        }
        return lottoNumber;
    }

    private void validate(int manualCount, List<String> manualNumbers) {
        if (manualCount == 0) {
            return;
        }
        if (manualCount != manualNumbers.size()) {
            throw new LottoTicketException(ExceptionMessages.TICKET.message());
        }
    }

    public int getLottoMoney() {
        return lottoMoney;
    }

    public int getManualCount() {
        return manualCount;
    }

    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }
}
