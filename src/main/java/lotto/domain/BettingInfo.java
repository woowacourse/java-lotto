package lotto.domain;

import java.util.List;
import java.util.Objects;

public class BettingInfo {
    private static final String EMPTY_INPUT_MSG = "%s이(가) 입력되지 않았습니다.";
    private static final String INCORRECT_MANUAL_LOTTO_NO_SIZE = "수동 로또 번호 개수가 잘못되었습니다.";
    private static final String INCORRECT_MANUAL_LOTTO_NO_INDEX= "수동 로또 번호에 잘못된 접근입니다.";

    private final PaidPrice paidPrice;
    private final LottoSize autoLottoSize;
    private final LottoSize manualLottoSize;
    private final List<List<String>> manualLottoNumbers;

    public BettingInfo (PaidPrice paidPrice, LottoSize manualLottoSize, List<List<String>> manualLottoNumbers) {
        validateNotNull(paidPrice, manualLottoSize, manualLottoNumbers);
        validateManualLotto(manualLottoSize, manualLottoNumbers);

        this.paidPrice =paidPrice;
        this.manualLottoSize = manualLottoSize;
        this.manualLottoNumbers = manualLottoNumbers;
        this.autoLottoSize = createOtherTypeLottoSize(paidPrice, manualLottoSize);
    }

    private void validateNotNull(PaidPrice paidPrice, LottoSize manualLottoSize, List<List<String>> manualLottoNumbers) {
        Objects.requireNonNull(paidPrice, String.format(EMPTY_INPUT_MSG, "구입 금액"));
        Objects.requireNonNull(manualLottoSize, String.format(EMPTY_INPUT_MSG, "수동 로또 개수"));
        Objects.requireNonNull(manualLottoNumbers, String.format(EMPTY_INPUT_MSG, "수동 로또 번호"));

        LottoSize autoLottoSize = createOtherTypeLottoSize(paidPrice, manualLottoSize);
        Objects.requireNonNull(autoLottoSize, String.format(EMPTY_INPUT_MSG, "자동 로또 개수"));
    }

    // 자동 로또 개수와 자동 로또 번호의 개수가 일치하는 지
    private void validateManualLotto(LottoSize lottoSize, List<List<String>> manualLottoNumbers) {
        if (lottoSize.getLottoSize() != manualLottoNumbers.size()) {
            throw new IllegalArgumentException(INCORRECT_MANUAL_LOTTO_NO_SIZE);
        }
    }

    private LottoSize createOtherTypeLottoSize(PaidPrice paidPrice, LottoSize lottoSize) {
        int otherTypeLottoSize = paidPrice.getTotalLottoSize() - lottoSize.getLottoSize();
        return new LottoSize(paidPrice, otherTypeLottoSize);
    }

    public int getManualLottoSize() {
        return this.manualLottoSize.getLottoSize();
    }

    public int getAutoLottoSize() {
        return this.autoLottoSize.getLottoSize();
    }

    public List<String> getManualLottoNumbers(int index) {
        if (index < 0 || index >= this.manualLottoNumbers.size()) {
            throw new IllegalArgumentException(INCORRECT_MANUAL_LOTTO_NO_INDEX);
        }
        return this.manualLottoNumbers.get(index);
    }
}
