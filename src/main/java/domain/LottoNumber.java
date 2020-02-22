package domain;

import spark.utils.StringUtils;

public class LottoNumber {
    private int lottoNumber;

    public LottoNumber(String number) {
        if (StringUtils.isBlank(number)) {
            throw new IllegalArgumentException("잘못된 복권 번호를 입력하였습니다.");
        }
        int parseNumber;
        try {
            parseNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해 주십시오.");
        }
        if (!LottoNumberBundle.contains(parseNumber)) {
            throw new IllegalArgumentException("범위를 벗어나는 복권 번호를 입력하였습니다.");
        }
        this.lottoNumber = parseNumber;
    }
}
