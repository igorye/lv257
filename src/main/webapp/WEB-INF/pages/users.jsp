<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>
<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
</head>
<body>
<jsp:include page="_menu2.jsp" />

<br />
<br />
<br />

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12 col-sm-offset-0 col-md-12 col-md-offset-0 main">
            <div class="container">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="${pageContext.request.contextPath}/users">Users</a></li>
                    <li> <a href="${pageContext.request.contextPath}/roles">Roles</a></li>
                    <li> <a href="${pageContext.request.contextPath}/privileges">Privileges</a></li>
                    <li> <a href="UsersRequests.html">Request <span class="badge">3</span></a></li>
                </ul>
            </div>
            <br/>
            <div class="row">
                <div class="container">
                    <div class="margin-bottom">
                        <button type="button" class="btn btn-primary">Add new User</button>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="table-responsive">
                    <%--<h2>Users management</h2>--%>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Enabled</th>
                            <th>Edit</th>
                            <th>Clone</th>
                            <th>Delete</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${not empty users}">

                            <ul>
                                <c:forEach var="listValue" items="${users}">
                                    <tr>
                                        <td>${listValue.id}</td>
                                        <td>${listValue.username}</td>
                                        <td>${listValue.password}</td>
                                        <td>${listValue.role.name}</td>
                                        <td>${listValue.enabled}</td>
                                        <td ><a href="${pageContext.request.contextPath}/roleInfo?rn=${listValue.id}">
                                            <span class="glyphicon glyphicon-edit"></span>
                                        </a></td>
                                        <td ><a href="#">
                                            <span class="glyphicon glyphicon-copy"></span>
                                        </a></td>
                                        <td ><a href="#">
                                            <span class="glyphicon glyphicon-remove"></span>
                                        </a></td>
                                    </tr>
                                </c:forEach>
                            </ul>

                        </c:if>
                        </tbody>
                    </table>
                </div>

                <br />
                <div class="container-fluid">
                    <div class="pull-left">
                        <h5>Page 1 of N</h5>
                    </div>
                    <div class="pull-right">
                        <div class="form-group form-inline">
                            <label class="font-normal" for="sel2">Show results per
                                page:</label> <select class="form-control" id="sel2">
                            <option>10</option>
                            <option>20</option>
                            <option>50</option>
                            <option>100</option>
                        </select>
                        </div>
                    </div>
                    <div class="container-fluid" align="center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li class="disabled"><a href="#" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a></li>
                                <li class="active"><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">5</a></li>
                                <li><a href="#" aria-label="Next"> <span
                                        aria-hidden="true">&raquo;</span>
                                </a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>