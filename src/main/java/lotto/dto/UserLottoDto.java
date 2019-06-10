package lotto.dto;

import lotto.domain.Exceptions.LottoNumberException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserLottoDto {
    private final int buyMoney;
    private final int manualCount;
    private final List<List<Integer>> manualNumbers;

    public UserLottoDto(String lottoMoney, int manualCount, List<String> manualNumbers) {
        this.manualCount = manualCount;
        try {
            this.buyMoney = parseNumber(lottoMoney);
            this.manualNumbers = parseNumber(manualNumbers);
        } catch (NumberFormatException e) {
            throw new LottoNumberException();
        }
    }

    private List<List<Integer>> parseNumber(List<String> manualNumbers) {
        List<List<Integer>> numbers = new ArrayList<>();
        for (String manualNumber : manualNumbers) {
            numbers.add(
                    Arrays.stream(manualNumber.split(","))
                            .map(this::parseNumber)
                            .collect(Collectors.toList()));
        }
        return numbers;
    }

    private int parseNumber(String text) {
        return Integer.parseInt(text);
    }

    public int getBuyMoney() {
        return buyMoney;
    }

    public int getManualCount() {
        return manualCount;
    }

    public List<List<Integer>> getManualNumbers() {
        return manualNumbers;
    }
}
