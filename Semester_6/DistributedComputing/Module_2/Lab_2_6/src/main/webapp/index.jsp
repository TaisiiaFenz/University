<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<body>
<h2>World map</h2>

<div class="authors-form" style="display: flex">
    <div>
        <h2>Add Author</h2>
        <form method="post" action="${pageContext.request.contextPath}/country">
            <div class="form-group">
                <label for="country_name" class="control-label">Name: </label>
                <input id="country_name" class="form-control" type="text"
                       name="country_name"/>
            </div>
            <div class="form-group">
                <label for="capital" class="control-label">Pseudonym: </label>
                <input id="capital" class="form-control" type="text"
                       name="capital"/>
            </div>
            <div class="form-group">
                <button id="button" type="submit" class="btn btn-success">Submit</button>
            </div>
        </form>
    </div>
    <div style="margin-left: 20px">
        <h2>Update Country</h2>
        <form method="post" action="${pageContext.request.contextPath}/update">
            <div class="form-group">
                <label for="country_id" class="control-label">Id: </label>
                <input id="country_id" class="form-control" type="number"
                       name="country_id"/>
            </div>
            <div class="form-group">
                <label for="countryname" class="control-label">Country name: </label>
                <input id="countryname" class="form-control" type="text"
                       name="country_name"/>
            </div>
            <div class="form-group">
                <label for="updatecapital" class="control-label">Capital: </label>
                <input id="updatecapital" class="form-control" type="text"
                       name="capital"/>
            </div>
            <div class="form-group">
                <button id="updateButton" type="submit" class="btn btn-success">Submit</button>
            </div>
        </form>
    </div>
</div>

<div>
    <a href="${pageContext.request.contextPath}/country">Get all counties</a>
</div>
<div class="countries">

    <h2><c:out value="${countryName}"/></h2>
    <c:forEach items="${countries}" var="country" varStatus="loop">
        <div>
            <h4><c:out value="${loop.index + 1}"/></h4>
            <p>Country id: <c:out value="${country.getCountryId()}"/></p>
            <p>Country name: <c:out value="${country.getCountryName()}"/></p>
            <p>Country capital: <c:out value="${country.getCapital()}"/></p>
        </div>
    </c:forEach>
    <a href="${pageContext.request.contextPath}/delete?countryId=${country.getCountryId()}">Delete</a>
</div>
</body>
</html>
