package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {

    private List<LottoBall> lottoBalls;

    public Lotto(List<Integer> numbers) {
        lottoBalls = new ArrayList<>();
        numbers.forEach(number -> lottoBalls.add(new LottoBall(number)));
    }

    public boolean hasBall(LottoBall lottoBall) {
        return lottoBalls.contains(lottoBall);
    }

    public int matchedEachOther(Lotto lotto) {
        return lottoBalls.stream()
                .filter(lotto::hasBall)
                .collect(Collectors.toList())
                .size();
    }

    public List<LottoBall> getLottoNumbers() {
        return lottoBalls;
    }

}
