<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Employees | MEC Employee Desk</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/employees">MEC <span>Employee Desk</span></a>
    <nav><a class="nav-cta" href="${pageContext.request.contextPath}/employees/new">+ Register employee</a></nav>
</header>
<main class="page-wrap">
    <div class="page-heading heading-row">
        <div>
            <p class="eyebrow">PEOPLE OPERATIONS</p>
            <h1>Employee directory</h1>
            <p class="muted">Manage and review your employee records.</p>
        </div>
        <a class="button button-primary desktop-cta" href="${pageContext.request.contextPath}/employees/new">+ Add employee</a>
    </div>

    <c:if test="${not empty successMessage}">
        <div class="success-banner" role="status"><span class="success-icon">✓</span><c:out value="${successMessage}" /></div>
    </c:if>

    <section class="panel directory-panel">
        <div class="panel-topline">
            <div><h2>All employees</h2><p class="muted small">A live list from the application repository</p></div>
            <span class="count-pill"><c:out value="${employees.size()}" /> records</span>
        </div>
        <c:choose>
            <c:when test="${empty employees}">
                <div class="empty-state">
                    <div class="empty-symbol">＋</div>
                    <h3>No employees registered yet</h3>
                    <p class="muted">Start by adding the first employee to the directory.</p>
                    <a class="button button-primary" href="${pageContext.request.contextPath}/employees/new">Register first employee</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-scroll">
                    <table>
                        <thead><tr>
                            <th>ID</th><th>Employee</th><th>Department</th><th>Designation</th><th>Salary / month</th><th>Joining date</th>
                        </tr></thead>
                        <tbody>
                        <c:forEach var="employee" items="${employees}">
                            <tr>
                                <td class="id-cell">#<c:out value="${employee.id}" /></td>
                                <td><div class="employee-name"><c:out value="${employee.name}" /></div><div class="muted small"><c:out value="${employee.email}" /></div></td>
                                <td><span class="department-tag"><c:out value="${employee.department}" /></span></td>
                                <td><c:out value="${employee.designation}" /></td>
                                <td class="salary-cell">₹<c:out value="${employee.salary}" /></td>
                                <td><c:out value="${employee.joiningDate}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:otherwise>
        </c:choose>
    </section>
    <p class="footnote">Audit events for employee service operations are written to the application log by Spring AOP.</p>
</main>
<footer class="footer">MEC Employee Desk <span>•</span> Spring MVC + AOP</footer>
</body>
</html>
