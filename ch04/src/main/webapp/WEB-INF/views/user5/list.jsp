<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>user5::list</title>
</head>
<body>
<h3>user5 목록</h3>

<a href="/ch04">메인</a>
<a href="/ch04/user5/register">등록</a>

<table border="1">
    <tr>
        <th>순번</th>
        <th>이름</th>
        <th>성별</th>
        <th>나이</th>
        <th>주소</th>
        <th>관리</th>
    </tr>

    <c:forEach var="user" items="${users}">
    <tr>
        <td>${user.seq}</td>
        <td>${user.name}</td>
        <td>${user.gender}</td>
        <td>${user.age}</td>
        <td>${user.addr}</td>
        <td>
            <a href="/ch04/user5/modify?seq=${user.seq}">수정</a>
            <a href="/ch04/user5/delete?seq=${user.seq}">삭제</a>
        </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>