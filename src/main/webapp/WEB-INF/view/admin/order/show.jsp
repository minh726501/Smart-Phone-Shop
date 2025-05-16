<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Table Order</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-12 mx-auto">
                <div class="d-flex justify-content-between">
                    <h3>Table Order</h3>
                    <a href="/admin/product/create" class="btn btn-primary">Create a order</a>
                </div>
                <hr />
                <table class="table table-bordered table-hover">

                </table>
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Manage Order</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                            <li class="breadcrumb-item active">Order</li>
                        </ol>

                    </div>
                </main>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Total Price</th>
                            <th>User</th>

                            <th>Status</th>
                            <th>Action</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${order}">
                            <tr>
                                <td>${order.id}</td>
                                <td>
                                    <fmt:formatNumber type="number"
                                         value="${order.totalPrice}" /> $
                                </td>
                                <td>${order.user.fullName}</td>
                                <td>${order.status}</td>

                                <td>
                                    <a href="/admin/order/view/${order.id}" class="btn btn-success">View</a>
                                    <a href="/admin/order/update/${order.id}" class="btn btn-warning mx-2">Update</a>
                                    <a href="/admin/order/delete/${order.id}" class="btn btn-danger">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
