<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<head>
    <title>${title}</title>
    <jsp:include page="metadata.jsp"/>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/dataTables.foundation.min.js"></script>

</head>

<body>

<jsp:include page="menu.jsp"/>

<div class="container">
    <ul class="nav nav-tabs">
        <li><a href="${contextPath}/resources/view">View</a></li>

        <li><a href="${contextPath}/resources/addType">Add</a></li>
        <li class="active"><a href="#">Requests</a></li>
    </ul>
</div>
<br>
<div class="container">

    <div class="table-responsive">
        <h3>Processed requests for resource type</h3>

        <table class="table table-hover table-condensed text-center table-bordered" id="requests">
            <thead>
            <tr>
                <th>
                    <div class="text-center ">
                        RequestedCategory
                    </div>

                </th>
                <th>
                    <div
                            class="text-center ">Who requested
                    </div>
                    <div class="fht-cell"></div>

                </th>
                <th>
                    <div
                            class="text-center ">Date
                    </div>
                    <div class="fht-cell"></div>
                </th>
                <th>
                    <div
                            class="text-center">Who is processing
                    </div>
                    <div class="fht-cell"></div>
                </th>

                <th>
                    <div
                            class="text-center">Status
                    </div>
                    <div class="fht-cell"></div>
                </th>
            </tr>
            <tr>

                <td><input type="text" class="form-control" id="searchReqCat" placeholder="Requested category"></td>
                <td><input type="text" class="form-control" id="searchRegister" placeholder="Register"></td>
                <td><input type="text" class="form-control" id="searchDate" placeholder="Date"></td>
                <td><input type="text" class="form-control" id="searchResAdm" placeholder="Resource Admin"></td>
                <td><input type="text" class="form-control" id="searchStatus" placeholder="Status"></td>

            </tr>
            </thead>
            <tbody>


            <c:forEach items="${resourceRequest}" var="request">
                <c:choose>
                <c:when test="${request.status=='ACCEPTED'}">
                    <tr name="simpleRequest" class="success">
                </c:when>
                <c:otherwise>
                    <tr name="simpleRequest" class="danger">
                </c:otherwise>
                </c:choose>


                    <td>${request.resourceType}</td>
                    <td>${request.requesterName}</td>
                    <td>${request.update.toString().split('\\.')[0]}</td>
                    <td>${request.assignerName}</td>
                    <td>${request.status}</td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
    <br>
    <div align="right">

        <button class="btn btn-primary" onclick="history.back()">See new requests</button>

    </div>
</div>
</body>
<script>
    $(document).ready(function () {
        var table = $('#requests').DataTable({
            "order": [[2, "desc"]],
            'dom': 'rt<"bottom"lp><"clear">',
        });
        $("#searchReqCat").on('keyup change', function () {
            table
                .columns(0)
                .search(this.value)
                .draw();
        });
        $("#searchRegister").on('keyup change', function () {
            table
                .columns(1)
                .search(this.value)
                .draw();
        });
        $("#searchDate").on('keyup change', function () {
            table
                .columns(2)
                .search(this.value)
                .draw();
        });
        $("#searchResAdm").on('keyup change', function () {
            table
                .columns(3)
                .search(this.value)
                .draw();
        });
        $("#searchStatus").on('keyup change', function () {
            table
                .columns(4)
                .search(this.value)
                .draw();
        });
    });
</script>
</html>