<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Order - ${updateOrder.id}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .form-container {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2 {
            font-weight: bold;
            color: #343a40;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .btn-secondary {
            color: white;
            background-color: #6c757d;
            border-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #545b62;
            border-color: #4e555b;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="form-container mx-auto">
            <h2 class="text-center mb-4">Update Order</h2>

            <form:form method="post" action="/admin/order/update" modelAttribute="updateOrder">
                <form:hidden path="id" />

                <!-- Receiver Name -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Receiver Name</label>
                    <form:input type="text" class="form-control" path="receiverName" required="true" placeholder="Enter receiver's name"/>
                </div>

                <!-- Receiver Address -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Receiver Address</label>
                    <form:input type="text" class="form-control" path="receiverAddress" required="true" placeholder="Enter receiver's address"/>
                </div>

                <!-- Receiver Phone -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Receiver Phone</label>
                    <form:input type="text" class="form-control" path="receiverPhone" required="true" placeholder="Enter receiver's phone number"/>
                </div>

                <!-- Status -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Order Status</label>
                    <form:select class="form-control" path="status">
                        <form:option value="PENDING">Pending</form:option>
                        <form:option value="SHIPPED">Shipped</form:option>
                        <form:option value="DELIVERED">Delivered</form:option>
                    </form:select>
                </div>

                <!-- Buttons -->
                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-primary btn-sm px-4">Update</button>
                    <a href="/admin/order" class="btn btn-secondary btn-sm px-4">Back</a>
                </div>
            </form:form>
        </div>
    </div>
</body>
</html>
