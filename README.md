# java-lotto

## 기능 요구사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

## 커스텀 요구사항
- 1인당 구매 가능한 금액은 최대 10만원이다.

## 기능 목록
- [x] 구입 금액을 입력할 수 있다.
- [x] 구입 금액에 해당하는 개수의 로또를 발행할 수 있다.
- [x] 로또는 1 ~ 45 사이 6개 숫자를 랜덤으로 생성하여 발행한다.
- [x] 발행된 로또를 출력한다.
- [ ] 당첨 번호를 입력받는다.
- [ ] 보너스 번호를 입력받는다.
- [ ] 당첨 금액 별 당첨 개수를 계산한다.
- [ ] 당첨 수익률을 계산한다.
- [ ] 당첨 통계를 출력한다

## 예외
- [x] 구입 금액이 숫자가 아니면 IllegalArgumentException을 던진다.
- [x] 구입 금액이 1000원으로 나누어지지 않으면 IllegalArgumentException을 던진다.
- [x] 구입 금액이 양수가 아니라면 IllegalArgumentException을 던진다.
- [ ] 당첨 번호와 보너스 번호가 중복되면 IllegalArgumentException을 던진다.
