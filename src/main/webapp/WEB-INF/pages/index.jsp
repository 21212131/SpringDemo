<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head lang="en">
    <meta charset="UTF-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" th:href="@{/css/bootstrap.min.css}"/>
</head>
<body ms-controller="viewmodel">
<h1 th:text="${msg}">Hello World</h1>
<button type="button" class="btn btn-primary" style="margin: 10px;" ms-click="@request">{{@text}}</button>
<table class="table table-striped">
    <thead>
    <tr>
        <td class="active">id</td>
        <td class="success">姓名</td>
        <td class="warning">性别</td>
        <td class="danger">年龄</td>
        <td class="info">角色</td>
    </tr>
    </thead>
    <tbody>
    <tr ms-for="el in @datalist">
        <td >{{el.id}}</td>
        <td >{{el.name}}</td>
        <td>{{el.sex}}</td>
        <td >{{el.age}}</td>
        <td >{{el.role}}</td>
    </tr>
    </tbody>
</table>
</body>
<script th:src="@{/js/jquery-2.1.1.min.js}"></script>
<script th:src="@{/js/avalon.js}"></script>
<script th:src="@{/js/bootstrap.min.js}"></script>
<script th:src="@{/js/myscript.js}"></script>
</html>