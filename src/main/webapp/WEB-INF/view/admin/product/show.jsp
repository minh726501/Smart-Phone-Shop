<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Table User</title>
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
                    <h3>Table Product</h3>
                    <a href="/admin/product/create" class="btn btn-primary">Create a product</a>
                </div>
                <hr />
                <table class="table table-bordered table-hover">

                </table>
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Manage Product</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                            <li class="breadcrumb-item active">Product</li>
                        </ol>

                    </div>
                </main>
                <table class="table table-bordered table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>

                            <th>Factory</th>
                            <th>Action</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${product}">
                            <tr>
                                <td>${p.id}</td>
                                <td>${p.name}</td>
                                <td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="$" maxFractionDigits="0"/></td>
                                <td>${p.factory}</td>

                                <td>
                                    <a href="/admin/product/view/${p.id}" class="btn btn-success">View</a>
                                    <a href="/admin/product/update/${p.id}" class="btn btn-warning mx-2">Update</a>
                                    <a href="/admin/product/delete/${p.id}" class="btn btn-danger">Delete</a>
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
