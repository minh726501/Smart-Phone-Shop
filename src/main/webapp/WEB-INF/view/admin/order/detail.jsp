<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details - ${viewOrder.id}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<main>
    <div class="container-fluid px-4">
        <h1 class="mt-4">Order Details</h1>
        <ol class="breadcrumb mb-4">
            <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
            <li class="breadcrumb-item"><a href="/admin/order">Orders</a></li>
            <li class="breadcrumb-item active">Order #${viewOrder.id}</li>
        </ol>
    </div>
</main>
<div class="container mt-5">
    <div class="row">
        <div class="col-12 mx-auto">
            <div class="card">
                <div class="card-header">
                    Order Information
                </div>
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Sản phẩm</th>
                                <th scope="col">Tên</th>
                                <th scope="col">Giá cả</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">Thành tiền</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Kiểm tra nếu đơn hàng không có sản phẩm -->
                            <c:if test="${empty viewOrder.orderProducts}">
                                <tr>
                                    <td colspan="5">Không có sản phẩm trong đơn hàng</td>
                                </tr>
                            </c:if>
                            <!-- Lặp qua danh sách sản phẩm trong đơn hàng -->
                            <c:forEach var="orderProduct" items="${viewOrder.orderProducts}">
                                <tr>
                                    <td>
                                        <img src="/images/product/${orderProduct.product.image}"
                                             class="img-fluid rounded-circle"
                                             style="width: 80px; height: 80px;"
                                             alt="">
                                    </td>
                                    <td>
                                        <p class="mb-0 mt-4">
                                            <a href="/product/${orderProduct.product.id}" target="_blank">
                                                ${orderProduct.product.name}
                                            </a>
                                        </p>
                                    </td>
                                    <td>
                                        <p class="mb-0 mt-4">
                                            <fmt:formatNumber type="number" value="${orderProduct.price}" /> đ
                                        </p>
                                    </td>
                                    <td>
                                        <p class="mb-0 mt-4">${orderProduct.quantity}</p>
                                    </td>
                                    <td>
                                        <p class="mb-0 mt-4">
                                            <fmt:formatNumber type="number" value="${orderProduct.price * orderProduct.quantity}" /> đ
                                        </p>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <a href="/admin/order" class="btn btn-success mt-3">Back</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
