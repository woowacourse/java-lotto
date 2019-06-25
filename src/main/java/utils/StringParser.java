package utils;

import domain.money.PurchaseAmount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StringParser {
    public static int parsePurchaseAmount(String moneyFromPlayer) {
        return Optional.ofNullable(moneyFromPlayer)
                .map(Integer::parseInt)
                .orElse(PurchaseAmount.MINIMUM_AMOUNT);
    }

    public static List<Integer> parseNumbers(String lottoNumbers) {
        List<String> splitedNmbers = Optional.ofNullable(lottoNumbers)
                .map(numbers -> Arrays.asList(numbers.split(",\\s*")))
                .orElse(new ArrayList<>());

        return splitedNmbers.stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int parseInt(String inputNumber) {
        return Optional.ofNullable(inputNumber)
                .map(Integer::parseInt)
                .orElse(0);
    }
}
