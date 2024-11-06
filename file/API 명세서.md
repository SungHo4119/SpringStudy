---
title: SpringStudy
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
code_clipboard: true
highlight_theme: darkula
headingLevel: 2
generator: "@tarslib/widdershins v4.0.23"

---

# SpringStudy

Base URLs:

# Authentication

# require

<a id="opIdgetBoardList"></a>

## GET getBoardList

GET /v1/board/list

게시판 전체 조회

> Response Examples

```json
[
  {
    "id": 1,
    "title": "title",
    "content": "Defero curis ut vito sed. Voluptate voveo testimonium urbanus animadverto artificiose desidero. Amor astrum caelum corporis sulum terreo.",
    "create_at": "2024-11-04T05:21:12.640Z",
    "update_at": "2024-01-06",
    "user": {
      "id": 1,
      "user_name": "tjd4119"
    }
  }
]
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResponseGetBoardList](#schemaresponsegetboardlist)|

<a id="opIdcreateBoard"></a>

## POST createBoard

POST /v1/board

게시글 작성

> Body Parameters

```json
{
  "title": "title",
  "content": "content",
  "user": {
    "id": 1,
    "user_name": "tjd4119",
    "password": "SungHoLim1"
  }
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|body|body|[RequestCreateBoard](#schemarequestcreateboard)| no |none|

> Response Examples

```json
{
  "id": 1,
  "title": "title",
  "content": "content",
  "create_at": "2024-11-03T13:04:11.781Z",
  "update_at": "2024-11-03T13:04:11.781Z",
  "user": {
    "id": 1,
    "user_name": "tjd4119"
  }
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|none|[ResponseCreateBoard](#schemaresponsecreateboard)|

<a id="opIdgetBoard"></a>

## GET getBoard

GET /v1/board/{board_id}

선택한 게시물 조회

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|board_id|path|integer| yes |게시판 ID|

> Response Examples

```json
{
  "id": 1,
  "title": "title",
  "content": "content",
  "create_at": "2024-11-03T23:20:41.591Z",
  "update_at": "2024-11-03T23:20:41.591Z",
  "user": {
    "id": 1,
    "user_name": "tjd4119"
  }
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResponseGetBoard](#schemaresponsegetboard)|

<a id="opIdupdateBoard"></a>

## PATCH updateBoard

PATCH /v1/board/{board_id}

선택한 게시글 수정

> Body Parameters

```json
{
  "title": "title",
  "content": "content",
  "user": {
    "id": 1,
    "user_name": "tjd4119",
    "password": "SungHoLim1"
  }
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|board_id|path|integer| yes |게시판 아이디|
|body|body|[RequestUpdateBoard](#schemarequestupdateboard)| no |none|

> Response Examples

```json
{
  "id": 1,
  "title": "title",
  "content": "content",
  "create_at": "2024-11-04T07:49:23.831Z",
  "update_at": "2024-11-04T07:49:23.831Z",
  "user": {
    "id": 1,
    "user_name": "tjd4119"
  }
}
```

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|none|[ResponseUpdateBoard](#schemaresponseupdateboard)|

<a id="opIddeleteBoard"></a>

## DELETE deleteBoard

DELETE /v1/board/{board_id}

선택한 게시글 삭제

> Body Parameters

```json
{
  "id": 1,
  "user_name": "tjd4119",
  "password": "SungHoLim1"
}
```

### Params

|Name|Location|Type|Required|Description|
|---|---|---|---|---|
|board_id|path|integer| yes |게시판 아이디|
|body|body|[RequestDeleteBoard](#schemarequestdeleteboard)| no |none|

> Response Examples

> 204 Response

### Responses

|HTTP Status Code |Meaning|Description|Data schema|
|---|---|---|---|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|none|Inline|

### Responses Data Schema

# Data Schema

<h2 id="tocS_user">user</h2>

<a id="schemauser"></a>
<a id="schema_user"></a>
<a id="tocSuser"></a>
<a id="tocsuser"></a>

```json
{
  "id": 0,
  "user_name": "string",
  "password": "stringst"
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|id|integer|true|none||PK - ID|
|user_name|string|true|none||User_Name|
|password|string|true|none||Password|

<h2 id="tocS_board">board</h2>

<a id="schemaboard"></a>
<a id="schema_board"></a>
<a id="tocSboard"></a>
<a id="tocsboard"></a>

```json
{
  "id": 0,
  "user_id": 0,
  "title": "string",
  "content": "string",
  "create_at": "2019-08-24T14:15:22Z",
  "update_at": "2019-08-24T14:15:22Z"
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|id|integer|true|none||PK - ID|
|user_id|integer|true|none||FK - user|
|title|string|true|none||게시판 제목|
|content|string|true|none||게시판 내용|
|create_at|string(date-time)|true|none||생성 날짜|
|update_at|string(date-time)|true|none||수정 날짜|

<h2 id="tocS_ResponseGetBoard">ResponseGetBoard</h2>

<a id="schemaresponsegetboard"></a>
<a id="schema_ResponseGetBoard"></a>
<a id="tocSresponsegetboard"></a>
<a id="tocsresponsegetboard"></a>

```json
{
  "id": 0,
  "title": "string",
  "content": "string",
  "create_at": "2019-08-24T14:15:22Z",
  "update_at": "2019-08-24T14:15:22Z",
  "user": {
    "id": 0,
    "user_name": "string"
  }
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|id|integer|true|none||PK - ID|
|title|string|true|none||게시판 제목|
|content|string|true|none||게시판 내용|
|create_at|string(date-time)|true|none||생성 날짜|
|update_at|string(date-time)|true|none||수정 날짜|
|user|object|true|none||none|
|» id|integer|true|none||PK - ID|
|» user_name|string|true|none||User_Name|

<h2 id="tocS_ResponseGetBoardList">ResponseGetBoardList</h2>

<a id="schemaresponsegetboardlist"></a>
<a id="schema_ResponseGetBoardList"></a>
<a id="tocSresponsegetboardlist"></a>
<a id="tocsresponsegetboardlist"></a>

```json
[
  {
    "id": 0,
    "title": "string",
    "content": "string",
    "create_at": "2019-08-24T14:15:22Z",
    "update_at": "2019-08-24T14:15:22Z",
    "user": {
      "id": 0,
      "user_name": "string"
    }
  }
]

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|id|integer|true|none||PK - ID|
|title|string|true|none||게시판 제목|
|content|string|true|none||게시판 내용|
|create_at|string(date-time)|true|none||생성 날짜|
|update_at|string(date-time)|true|none||수정 날짜|
|user|object|true|none||none|
|» id|integer|true|none||PK - ID|
|» user_name|string|true|none||User_Name|

<h2 id="tocS_RequestCreateBoard">RequestCreateBoard</h2>

<a id="schemarequestcreateboard"></a>
<a id="schema_RequestCreateBoard"></a>
<a id="tocSrequestcreateboard"></a>
<a id="tocsrequestcreateboard"></a>

```json
{
  "title": "string",
  "content": "string",
  "user": {
    "id": 0,
    "user_name": "string",
    "password": "stringst"
  }
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|title|string|true|none||게시판 제목|
|content|string|true|none||게시판 내용|
|user|object|true|none||none|
|» id|integer|true|none||PK - ID|
|» user_name|string|true|none||User_Name|
|» password|string|true|none||Password|

<h2 id="tocS_ResponseCreateBoard">ResponseCreateBoard</h2>

<a id="schemaresponsecreateboard"></a>
<a id="schema_ResponseCreateBoard"></a>
<a id="tocSresponsecreateboard"></a>
<a id="tocsresponsecreateboard"></a>

```json
{
  "id": 0,
  "title": "string",
  "content": "string",
  "create_at": "2019-08-24T14:15:22Z",
  "update_at": "2019-08-24T14:15:22Z",
  "user": {
    "id": 0,
    "user_name": "string"
  }
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|id|integer|true|none||PK - ID|
|title|string|true|none||게시판 제목|
|content|string|true|none||게시판 내용|
|create_at|string(date-time)|true|none||생성 날짜|
|update_at|string(date-time)|true|none||수정 날짜|
|user|object|true|none||none|
|» id|integer|true|none||PK - ID|
|» user_name|string|true|none||User_Name|

<h2 id="tocS_RequestUpdateBoard">RequestUpdateBoard</h2>

<a id="schemarequestupdateboard"></a>
<a id="schema_RequestUpdateBoard"></a>
<a id="tocSrequestupdateboard"></a>
<a id="tocsrequestupdateboard"></a>

```json
{
  "title": "string",
  "content": "string",
  "user": {
    "id": 0,
    "user_name": "string",
    "password": "stringst"
  }
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|title|string|true|none||게시판 제목|
|content|string|true|none||게시판 내용|
|user|object|true|none||none|
|» id|integer|true|none||PK - ID|
|» user_name|string|true|none||User_Name|
|» password|string|true|none||Password|

<h2 id="tocS_ResponseUpdateBoard">ResponseUpdateBoard</h2>

<a id="schemaresponseupdateboard"></a>
<a id="schema_ResponseUpdateBoard"></a>
<a id="tocSresponseupdateboard"></a>
<a id="tocsresponseupdateboard"></a>

```json
{
  "id": 0,
  "title": "string",
  "content": "string",
  "create_at": "2019-08-24T14:15:22Z",
  "update_at": "2019-08-24T14:15:22Z",
  "user": {
    "id": 0,
    "user_name": "string"
  }
}

```

### Attribute

|Name|Type|Required|Restrictions|Title|Description|
|---|---|---|---|---|---|
|id|integer|true|none||PK - ID|
|title|string|true|none||게시판 제목|
|content|string|true|none||게시판 내용|
|create_at|string(date-time)|true|none||생성 날짜|
|update_at|string(date-time)|true|none||수정 날짜|
|user|object|true|none||none|
|» id|integer|true|none||PK - ID|
|» user_name|string|true|none||User_Name|

<h2 id="tocS_RequestDeleteBoard">RequestDeleteBoard</h2>

<a id="schemarequestdeleteboard"></a>
<a id="schema_RequestDeleteBoard"></a>
<a id="tocSrequestdeleteboard"></a>
<a id="tocsrequestdeleteboard"></a>

```json
{
  "id": 0,
  "user_name": "string",
  "password": "stringst"
}

```

### Attribute

*None*

