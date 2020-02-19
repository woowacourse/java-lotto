# java-lotto
로또 미션 진행을 위한 저장소

## 미션 개요

당 미션은 로또를 발급해주는 로또 시스템을 구현한다. 로또 시스템은 사용자가 구매를 위해 금액을 `제시`하면, `기준`에 따라 일정한 범위의 숫자 내에서 정해진 갯수의 숫자를 임의로 골라 로또를 `발급`한다. 이후 당첨번호와의 일치여부를 `확인`한다. 그 결과에 따라 당첨 금액을 `산출`한다. 마지막으로 수익률을 계산한다.

## 구현 상세

- `제시`는 콘솔을 통하여 수행한다.
- `기준`은 사용자가 제시한 금액과 로또 가격을 나눠 정수값을 추출하여, 발급할 로또 갯수를 정한다.
  - 로또 가격은 1,000원이다.
- `발급`은 1부터 45까지의 숫자 중 임의로 6개의 숫자를 선택하는 것을 말한다.
- 당첨번호와의 일치여부 `확인`은 발급된 번호와 지난 주 당첨번호 및 보너스 볼과의 일치하는 갯수를 알아내는 것을 말한다.
- 당첨 금액은 다음과 같은 규칙에 따라 `산출`된다.
  - 3개 일치 : 5,000원
  - 4개 일치 : 50,000원
  - 5개 일치, 보너스볼 불일치 : 1,500,000원
  - 5개 일치, 보너스볼 일치 : 30,000,000원
  - 6개 일치: 2,000,000,000원

## 기능 목록

1. 사용자에게 로또를 구매할 금액을 요청한다.
2. 사용자로부터 구매 금액을 입력받는다.
3. 기준에 따라 입력된 금액을 발급할 로또 개수로 변환한다.
4. 로또 개수를 출력한다.
5. 로또 개수만큼 로또를 발급한다.
   - 발급은 임의로 1부터 45까지의 숫자를 6개 생성하여 리스트로 만든다.
6. 발급된 로또를 출력한다.
7. 사용자에게 지난 주 당첨번호를 요청한다.
8. 사용자로부터 지난 주 당첨번호를 입력받는다.
9. 사용자에게 보너스 볼을 요청한다.
10. 사용자로부터 보너스 볼을 입력받는다.
11. 발급된 로또와 지난 주 당첨번호 및 보너스 볼을 비교하여, 당첨 금액 산출 규칙에 따라 당첨 금액을 산출한다.
12. 산출된 당첨 금액에 따른 로또 개수를 출력한다.
13. 수익률을 계산한다.
14. 수익률을 출력한다.
