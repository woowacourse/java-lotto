public class LottoNumberValidator {
    public void validate(int lottoNumber) {
        if (1 > lottoNumber || lottoNumber > 45) {
            throw new IllegalArgumentException("당첨 번호는 1 ~ 45사이의 숫자만 가능합니다.");
        }
    }
}
