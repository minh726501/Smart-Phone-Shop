<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                        <!-- <link href="/css/demo.css" rel="stylesheet"> -->
                    </head>
                    <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Manage Product</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">User</li>
                                </ol>
                            </div>
                        </main>
                    <body>
                        <div class="container mt-5">
                            <div class="row">
                                <div class="col-md-6 col-12 mx-auto">
                                    <h3>Update Product</h3>
                                    <hr />
                                    <form:form method="post" action="/admin/product/update" modelAttribute="updateProduct">
                                    <form:hidden path="id" />

                                         <div class="mb-3">
                                              <label class="form-label">Name:</label>
                                              <form:input type="text" name="name" class="form-control" path="name"  />
                                         </div>
                                        <div class="mb-3">
                                            <label class="form-label">Price:</label>
                                            <form:input type="text" name="price" class="form-control" path="price" />
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">shortDesc:</label>
                                            <form:input type="text" name="shortDesc" class="form-control" path="shortDesc" />
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">detailDesc:</label>
                                            <form:input type="text" name="detailDesc" class="form-control" path="detailDesc"  />
                                        </div>
                                        <div class="mb-3">
                                             <label class="form-label">quantity:</label>
                                             <form:input type="text" name="quantity" class="form-control" path="quantity"  />
                                        </div>
                                        <div class="mb-3">
                                             <label class="form-label">factory:</label>
                                             <form:select class="form-select" path="factory">
                                             <form:option value="iPhone">iPhone</form:option>
                                             <form:option value="Samsung">Samsung</form:option>
                                             <form:option value="Xiaomi">Xiaomi</form:option>
                                             <form:option value="OPPO">OPPO</form:option>
                                             <form:option value="realme">realme</form:option>

                                        </form:select>

                                        </div>
                                        <button type="submit" class="btn btn-warning mx-2">Update</button>
                                        <a href="/admin/user" class="btn btn-secondary">Cancel</a>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </body>
            </html>