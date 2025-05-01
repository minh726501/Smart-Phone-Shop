<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Detail ${id}</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<main>
        <div class="container-fluid px-4">
            <h1 class="mt-4">Manage User</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                <li class="breadcrumb-item active">User</li>
            </ol>
        </div>
    </main>
    <div class="container mt-5">
        <div class="row">
            <div class="col-12 mx-auto">
                <div class="d-flex justify-content-between">
                    <h3>User Detail ${id}</h3>
                </div>
                <div class="card" style="width:60%">
                    <div class="card-header">
                        User Information
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">ID: ${viewUser.id}</li>
                        <li class="list-group-item">Role: ${viewUser.getRole().getName()}</li>
                        <li class="list-group-item">Full Name: ${viewUser.fullName}</li>
                        <li class="list-group-item">Gmail: ${viewUser.email}</li>
                        <li class="list-group-item">Address: ${viewUser.address}</li>
                        <li class="list-group-item">
                        <p>Avatar raw value: ${viewUser.avatar}</p>

                           Image: <img src="/images/avatar/${viewUser.avatar}" alt="Ảnh sản phẩm" width="300" height="300">
                        </li>
                    </ul>

                </div>
                 <a href="/admin/user" class="btn btn-info mt-3">Back </a>
            </div>
        </div>
    </div>
</body>
</html>
