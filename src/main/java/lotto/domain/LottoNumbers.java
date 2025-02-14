package lotto.domain;

import static lotto.common.Constants.LOTTO_NUM_SIZE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LottoNumbers {

    private final List<LottoNumber> item;

    public LottoNumbers(List<LottoNumber> item) {
        validLottoNumbers(item);
        this.item = sorted(item);
    }

    public static LottoNumbers from(List<LottoNumber> lottoNumbers) {
        return new LottoNumbers(lottoNumbers);
    }


    public static LottoNumbers from(String input) {
        if (input == null || input.isBlank() || input.endsWith(", ")) {
            throw new IllegalArgumentException("잘못된 입력입니다. 이와 같은 형태로 작성해주세요.(ex. 1, 2, 3, 4, 5, 6)");
        }

        if (!input.contains(",")) {
            throw new IllegalArgumentException("구분자(,)를 활용해주세요! (ex. 1, 2, 3, 4, 5, 6)");
        }

        return new LottoNumbers(Arrays.stream(input.split(","))
                .map(LottoNumber::new)
                .toList());
    }

    public boolean contains(LottoNumber lottoNumber) {
        return item.contains(lottoNumber);
    }

    public long getMatchCount(LottoNumbers winnerNumbers) {
        return item.stream().filter(winnerNumbers::contains).count();
    }


    private void validLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUM_SIZE) {
            throw new IllegalArgumentException("로또 번호 6개를 입력해주세요.");
        }

        if (new HashSet<>(lottoNumbers).size() != LOTTO_NUM_SIZE) {
            throw new IllegalStateException("중복은 불가능합니다.");
        }
    }

    private List<LottoNumber> sorted(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .sorted()
                .toList();
    }

    public List<LottoNumber> getItem() {
        return item;
    }
}
