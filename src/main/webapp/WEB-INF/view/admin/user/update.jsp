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
                                <h1 class="mt-4">Manage User</h1>
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
                                    <h3>Update User</h3>
                                    <hr />
                                    <form:form method="post" action="/admin/user/update" modelAttribute="updateUser">
                                         <div class="mb-3" style="display:none;">
                                             <label class="form-label">Id:</label>
                                             <form:input type="text" name="id" class="form-control" path="id" />
                                         </div>
                                         <div class="mb-3">
                                              <label class="form-label">Email:</label>
                                              <form:input type="email" name="gmail" class="form-control" path="email" disabled="true" />
                                         </div>
                                        <div class="mb-3">
                                            <label class="form-label">Full Name:</label>
                                            <form:input type="text" name="fullName" class="form-control" path="fullName" />
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Phone Number:</label>
                                            <form:input type="text" name="phone" class="form-control" path="phone" />
                                        </div>
                                        <div class="mb-3">
                                            <label class="form-label">Address:</label>
                                            <form:input type="text" name="address" class="form-control" path="address"  />
                                        </div>
                                        <div class="mb-3">
                                             <label class="form-label">Role:</label>
                                             <form:select class="form-select" path="role.name">
                                             <form:option value="ADMIN">ADMIN</form:option>
                                             <form:option value="USER">USER</form:option>
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