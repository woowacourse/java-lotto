package dto;

import domain.LottoNumber;

public class LottoNumberDto {

    private final int number;

    public LottoNumberDto(final int number) {
        this.number = number;
    }

    public static LottoNumberDto from(final LottoNumber lottoNumber) {
        return new LottoNumberDto(lottoNumber.getNumber());
    }
    
    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final LottoNumberDto that = (LottoNumberDto) object;

        return number == that.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
