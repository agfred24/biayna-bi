<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.
%>
<%@ include file="header.jsp"%>
<div class="container">

	<div class="row">
		<div class="col-xl-3 col-lg-3 col-md-2 col-sm-1 col-1"></div>
		<div class="col-xl-6 col-lg-6 col-md-8 col-sm-10 col-10 p-4 rounded"
			style="background-color: #8EC9FF;">

			<form id="registrationForm" action="registration.html" method="post">

				<p class="lead text-muted ml-1">Registration</p>

				<c:if test="${not empty errors.databaseError}">
					<c:choose>
						<c:when test="${not empty errors.databaseError}">  
     	  					<div class="alert alert-danger small" role="alert">${errors.databaseError}</div>
    					</c:when>
						<c:otherwise>  
       						<div class="alert alert-danger small" role="alert">Please provide the required information.</div>
    					</c:otherwise>
					</c:choose>					
				</c:if>
				
				<c:if test="${isRegistered}">
					<div class="alert alert-success small" role="alert">The user has been registered successfully.</div>
				</c:if>
				
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="fas fa-users small"></i></span>
					</div>
					<select class="custom-select custom-select-md form-control <c:if test="${not empty errors.role}">is-invalid</c:if>"
						title="Role" name="roleId">
						<option value="0">Role</option>
						<option value="1" <c:if test="${user.roleId == 1}">selected</c:if>>User</option>
						<option value="2" <c:if test="${user.roleId == 2}">selected</c:if>>Producer</option>
						<option value="3" <c:if test="${user.roleId == 3}">selected</c:if>>Administrator</option>
					</select>
					<div class="invalid-feedback mx-4">${errors.role}</div>
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="fas fa-user"></i></span>
					</div>
					<input type="text"
						class="form-control <c:if test="${not empty errors.firstName}">is-invalid</c:if>"
						placeholder="First Name" aria-label="firstName" name="firstName" id="firstName" value="${user.firstName}"/>
					<div class="invalid-feedback mx-4">${errors.firstName}</div>
				</div>

				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="fas fa-user"></i></span>
					</div>
					<input type="text"
						class="form-control <c:if test="${not empty errors.lastName}">is-invalid</c:if>"
						placeholder="Last Name" aria-label="lastName" name="lastName" id="lastName" value="${user.lastName}" />
					<div class="invalid-feedback mx-4">${errors.lastName}</div>
				</div>

				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="fas fa-envelope"></i></span>
					</div>
					<input type="text"
						class="form-control  <c:if test="${not empty errors.email}">is-invalid</c:if>"
						placeholder="Email" aria-label="Email" name="email" id="email" value="${user.email}"/>
					<div class="invalid-feedback mx-4">${errors.email}</div>
				</div>

				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="fas fa-lock"></i></span>
					</div>
					<input type="password" 
						class="form-control <c:if test="${not empty errors.password}">is-invalid</c:if>" 
						placeholder="Password" aria-label="Password" aria-describedby="basic-addon1"
						name="password" id="password" />
						<div class="invalid-feedback mx-4">${errors.password}</div>
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="fas fa-lock"></i></span>
					</div>
					<input type="password" 
						class="form-control  <c:if test="${not empty errors.confirmPassword}">is-invalid</c:if>"
						placeholder="Confirm Password"	aria-label="confirmPassword" aria-describedby="basic-addon1"
						name="confirmPassword" id="confirmPassword" />	
						<div class="invalid-feedback mx-4">${errors.confirmPassword}</div>					
				</div>
				
				<button type="submit" class="btn btn-outline-primary btn-block">Register</button>
			</form>
			<div class="lead small pt-2 pl-1">
				<a href="login.html">Log in</a>
			</div>
		</div>
		<div class="col-xl-3 col-lg-3 col-md-2 col-sm-1 col-1 "></div>
	</div>
	<div class="row">
		<div class="col-12">
			<p class="text-center">
				<br /> Copyright &copy; - Biayna
				<%=java.time.LocalDateTime.now().getYear()%>
			</p>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>