# java-lotto

로또 미션 저장소

## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)


## 로또 기능 요구사항
- [x] 구입 금액을 입력받을 수 있다.
- [X] 수동으로 구입할 로또 갯수를 입력한다.
- [X] 수동 구입 로또 번호를 수동 구매 갯수만큼 입력한다.
- [X] 구입한 수동 갯수와 자동 갯수를 출력한다.
- [x] 자동 구입한 로또 갯수 만큼 로또 번호 6개를 발급 후 출력한다.
  - [x] 한 로또의 번호 출력은 오름차순이다.
- [x] 당첨 번호 6개와 보너스 번호 1개를 입력받을 수 있다.
- [x] 발급된 로또의 순위를 파악한다.
  - [X] 1등 : 숫자 6개 일치
  - [x] 2등 : 숫자 5개 + 보너스번호
  - [x] 3등 : 숫자 5개
  - [x] 4등 : 숫자 4개
  - [x] 5등 : 숫자 3개
  - [x] 꽝 : 숫자 2개 ~ 숫자 0개
- [x] 전체 발급된 로또의 통계를 출력한다.
  - [x] 모든 당첨 로또의 상금 합 / 구입 금액 이 수익률이다.
- [x] 예외 발생시 재입력 여부를 묻고 재입력할 경우 다시 입력받는다.
  - [x] 재입력 여부는 Y 혹은 y를 입력시 재입력 의사가 있다고 판단한다.
  - [x] N 혹은 n 입력시 재입력 의사 없다고 판단하고 종료한다.
  - [x] 그 외 입력은 다시 재입력 여부를 묻는다.

## 검증 및 예외 사항
- [x] 입력금액 검증
  - [x] 입력금액은 양수여야 한다.
  - [x] 입력금액은 1000의 배수여야 한다.
- [X] 수동 횟수 검증
  - [X] 입력금액 / 1000 보다 클 수 없다
  - [X] 음수가 될 수 없다. (0가능)
- [x] 로또 번호 검증
  - [x] 로또 번호가 6개가 아니면 예외를 던진다.
  - [x] 로또 번호들은 `,`로 구분해서 입력한다.
  - [x] 로또 번호 앞 뒤 공백은 제거한다.
  - [x] 각 로또 번호는 반드시 숫자여야 한다.
  - [x] 빈 문자열이거나 null 일 수 없다.
  - [x] 로또 번호는 1부터 45까지이다.
  - [x] 각 로또 번호들은 중복되지 않는다.
- [x] 당첨 번호 검증
  - [x] 당첨 로또 번호와 보너스번호 모두 중복되는 번호는 없다.

## 객체 키워드
- 구입 금액
- 로또
- 개별 로또 번호
- 당첨 번호 + 보너스 번호
- 입력 parser -> `, `로 구분
- 랭크(열거형)
- 통계 내는 역할하는 애
- 통계 데이터를 전달하는 dto (미구현)
- 로또 기계 -> 전체적인 흐름제어


## 시간되면 한번 고민
- parser가 뷰에 속한다고 판단하여 배치했는데, 도매인 객체 생성도 하고 있다. 이게 맞는건가?
- 컨트롤러 역할을 Application이 하고 있다. 따로 여러개의 컨트롤러를 분리해야 될까?
- Parser는 템플릿 메서드 패턴을 통해 보너스 번호, 로또 번호, 입금액을 파싱하고 있다. 코드 재활용은 좋아졌는데 각 클래스를 읽으면 이해가 되지 않는다.
- 예외 재입력 과정에서 지나치게 많은 매개변수를 활용하고 있는 것 아닌가 싶다.
- 예외가 에러 메시지, 즉 사용자에게 보여질 메시지를 관리하는 건 뷰에 관여하는 것 아닌가라는 생각이 들어서 따로 분리해서 하고자 했다.

## 구현하면서 고민됐었던 점들
- parser가 뷰에 속한다고 판단하여 배치했는데, 도매인 객체 생성도 하고 있어요! 이거 괜찮을까요?
- 컨트롤러 역할을 Application이 하고 있어요!. 따로 여러개의 컨트롤러를 분리해야 될까요??
- Parser는 템플릿 메서드 패턴을 통해 보너스 번호, 로또 번호, 입금액을 파싱하고 있어요! 코드 재활용은 좋아졌는데 각 클래스의 메서드들이 많이 생략되서 다른 사람이 보고 이해하기 어려워진 것 같아요!!
- 예외 재입력 과정(`InputView`)에서 지나치게 많은 매개변수를 활용하고 있는 것 아닌가 싶어요 😿 4개... (그리고 지나치게 복잡하게 구현된 것 아닌가 싶어요!)
- 예외가 에러 메시지, 즉 사용자에게 보여질 메시지를 관리하는 건 뷰에 관여하는 것 아닌가라는 생각이 들어서 `OutputView`에서 Map으로 에러메시지를 관리하는데 이건 어떤지 궁금해요!

## 질문하기
- 자동 로또 생성에서 로또 번호의 범위를 감추니, 테스트하기가 까다로워졌다. 이에 대한 생각은?
- 