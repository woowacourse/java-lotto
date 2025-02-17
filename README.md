# Java Lotto

로또 미션 저장소

## 기능 요구 사항

1. **구입 금액 입력**
    - 1000 단위가 아니면 예외 발생 및 재입력 요청
    - 구입 금액은 양수여야 함
    - 최대 범위는 int의 최대값(2,147,483,647)이다.

2. **로또 발행**
    - 입력받은 구입 금액만큼 로또 생성
    - 각 로또 번호는 1~45 사이의 값이며, 중복이 없어야 함
    - 로또들은 중복될 수 있음
    - 로또 번호는 오름차순으로 정렬됨

3. **당첨 번호 입력**
    - 당첨 번호 6개 입력
    - 번호들은 중복될 수 없음
    - 번호는 구분자 `","` 로 구분 (올바르지 않을 시 에러 발생 및 재입력 요청)
    - 번호가 로또 번호 범위에 벗어나면 에러 발생 및 재입력 요청
    - 공백이 포함된 경우 자동으로 제거하여 변환 처리

4. **보너스 볼 입력**
    - 보너스 볼은 로또 번호 범위 내의 값이어야 함
    - 보너스 볼은 당첨 번호와 중복될 수 없음
    - 단일 숫자 입력

5. **당첨 통계 계산 및 출력**
    - 당첨 금액은 `int` 최대 값을 초과할 수 있음
    - 일치 개수를 오름차순으로 정렬하여 출력
    - 수익률을 소수점 둘째 자리까지 출력 (반올림 적용)

---

## 도메인 설계

### **구입 금액 (`Money`)
- 구입 금액을 통해 구매 가능한 로또 개수를 반환
- 양수이며 1000 단위여야 함

#### 예외 처리
- 음수이거나 1000 단위가 아닐 경우 예외 발생

### **로또 번호 (`LottoNumber`)
- 1~45 범위의 값만 허용
- 동일한 값 비교 (`equals` 적용)
- 정렬을 위한 `Comparator` 사용 가능

#### 예외 처리
- 범위를 벗어난 값일 경우 예외 발생

### **로또 (`Lotto`)
- 6개의 `LottoNumber`를 포함하는 객체
- 번호는 오름차순 정렬하여 출력한다.

#### 예외 처리
- 중복된 번호가 포함될 경우 예외 발생
- 로또 번호는 6개가 아닐 경우 예외 발생

### **로또 묶음 (`LottoGroup`)
- 구입한 로또 개수만큼 로또를 생성 및 관리

### **당첨 번호 (`WinnerLotto`)
- 당첨 번호 (`WinnerNumbers`)
- 보너스 번호 (`BonusNumber`)
- 입력받은 당첨 번호를 구분자를 기준으로 나누어 저장
- 보너스 번호를 입력받아 저장
- 특정 로또와 비교하여 일치 개수를 반환
- 해당 로또가 보너스 번호를 포함하는지 확인

#### 예외 처리
- 번호가 중복될 경우 예외 발생
- 유효한 로또 번호가 아닐 경우 예외 발생

### **순위 (`Rank`)
- 일치 개수와 보너스 번호 유무를 기준으로 순위 결정
- 5개 번호가 일치할 경우 보너스 번호 포함 여부 추가 고려
- 그 외에는 단순히 일치 개수로만 판단

### **당첨 통계 (`Profit`)
- `Map`과 `Enum`을 활용하여 당첨 내역 관리
- 일치하지 않는 경우 0으로 표기
- 로또 번호와 당첨 번호가 일치하면 해당 개수를 증가시킴
- 총 당첨 금액을 계산
- 수익률을 계산하여 출력

### **랜덤 숫자 생성기 (`RandomNumberUtils`)
- 1~45 범위의 랜덤 숫자 생성

