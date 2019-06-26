package domain.lottonumber;

class IllegalLottoNumberException extends IllegalArgumentException {
    IllegalLottoNumberException() {
        super("로또 숫자는 " + LottoNumber.MIN_NUMBER + "이상, " + LottoNumber.MAX_NUMBER + "이하여야 합니다.");
    }
}

