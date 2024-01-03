<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Users</title>
</head>
<body class="bg-dark">
    <section class="content my-3">
        <div id="wrapper">
            <div class="container">
                <div class="row justify-content-around">
                    <form action="ManageUser" method="post" class="bg-black text-light col-md-5 bg-light p-3 my-3">
                    <%-- Hiển thị thông báo lỗi nếu có --%>
							    <% String errorString = (String)request.getAttribute("errorString"); %>
							    <% if(errorString != null && !errorString.isEmpty()) { %>
							        <div class="alert alert-danger">
							            <strong>Error:</strong> <%= errorString %>
							        </div>
							    <% } %>
                        <div class="row">
                            <div class="col-md-9">
                                <h1 class=" tex-uppercase h3 py-2">List of Users</h1>
                            </div>
                            
                            <div class="col-md-3 d-flex justify-content-end ">                                    
                                <button type="button" class="btn btn-success mt-6" id="add" onclick="location.href='AddUser'">
                                    <h6>Add Users</h6><i class="fa-solid fa-plus"></i> 
                                </button>
                            </div>
                        </div>
                    	
                    	<hr style="border-top: 1px solid white; margin-bottom: 20px;">
                    	
                        <div class="container mt-3">          
                            <table class="table table-dark text-light">
                              <thead>
                                <tr class="">
                                  <th class="col-1">STT</th>
                                  <th>Username</th>
                                  <th class="col-1">Edit</th>
                                  <th class="col-1">Delete</th>
                                </tr>
                              </thead>
                              <tbody>
                               <%int stt=1; %>
                                <c:forEach var="user" items="${userList}">
                                    <tr>
                                        <td><%=stt++%></td>
                                        <td><c:out value="${user.username}" /></td>
                                        
                                        <td>
                                            <a href="EditUser?idUser=${user.idUser}">
                                                <i class="fa-solid fa-pen-to-square"></i>
                                            </a>
                                        </td>
                                        
                                        <td>
                                            <a href="DeleteUser?idUser=${user.idUser}">
                                                <i class="fa-solid fa-trash"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                              </tbody>
                            </table>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
