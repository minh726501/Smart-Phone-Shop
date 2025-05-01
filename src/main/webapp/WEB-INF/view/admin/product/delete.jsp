<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirm Delete Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
            <div class="col-6 mx-auto">
                <h3>Are you sure you want to delete this product?</h3>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Product: ${deleteProduct.name}</h5>
                        <p class="card-text">Factory: ${deleteProduct.factory}</p>
                        <p class="card-text">Sold: ${deleteProduct.sold}</p>
                        <p class="card-text">Image: <img src="/images/product/${deleteProduct.image}" alt="Product Image" width="500" height="auto"></p>

                        <!-- Form xóa sản phẩm -->
                        <form method="post" action="/admin/product/delete/${deleteProduct.id}">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <button type="submit" class="btn btn-danger">Delete</button>
                            <a href="/admin/product" class="btn btn-secondary">Cancel</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
