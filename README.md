# java-lotto

로또 미션 저장소

## 요구 기능

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.
- 수익률(%) = (당첨된 금액) / (전체 구매 금액) * 100

로또번호는 오름차순으로 정렬해야 한다.

## 예외 사항

- 구매 금액이 숫자가 아닌 경우, IllegalArgumentException을 throw한다.
- 1000원 단위가 아닌 경우, IllegalArgumentException을 throw한다.
- 당첨 번호 및 보너스 볼에 중복 번호를 입력할 경우, IllegalArgumentException을 throw한다.
