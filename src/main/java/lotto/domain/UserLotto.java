package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class UserLotto extends Lotto{
    private static final int LOTTO_SIZE = 6;

    public UserLotto(List<LottoNumber> lottoNumbers) {
        super(lottoNumbers);
        if (!isValidSize(lottoNumbers)) {
            throw new InvalidLottoException("로또 수는 6개 이어야 합니다.");
        }
    }

    @Override
    boolean isValidSize(List<LottoNumber> scannedNumbers) {
        return scannedNumbers.size() == LOTTO_SIZE;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder=new StringBuilder("[");
        stringBuilder.append(this.lottoNumbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")));
        return stringBuilder.append("]").toString();
    }
}
