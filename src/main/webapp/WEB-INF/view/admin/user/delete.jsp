<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm Delete User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <div class="col-6 mx-auto">
                <h3>Are you sure you want to delete this user?</h3>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">User: ${deleteUser.fullName}</h5>
                        <p class="card-text">Email: ${deleteUser.email}</p>
                        <p class="card-text">Phone: ${deleteUser.phone}</p>
                        <p class="card-text">Address: ${deleteUser.address}</p>


                        <form method="post" action="/admin/user/delete/${deleteUser.id}" modelAttribute="deleteUser">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <button type="submit" class="btn btn-danger">Delete</button>
                            <a href="/admin/user" class="btn btn-secondary">Cancel</a>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
