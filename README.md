# java-lotto
1. LottoNumber
    - 로또 번호 하나를 저장 (int)
    - 1 ~ 45의 사이의 정수
2. LottoNumbers
    - 6개의 로또 번호만 입력 가능
    - 중복 불가능
    - 다른 로또 번호들과 비교해서 일치 개수 반환
3. LottoNumbersGenerator
    - 1 ~ 45까지의 로또 번호를 미리 저장해 놓고 재사용
    - 받은 번호의 LottoNumber 반환
    - 받은 번호들로 이루어진 LottoNumbers 반환
    - Shuffle을 활용해서 List의 앞에서부터 6개의 랜덤한 로또 번호로 LottoNumbers를 만들어 반환
4. Lotto
    - LottoNumbers 저장
    - 받은 번호가 로또에 있는지 확인
    - 다른 로또와 비교해서 일치 개수를 반환(LottoNumbers에 메세지 전달)
5. Lottos
    - Lotto들의 리스트
    - LottoNumbers 입력받으면 해당 번호로 로또를 생성해 추가
    - 다른 Lottos와 결합가능(자동 + 수동)
6. LottoMachine
    - 받은 자동 로또의 개수 만큼 랜덤한 Lottos 반환
    - 받은 수동 로또 번호들로 Lottos 반환  
7. WinningInformation
    - 당첨 번호들을 저장
    - 보너스 번호를 저장
    - 받은 로또의 등수를 반환
8. LottoGame
    - Lottos와 WinningLotto 입력시 LottoResult 반환
9. Rank
    - 당첨 종류를 나타내는 enum
    - 일치 개수를 입력 받으면 해당하는 Rank를 반환
10. LottoResult
    - 당첨 결과 저장 (Map<WinningType, Integer>)
    - 수익률 계산
11. Money
    - 구입 금액 저장
    - 최대 구매가능 금액 계산
    - 최대 구매가능 금액과 최소 구매가능 금액 판단
12. PurchaseInformation
    - 수동 구매 개수와 자동 구매 개수를 저장
    - 구입 금액과 수동 구매 개수로 자동 구매 개수 계산

### Input
- 구입 금액
- 당첨 번호

### output
- 구입한 로또 개수 및 로또 번호들
- 당첨 통계
- 총 수익률
