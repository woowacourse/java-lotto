package domain;

import exception.LottoException;
import java.util.Objects;

 public class LottoNumber implements Comparable<LottoNumber> {
     private final int lottoNumber;
     private String INVALID_NUMBER_RANGE = "로또 숫자는 1부터 45까지 가능합니다.";
     private int LOTTO_NUMBER_END = 45;
     private int LOTTO_NUMBER_START = 1;

     public LottoNumber(int lottoNumber) {
        validateLottoNumber(lottoNumber);
        this.lottoNumber = lottoNumber;
    }

     private void validateLottoNumber(int lottoNumber) {
         if(lottoNumber > LOTTO_NUMBER_END || lottoNumber < LOTTO_NUMBER_START){
            throw new LottoException(INVALID_NUMBER_RANGE);
        }
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
        return lottoNumber == that.lottoNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumber);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return lottoNumber - o.lottoNumber;
    }

    @Override
    public String toString(){
        return lottoNumber + "";
    }
}
