# CareBox

---
| 중장년층 및 건강에 관심 있는 일반 사용자에게 제공하는 개인 맞춤형 건강관리 & 영양제 복용 서비스

## 📚 목차
1. [기술 설명](#기술-설명)
2. [API 명세서](#API-명세서)
3. [트러블 슈팅](#트러블-슈팅)
3. [추가하고 싶은 기능](#추가하고-싶은-기능)
4. [팀원](#팀원)

## 기술 설명

---

### 📌 공통
- GitLab & GitHub
- REST API
- GPT, YouTube API?

### 💻 백엔드
- Java 17+
- Gradle
- Spring Boot
- Spring Security
- JPA
- MySQL

### 🌐 프론트엔드
- Vue 3
- Vite
- Vue Router
- Axios
- Pinia


### 🛠️ 실행 방법
```
# 백엔드
cd backend
./gradlew bootRun
```

```
# 프론트엔드
cd frontend
npm install
npm run dev
```

## API 명세서

---
### 1. Auth (/auth) : 인증 관련 API

| Method | URL     | Description | Request                                                                                                                                                                                             | Response                                                                       |
|--------|---------|-------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| Post   | /login  | 로그인         | `{ "email" : "...", "password" : "..." }`                                                                                                                                                           | `{ "user" : { "id" : .., "username" : "..", "email" : ".."}, "token" : ".." }` |
| Post   | /signup | 회원가입        | `{ "name" : "..", "password" : "..", "email" : "..", "birthDate" : "..", "gender" : "..", "nickname" : "..", "profileImage" : "..", "role" : "..", "licenseNumber" : "..", "hospitalName" : ".." }` | `{ }`                                                                          |

### 2. Community (/api/community/posts) : 게시글 관련 API

| 요청 헤더 : Authorization : Bearer {Token}

| Method | URL          | Des       | Request                                                              | Response                                                                                                                                         |
|--------|--------------|-----------|----------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| Post   | /            | 게시글 등록    | `{ "title" : "...", "content" : "..." }`                             | `{ "id" : .. }`                                                                                                                                  |
| Get    | ?sort=status | 모든 게시글 조회 | Query Param : `sort`                                                 | `{ "posts" : { "id" : .., "title" : "..", "content" : "..", "authName" : "..", "createdAt" : "..", "updatedAt" : "..", "viewCount" : .. }, .. }` |
| Get    | /{id}        | 특정 게시글 조회 | PathVariable : `{id}`                                                | `{ "id" : .., "title" : "..", "content" : "..", "authName" : "..", "createdAt" : "..", "updatedAt" : "..", "viewCount" : .. }`                   |
| Put    | /{id}        | 게시글 수정    | PathVariable : `{id}`, Body : `{ "title" : "..", "content" : ".." }` | `{ }`                                                                                                                                            |
| Delete | /{id}        | 게시글 삭제    | PathVariable : `<br/>`                                               | `{ }`                                                                                                                                            |

### 3. Comment (/api/community/posts/comments) : 댓글 관련 API

| 요청 헤더 : Authorization : Bearer {Token}

| Method | URL           | Des       | Request               | Response                                   |
|--------|---------------|-----------|-----------------------|--------------------------------------------|
| Post   | /{postId}     | 댓글 달기     | `{ "content" : ".."}` | `{ "id" : .. }`                            |
| Get    | /{postId}     | 댓글 리스트 얻기 | PathVariable : `{id}` | `{ "comments" : { "content" : "..", ..} }` |
| Put    | /{commentId}  | 댓글 수정     | PathVariable : `{id}` | `{ }`                                      |
| Delete | /{commentsId} | 댓글 삭제     | PathVariable : `{id}` | `{ }`                                      |

### 4. Calendar (/api/calendar) : 일정(달력) 관련 API

| 요청 헤더 : Authorization : Bearer {Token}

| Method | URL                      | 설명       | Request                                                                                          | Response                                                                                                                                                                                                                                                       |
|--------|--------------------------|----------|--------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| POST   | /intake                  | 복용 기록 저장 | `{ "supplementName" : "..", "amountTakenMg" : .., "localDate" : "..", "intakeTimeType" : ".." }` | `"message" : "복용 기록이 저장되었습니다."`                                                                                                                                                                                                                                |
| GET    | /summary?date=yyyy-MM-dd | 하루 요약 조회 | Query Param: `date`                                                                              | `{ "date": "..", "nutrientStatusList" : { { "nutirientName" : "..", "totalTakenMg" : .., "recommendedMg" : .., "message" : ".." }, {..} }, "SupplementIntakeRecordDto" : { { "supplementName" : "..", "amountTakenMg" : "..", "timeType" : ".." }, { .. } } }` |

### 5. Column (/api/columns) : 칼럼 관련 API

| 요청 헤더 : Authorization : Bearer {Token}

| Method | URL               | 설명           | Request                                                                           | Response                                                                                       |
|--------|-------------------|--------------|-----------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------|
| POST   | /                 | 칼럼 등록        | `{ "title" : "..", "content" : "..", "pharmacistId" : ".." }`                     | `{ }`                                                                                          |
| GET    | /{id}             | 칼럼 상세 조회     | PathVariable: `id`                                                                | `{ "id" : .., "title" : "..", "content" : "..", "createdAt" : "..", "pharmacistName" : ".." }` |
| GET    | /pharmacists/{id} | 약사별 칼럼 목록 조회 | PathVariable: `id`                                                                | `{ "columns" : { "title" : "..", "content" : "..", "pharmacistId" : ".." }, {..}`              |
| PUT    | /{id}             | 칼럼 수정        | PathVariable: `id`, `{ "title" : "..", "content" : "..", "pharmacistId" : ".." }` | `{ }`                                                                                          |
| DELETE | /{id}             | 칼럼 삭제        | PathVariable: `id`                                                                | `{ }`                                                                                          |
| GET    | /all              | 전체 칼럼 리스트 조회 | `{ }`                                                                             | `{ "columns" : { "title" : "..", "content" : "..", "pharmacistId" : ".." }, {..}`              |

### 6. Follow (/api/follow) : 팔로우 관련 API

| 요청 헤더 : Authorization : Bearer {Token}

| Method | URL             | 설명            | Request                      | Response                                                                                                      |
|--------|-----------------|---------------|------------------------------|---------------------------------------------------------------------------------------------------------------|
| POST   | /{pharmacistId} | 약사 팔로우 요청     | PathVariable: `pharmacistId` | `{ }`                                                                                                         |
| GET    | /my             | 내가 팔로우한 목록 조회 | 인증 필요                        | `{ "follows" : { "pharmacistId" : .., "name" : "..", "hospitalNamme" : "..", "profileImage" : ".." }, {..} }` |
| DELETE | /{pharmacistId} | 팔로우 취소        | PathVariable: `pharmacistId` | `{ }`                                                                                                         |

### 7. Notification (/api/notification) : 알람 관련 API

| 요청 헤더 : Authorization : Bearer {Token}

| Method | URL        | 설명       | Request               | Response                                                                                         |
|--------|------------|----------|-----------------------|--------------------------------------------------------------------------------------------------|
| POST   |            | 알림 등록    | `NotificationRequest` | `{ }`                                                                                            |
| GET    | /today     | 오늘 알림 조회 | `{ }`                 | `{ "notification" : { "id" : .., "message" : "..", "isRead" : .., "notifiedAt" : ".." }, {..} }` |
| GET    |            | 전체 알림 조회 | `{ }`                 | `NotificationListResponse`                                                                       |
| POST   | /read/{id} | 알림 읽음 처리 | PathVariable: `id`    | `{ }`                                                                                            |

### 8. Pharmacist (/api/pharmacists) : 약사 관련 API

| 요청 헤더 : Authorization : Bearer {Token}

| Method | URL | 설명          | Request | Response                                                             |
|--------|-----|-------------|---------|----------------------------------------------------------------------|
| GET    |     | 전체 약사 목록 조회 | 없음      | `{ "pharmacists" : { "name" : "..", "profileImage" : ".." }, {..} }` |

### 9. ProfileController (/users) : 프로필 관련 API

| 요청 헤더 : Authorization : Bearer {Token}

| Method | URL      | 설명       | Request                                                                                       | Response                                                                                                           |
|--------|----------|----------|-----------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|
| GET    | /profile | 내 프로필 조회 | `{ }`                                                                                         | `{ "name" : "..", "email" : "..", "birthDate" : "..", "gender" : "..", "nickNake" : "..", "profileImage" : ".." }` |
| PUT    | /profile | 내 프로필 수정 | `{ "name" : "..", "birthDate" : "..", "gender" : "..", "nickname" : "..", "profile" : ".." }` | `{ }`                                                                                                              |




## 트러블 슈팅

---



## 추가하고 싶은 기능

---
- [ ] 소셜 로그인 (Google, Kakao)
- [ ] 분석 결과 기능
- [ ] 좋아요 기능
- [ ] 영양제 등록 및 판매 기능
- [ ] Exception 생성 및 분리
- [ ] 다크 모드 지원
- [ ] 무한 스크롤
- [ ] 테스트 코드 (Jest / JUnit 등)

## 팀원

---
| 이름 | 역할     | GitHub 
|-------|--------|--------|
| 김영준 | FE, BE |        |
| 민사빈 | BE, FE | https://github.com/xabinM |
