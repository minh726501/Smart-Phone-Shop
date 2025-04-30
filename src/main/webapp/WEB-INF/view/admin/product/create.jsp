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
            <h1 class="mt-4">Manage Product</h1>
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                <li class="breadcrumb-item active">Product</li>
            </ol>
        </div>
    </main>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 col-12 mx-auto">
                <h3>Create a product</h3>
                <hr />
                <form:form method="post" action="/admin/product/create" modelAttribute="newProduct" class="row" enctype="multipart/form-data">
                    <div class="mb-3 col-12 col-md-6">
                        <label class="form-label"> Name:</label>
                        <form:input type="text" name="name" class="form-control" path="name" />
                         <form:errors path="name" cssClass="text-danger" />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <label class="form-label">Price:</label>
                        <form:input type="text" name="price" class="form-control" path="price" />
                         <form:errors path="price" cssClass="text-danger" />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <label class="form-label">Detail Description:</label>
                        <form:input type="text" name="detailDesc" class="form-control" path="detailDesc" />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <label class="form-label">Short Description:</label>
                        <form:input type="text" name="shortDesc" class="form-control" path="shortDesc" />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                         <label class="form-label">Quantity:</label>
                         <form:input type="text" name="quantity" class="form-control" path="quantity" />
                          <form:errors path="quantity" cssClass="text-danger" />
                    </div>
                    <div class="mb-3 col-12 col-md-6">
                        <label class="form-label">Factory:</label>
                        <form:select class="form-select" path="factory">
                            <form:option value="iPhone">iPhone</form:option>
                            <form:option value="Samsung">Samsung</form:option>
                            <form:option value="Xiaomi">Xiaomi</form:option>
                            <form:option value="OPPO">OPPO</form:option>
                            <form:option value="realme">realme</form:option>

                        </form:select>
                    </div>

                    <div class="mb-3 col-12 col-md-6">
                        <label for="avatarFile" class="form-label">Image:</label>
                        <input class="form-control" type="file" id="avatarFile" accept=".png, .jpg, .jpeg, .HEIC" name="bqmFile"/>
                    </div>
                    <div class="col-12 mb-3">
                        <img style="max-height: 250px; display: none;" alt="avatar preview" id="avatarPreview">
                    </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
