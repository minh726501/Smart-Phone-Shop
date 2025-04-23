<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
    $(document).ready(() => {
        const avatarFile = $("#avatarFile");
        avatarFile.change(function (e) {
            const imgURL = URL.createObjectURL(e.target.files[0]);
            $("#avatarPreview").attr("src", imgURL);
            $("#avatarPreview").css({ "display": "block" });
        });
    });
    </script>

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
            <div class="col-md-6 col-12 mx-auto">
                <h3>Create a user</h3>
                <hr />
                <form method="post" action="/admin/user/create" modelAttribute="newUser" class="row" enctype="multipart/form-data">
                    <div class="mb-3 col-12 col-md-6">
                        <label class="form-label">Email:</label>
                        <input type="email" name="email" class="form-control" path="email" />

                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <label class="form-label">Full Name:</label>
                        <input type="text" name="fullName" class="form-control" path="fullName" />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <label class="form-label">Phone Number:</label>
                        <input type="text" name="phone" class="form-control" path="phone" />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <label class="form-label">Address:</label>
                        <input type="text" name="address" class="form-control" path="address" />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <label class="form-label">Password:</label>
                        <input type="password" name="password" class="form-control" path="password" />
                    </div>
                    <%--div class="mb-3 col-12 col-md-6">
                        <label class="form-label">Role:</label>
                        <form:select class="form-select" path="role.name">
                            <form:option value="ADMIN">ADMIN</form:option>
                            <form:option value="USER">USER</form:option>
                        </form:select>
                    </div--%>
                    <div class="mb-3 col-12 col-md-6">
                        <label for="avatarFile" class="form-label">Avatar:</label>
                        <input class="form-control" type="file" id="avatarFile" accept=".png, .jpg, .jpeg, .HEIC" name="bqmFile"/>
                    </div>
                    <div class="col-12 mb-3">
                        <img style="max-height: 250px; display: none;" alt="avatar preview" id="avatarPreview">
                    </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
