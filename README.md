# java-lotto
로또 미션 진행을 위한 저장소



## 요구사항

- 로또 구입 금액을 입력받는다.
- 금액에 해당하는 로또를 발급한다.
- 구입한 로또 수 만큼 번호를 생성한다.
- 당첨 번호와 보너스 번호를 입력받는다.
- 당첨 여부를 검사한다.
- 수익률을 계산한다.

## 구현단위

- 구입 금액을 검증하는 클래스
  - 생성자 테스트 (int형으로 입력)
  - 예외처리 테스트 (잔돈, 음수)
  - 반환값 테스트 (로또 몇 개를 구매할 수 있는지)
  - 수익률 계산 테스트(수익금을 전달해서 수익률 반환)
- 로또 번호를 담당하는 클래스
  - 생성 테스트
  - 예외처리 테스트(1~45 바깥 범위, 음수, 문자열)
  - Equals 테스트
- 로또 번호 세트를 담당하는 클래스
  - 생성 테스트(LottoNumber 리스트 파라미터로 전달)
  - 예외 테스트(중복된 번호, 개수 6개 확인)
  - 비교 테스트(같은 번호 개수를 반환)
  - 넘버 1개(보너스 넘버)가 들어왔을때 이 값을 가지는지 테스트
- 로또 번호 세트를 생성하는 팩토리 클래스
  - Create 메서드(인터페이스 바탕으로 테스트) 통한 생성
  - 인터페이스 목록은 다음과 같다 : 
    - 테스트용 인터페이스
    - 랜덤 생성 인터페이스
    - (예정) 수동 입력 인터페이스
- 결과값을 저장하는 Enum 클래스 생성
  - 결과를 전달하여 Enum을 반환받는 메서드 구현
- 당첨 번호 세트를 저장하는 클래스 생성
  - null 입력 테스트 수행
  - 보너스 볼의 중복 여부 테스트 수행
  - 당첨 결과를 Enum으로 반환
- 결과값 클래스 : 맵<enum, cnt> 를 통해 당첨자 정보를 저장해서 컨트롤러에 전달
  - 생성자 테스트
  - 예외 : null방어
  - Add 메서드 테스트
    - 내부에 ResultCount 객체를 보유하여 조작
  - 키값을 받아 밸류를 반환하는 메서드
- 뷰
  - 인풋뷰
  - 아웃풋뷰
- 컨트롤러(게임)
  - 금액, 리스트(로또넘버즈), 당첨번호, 결과저장객체
  - 순서
    - 팩토리에서 로또넘버즈를 랜덤으로 생성하여 가져온다.(몇개? 금액객체에서 반환)
    - 아웃풋뷰에 로또넘버즈를 전달하여 출력
    - 당첨번호 입력 : 임시로 넘버즈 만들어 저장
    - 보너스번호 입력 : 임시로 넘버 객체 만들어 저장
    - 위너 클래스 생성
    - 리스트를 순회하면서 위너 클래스와 각 넘버즈를 매칭시킴
    - 반환받은 랭크를 결과객체에 add해줌
    - 각 랭크에 대해 결과객체에서 개수를 받아, 아웃풋뷰에 전달

## 예외처리

