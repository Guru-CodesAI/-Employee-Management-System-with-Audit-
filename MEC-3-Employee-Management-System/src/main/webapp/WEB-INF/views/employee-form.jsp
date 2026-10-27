<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register Employee | MEC Employee Desk</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/styles.css">
</head>
<body>
<header class="topbar">
    <a class="brand" href="${pageContext.request.contextPath}/employees">MEC <span>Employee Desk</span></a>
    <nav><a href="${pageContext.request.contextPath}/employees">Employee list</a></nav>
</header>
<main class="page-wrap narrow">
    <div class="page-heading">
        <p class="eyebrow">EMPLOYEE MANAGEMENT</p>
        <h1>Register an employee</h1>
        <p class="muted">Add employee information to the directory.</p>
    </div>
    <section class="panel">
        <form:form method="post" modelAttribute="employee"
                   action="${pageContext.request.contextPath}/employees" cssClass="employee-form">
            <form:errors path="*" element="div" cssClass="error-summary" />
            <div class="form-grid">
                <div class="field full">
                    <label for="name">Full name</label>
                    <form:input path="name" id="name" placeholder="e.g. Priya Kumar" maxlength="80" />
                    <form:errors path="name" cssClass="field-error" />
                </div>
                <div class="field full">
                    <label for="email">Email address</label>
                    <form:input path="email" id="email" type="email" placeholder="name@example.com" maxlength="120" />
                    <form:errors path="email" cssClass="field-error" />
                </div>
                <div class="field">
                    <label for="department">Department</label>
                    <form:input path="department" id="department" placeholder="e.g. Engineering" maxlength="60" />
                    <form:errors path="department" cssClass="field-error" />
                </div>
                <div class="field">
                    <label for="designation">Designation</label>
                    <form:input path="designation" id="designation" placeholder="e.g. Software Engineer" maxlength="60" />
                    <form:errors path="designation" cssClass="field-error" />
                </div>
                <div class="field">
                    <label for="salary">Monthly salary (₹)</label>
                    <form:input path="salary" id="salary" type="number" min="0.01" step="0.01" placeholder="35000.00" />
                    <form:errors path="salary" cssClass="field-error" />
                </div>
                <div class="field">
                    <label for="joiningDate">Joining date</label>
                    <form:input path="joiningDate" id="joiningDate" type="date" />
                    <form:errors path="joiningDate" cssClass="field-error" />
                </div>
            </div>
            <div class="form-actions">
                <a class="button button-secondary" href="${pageContext.request.contextPath}/employees">Cancel</a>
                <button class="button button-primary" type="submit">Save employee <span aria-hidden="true">→</span></button>
            </div>
        </form:form>
    </section>
    <p class="footnote">All fields are required. Employee information is validated before it is saved.</p>
</main>
</body>
</html>
