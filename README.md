# java-lotto

- 로또 미션 저장소

---

## 추가한 기능 요구 사항

- 구입 금액은 정수형 범위로 제한한다.
- 잘못된 입력을 한 경우 재입력을 받는다.
- 잘못된 입력값에 대해 IllegalArgumentException 예외를 오류 메시지와 함께 발생시킨다.

---

# 구현할 기능 목록

## 🗂️ Domain

### Lotto

- [x] 로또 번호를 저장한다.
- [x] 로또 번호의 유효성을 검증한다.
    - [x] [예외] 로또 번호의 개수가 6개가 아니면 예외를 발생시킨다.
    - [x] [예외] 로또 번호가 1-45의 범위가 아닌 경우, 예외를 발생시킨다.

### LottoMoney

- [x] 로또 구입 금액을 저장한다.
- [x] 로또 구입 금액의 유효성을 검증한다.
    - [x] [예외] 로또 구입 금액이 1000원 미만인 경우, 예외를 발생시킨다.
    - [x] [예외] 로또 구입 금액이 1000원 단위가 아닌 경우, 예외를 발생시킨다.

### LottoMachine

- [x] 로또 티켓을 생성하고 저장한다.

### WinningLotto

- [x] 당첨 로또의 당첨 번호와 보너스 번호를 저장한다.
- [x] 보너스 번호의 유효성을 검증한다.
    - [x] [예외] 보너스 번호가 1-45의 범위가 아닌 경우, 예외를 발생시킨다.
    - [x] [예외] 보너스 번호가 당첨 번호 6개와 중복되는 경우, 예외를 발생시킨다.

### LottoPrize

- [x] 로또 티켓이 당첨 로또와 일치하는 개수에 따른 등수와 상금을 저장한다.

### LottoResult

- [x] 로또 티켓의 당첨 개수를 계산한다.
- [x] 로또 티켓의 당첨 개수에 따른 수익률을 계산한다.
    - [x] 로또 티켓의 당첨 개수에 따른 상금액을 계산한다.

---

## 📡 Service

### LottoService

- [x] 로또 번호를 입력받아 생성된 로또 객체를 반환한다.
- [x] 입력 값의 자료형에 대한 유효성을 검증한다.
    - [x] [예외] 로또 번호가 숫자가 아닌 경우, 예외를 발생시킨다.

### LottoMoneyService

- [x] 로또 구입 금액을 입력받아 생성된 로또 구입 금액 객체를 반환한다.
- [x] 입력 값의 자료형에 대한 유효성을 검증한다.
    - [x] [예외] 로또 구입 금액이 숫자가 아닌 경우, 예외를 발생시킨다.

### WinningLottoService

- [x] 당첨 로또 번호와 보너스 번호를 입력받아 생성된 당첨 로또 객체를 반환한다.
- [x] 입력 값의 자료형에 대한 유효성을 검증한다.
    - [x] [예외] 보너스 번호가 숫자가 아닌 경우, 예외를 발생시킨다.

---

## 👀 View

### InputView

- [x] 구입 금액을 입력받는다.
- [x] 당첨 로또를 입력받는다.
    - [x] 당첨 번호를 입력받는다.
    - [x] 보너스 번호를 입력받는다.

### OutputView

- [x] 오류 메시지를 출력한다.
- [x] 구입한 로또 티켓을 출력한다.
- [x] 당첨 통계를 출력한다.
    - [x] 일치하는 당첨 개수를 출력한다.
    - [x] 총 수익률을 출력한다.

---

## 🕹️ Controller

### GameController

- [x] 로또 게임의 흐름을 제어한다.
    - [x] 구입 금액을 입력 받도록 한다.
    - [x] 로또 티켓을 구입한다.
    - [x] 구입한 로또 티켓을 출력하도록 한다.
    - [x] 당첨 로또를 입력 받도록 한다.
        - [x] 당첨 번호를 입력 받도록 한다.
        - [x] 보너스 번호를 입력 받도록 한다.
    - [x] 로또 티켓의 당첨 결과를 계산하도록 한다.
        - [x] 로또 티켓의 당첨 개수를 계산하도록 한다.
        - [x] 로또 티켓의 수익률을 계산하도록 한다.
    - [x] 당첨 통계를 출력하도록 한다.

---

## 🛠 Util

### Constant

- [x] 로또 관련 상수를 저장한다.

### ExceptionHandler

- [x] 잘못된 입력 시 예외를 오류 메시지와 함께 발생시킨다.

### ObjectCreator

- [x] 입력 값을 바탕으로 객체를 생성한다.

### RandomGenerator

- [x] 랜덤한 로또 번호를 생성한다.

### StringConverter

- [x] 문자열을 숫자 리스트로 변환한다.
- [x] 문자열을 숫자로 변환한다.
