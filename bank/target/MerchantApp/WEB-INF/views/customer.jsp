<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Customers</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a Customer
</h1>

<c:url var="addAction" value="/customer/add" ></c:url>

<form:form action="${addAction}" commandName="customer">
<table>
	<c:if test="${!empty customer.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="surname">
				<spring:message text="Surname"/>
			</form:label>
		</td>
		<td>
			<form:input path="surname" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="username">
				<spring:message text="Username"/>
			</form:label>
		</td>
		<td>
			<form:input path="username" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="password">
				<spring:message text="Password"/>
			</form:label>
		</td>
		<td>
			<form:input path="password" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="jmbg">
				<spring:message text="Jmbg"/>
			</form:label>
		</td>
		<td>
			<form:input path="jmbg" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="passportNumber">
				<spring:message text="PassportNumber"/>
			</form:label>
		</td>
		<td>
			<form:input path="passportNumber" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="address">
				<spring:message text="Address"/>
			</form:label>
		</td>
		<td>
			<form:input path="address" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="phoneNumber">
				<spring:message text="PhoneNumber"/>
			</form:label>
		</td>
		<td>
			<form:input path="phoneNumber" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="accountNumber">
				<spring:message text="AccountNumber"/>
			</form:label>
		</td>
		<td>
			<form:input path="accountNumber" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="email">
				<spring:message text="Email"/>
			</form:label>
		</td>
		<td>
			<form:input path="email" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="owner">
				<spring:message text="Owner"/>
			</form:label>
		</td>
		<td>
			<form:input path="owner" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="age">
				<spring:message text="Age"/>
			</form:label>
		</td>
		<td>
			<form:input path="age" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty customer.name}">
				<input type="submit"
					value="<spring:message text="Edit Customer"/>" />
			</c:if>
			<c:if test="${empty customer.name}">
				<input type="submit"
					value="<spring:message text="Add Customer"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Customer List</h3>
<c:if test="${!empty listCustomers}">
	<table class="tg">
	<tr>
		<th width="80">Customer ID</th>
		<th width="120">Customer Name</th>
		<th width="120">Customer Surname</th>
		<th width="120">Customer Username</th>
		<th width="120">Customer Password</th>
		<th width="120">Customer Jmbg</th>
		<th width="120">Customer PassportNumber</th>
		<th width="120">Customer Address</th>
		<th width="120">Customer PhoneNumber</th>
		<th width="120">Customer AccountNumber</th>
		<th width="120">Customer Email</th>
		<th width="120">Customer Owner</th>
		<th width="120">Customer Age</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listCustomers}" var="customer">
		<tr>
			<td>${customer.id}</td>
			<td>${customer.name}</td>
			<td>${customer.surname}</td>
			<td>${customer.username}</td>
			<td>${customer.password}</td>
			<td>${customer.jmbg}</td>
			<td>${customer.passportNumber}</td>
			<td>${customer.address}</td>
			<td>${customer.phoneNumber}</td>
			<td>${customer.accountNumber}</td>
			<td>${customer.email}</td>
			<td>${customer.owner}</td>
			<td>${customer.age}</td>
			<td><a href="<c:url value='/edit/${customer.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${customer.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>
