# java-lotto

로또 미션 진행을 위한 저장소

1. 로또 구매
    - [x] 사용자 입력 : 구입 금액
        - 최대 구입 가능 금액은 100만원으로 한정
        - 발생 가능한 예외 사항
            - [x] null 입력된 경우
            - [x] 숫자가 아닌 경우
            - [x] 1000원 이하의 금액이 입력된 경우
            - [x] 100만원 초과의 금액이 입력된 경우
            - [x] 1000원 단위의 금액이 입력되지 않은 경우
    - [x] 구입한 로또 개수 출력
    - [x] 사용자가 구입한 로또 티켓 번호 생성
        - 로또 번호는 1 ~ 45
            - [x] 번호 오름차순 정렬
        - 발생 가능한 예외 사항
            - [x] 한 로또 티켓에 중복된 번호가 있는 경우
            - [x] 한 로또 티켓의 사이즈가 6보다 큰 경우
            - [x] 로또 번호 범위를 벗어나는 숫자가 생길 경우
    - [x] 사용자가 구입한 로또 티켓 번호 출력

2. 당첨 번호 및 보너스 입력
    - [x] 사용자 입력 : 지난 주 당첨 번호
    - [x] 사용자 입력 : 보너스 번호
        - 로또 번호는 1 ~ 45
            - [x] 번호 오름차순 정렬
        - 발생 가능한 예외 사항
            - [x] null 입력된 경우
            - [x] 숫자가 아닌 경우
            - [x] 로또 번호가 부족한 경우 (당첨 번호 6 개, 보너스 번호 1 개)
            - [x] 중복된 번호가 있는 경우
            - [x] 로또 번호 범위를 벗어난 숫자인 경우

3. 당첨 통계 및 총 수익률 출력
    - [x] 당첨 통계 title 출력
    - [x] 당첨 금액별 로또 개수 출력
        1. 보너스 번호를 제외한 일치 개수 계산
        2. 5 개 일치할 경우, 보너스 번호 일치 여부 확인
    - [x] 총 수익률 계산
    - [x] 총 수익률 출력
   
## 고민 사항 
- [x] enum 적용할 객체 정하기 (ex: 로또 숫자, 당첨 통계 부분)
- [x] 숫자를 나눌 delimiter(,) 와 trim() 처리
- [x] 사용자로부터 받은 번호가 정렬되지 않은 경우
- [x] null이 아닌 enter가 입력된 경우 예외처리
- [x] 로또 티켓 가격이 바뀔 경우
- [x] 가격 integer 최댓값 넘어가는 오버플로우 상황 고려
- [x] Prize 캐싱 여부 - 안하기로 결정
- [ ] LottoMoney value long으로 변경
- [ ] map을 이용한 for문 stream으로 변경 (WinningStatics)

## 1단계 피드백 후 리팩터링 목록

- [x] 사용하지 않는 코드 정리
- [x] 컨벤션 정리
- [x] 모든 validate 메서드 static 제거 후 생성자에서 실행
- [x] 의미 있는 값만 상수로 포장
- [x] 작은 도메인 부터 구조 정리
- [x] 적절한 객체에게 역할 분배하기
- [x] 로또 넘버 캐싱
- [x] 최대한의 테스트 넣기

## 2단계 기능목록
- [x] 수동으로 구매할 매수 입력
    - 발생 가능한 예외 상황
        - [x] 음수인 경우
        - [x] 구입 금액으로 구매할 수 없는 경우
    
- [x] 수동으로 구매할 번호 입력    
    - 발생 가능한 예외 사항
        - [x] 한 로또 티켓의 사이즈가 6보다 크거나 작은 경우 (InputVIew)
        - [x] 로또 번호 범위를 벗어나는 숫자가 생길 경우 (LottoNumber)
        - [x] 한 로또 티켓에 중복된 번호가 있는 경우 (LottoTicket)

## 2단계 리팩터링 목록
- [x] 구매 수량을 알려주는 메시지 변경
- [x] LottoNumber 캐시 Map -> List로 변경
- [x] 캐싱에 내부 static 클래스 사용해 보기
- [x] Controller의 printLottoQuantity 메서드 고민

## 2단계 피드백 후 리팩터링 목록
- [x] Prize에 추상메서드 이용 -> 다형성 이용 -> 함수형 인터페이스 사용 순으로 변경
- [x] 단순 stream.forEach() 하는 부분 향상된 for 문으로 변경 (WinningStatics)
- [x] 기존 LottoNumber를 0~45 인덱스를 가지는 캐시로 만들어져 0번은 더미로 사용 -> 더미를 삭제하고 가져올 때 value-1 인덱스를 가져오게 변경
- [x] LottoNumber의 정적 팩토리 메서드에 유효성 검증 코드가 있어, 캐싱할 때 그 값이 검증되지 않아 생성자에도 값 검증 코드를 넣음
- [x] stream 활용이 적절하지 않은 부분 일반 반복문으로 변경

