package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ManualLotto {

    private final List<Lotto> manualLotto;

    private ManualLotto(List<Lotto> manualLotto) {
        this.manualLotto = manualLotto;
    }

    public static ManualLotto createManualLotto(List<String> manualPurchaseNumbers) {
        List<Lotto> manualLotto = new ArrayList<>();

        for (String input : manualPurchaseNumbers) {
            validateInputCheck(input);
            List<Integer> lottoNumbers = Arrays.stream(input.split(", "))
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
            manualLotto.add(new Lotto(lottoNumbers));
        }
        return new ManualLotto(manualLotto);
    }

    private static void validateInputCheck(String input) {
        if (!Arrays.stream(input.split(", "))
                .allMatch(s -> s.chars().allMatch(Character::isDigit))) {
            throw new IllegalArgumentException("숫자와 , 를 이용하여 입력해주세요.");
        }
    }

    public List<Lotto> getManualLotto() {
        return manualLotto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManualLotto)) return false;

        ManualLotto that = (ManualLotto) o;

        return Objects.equals(manualLotto, that.manualLotto);
    }

    @Override
    public int hashCode() {
        return manualLotto != null ? manualLotto.hashCode() : 0;
    }
}
