<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
    <body style="box-shadow: 0 0 8px rgba(0, 0, 0, 0.15);
          width: 500px;
          display: flex;
          margin: 0 auto;
          padding: 30px"
    >
    <h2 style="text-align: center">
        World map
    </h2>

<div class="authors-form"
     style="display: flex"
>
    <div>
        <h2>Add Country</h2>
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

<div class="books-form" style="display: flex">
    <div>
        <h2>Add City</h2>
        <form method="post" action="${pageContext.request.contextPath}/city">
            <div class="form-group">
                <label for="city_name" class="control-label">City name: </label>
                <input id="city_name" class="form-control" type="text"
                       name="city_name"/>
            </div>
            <div class="form-group">
                <label for="population" class="control-label">Population: </label>
                <input id="population" class="form-control" type="number"
                       name="population_in_thousands"/>
            </div>
            <div class="form-group">
                <label for="countryId" class="control-label">Country id: </label>
                <input id="countryId" class="form-control" type="number"
                       name="country_id"/>
            </div>
            <div class="form-group">
                <button id="buttonCity" type="submit" class="btn btn-success">Submit</button>
            </div>
        </form>
    </div>
</div>

<div>
    <a href="${pageContext.request.contextPath}/city">Get all cities</a>
    <a href="${pageContext.request.contextPath}/country">Get all counties</a>
</div>
<div class="countries">
    <h2><c:out value="${countryTitle}"/></h2>
    <c:forEach items="${countries}" var="country" varStatus="loop">
        <div>
            <h4><c:out value="${loop.index + 1}"/></h4>
            <p>Country id: <c:out value="${country.getCountryId()}"/></p>
            <p>Country name: <c:out value="${country.getCountryName()}"/></p>
            <p>Country capital: <c:out value="${country.getCapital()}"/></p>
            <a href="${pageContext.request.contextPath}/delete?countryId=${country.getCountryId()}">Delete</a>
        </div>
    </c:forEach>
</div>

<div class="cities">
    <h2><c:out value="${cityTitle}"/></h2>
    <c:forEach items="${cities}" var="city" varStatus="loop">
        <div>
            <h4><c:out value="${loop.index +1 }"/></h4>
            <p>City id: <c:out value="${city.getCityId()}"/></p>
            <p>City name: <c:out value="${city.getCityName()}"/></p>
            <p>Population: <c:out value="${city.getPopulationInThousands()}"/></p>
            <p>Country id: <c:out value="${city.getCountryId()}"/></p>
            <a href="${pageContext.request.contextPath}/delete?id=${city.getCityId()}">Delete</a>
        </div>
    </c:forEach>
</div>
</body>
</html>
