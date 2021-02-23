package lotto.domain;

import java.util.Objects;

public class LottoNumber {
    
    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;
    
    private static final String ERROR_OUT_OF_BOUNDS = "1이상 45 이하의 숫자를 입력해주세요.";
    private static final String ERROR_NOT_INTEGER = "숫자가 아닙니다.";
    
    private final int lottoNum;
    
    private LottoNumber(int lottoNum) {
        this.lottoNum = lottoNum;
    }
    
    public static LottoNumber fromStringLottoNumber(String lottoNum) {
        lottoNum = lottoNum.trim();
        
        if (isInteger(lottoNum)) {
            return from(Integer.parseInt(lottoNum));
        }
        
        throw new IllegalArgumentException(ERROR_NOT_INTEGER);
    }
    
    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
    public static LottoNumber from(int lottoNum) {
        if (isOutOfBounds(lottoNum)) {
            throw new IllegalArgumentException(ERROR_OUT_OF_BOUNDS);
        }
        
        return new LottoNumber(lottoNum);
    }
    
    private static boolean isOutOfBounds(int lottoNum) {
        return lottoNum < MIN_LOTTO_NUM || lottoNum > MAX_LOTTO_NUM;
    }
    
    public int getLottoNum() {
        return lottoNum;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumber that = (LottoNumber) o;
        return lottoNum == that.lottoNum;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(lottoNum);
    }
}
