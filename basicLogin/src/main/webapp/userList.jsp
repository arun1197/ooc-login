<%@ page import="io.muic.ooc.webapp.Main" %>
<%@ page import="io.muic.ooc.webapp.ReadSQL" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>SuperheroAdmin - Bootstrap Admin Template</title>

    <!-- bootstrap -->
    <link href="css/userList/bootstrap.css" rel="stylesheet"/>

    <!-- libraries -->
    <link href="css/libs/font-awesome.css" type="text/css" rel="stylesheet"/>

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/compiled/layout.css">
    <link rel="stylesheet" type="text/css" href="css/compiled/elements.css">

</head>
<body>
    <%response.setHeader("Cache-Control","no-cache");%>
    <%response.setHeader("Cache-Control","no-store");%>
    <%response.setDateHeader("Expires", 0);%>
    <%response.setHeader("Pragma","no-cache");%>
    <% if(session.getAttribute("username")==null){
        response.sendRedirect("index.jsp");
    }
    %>
<div class="col-md-12" id="content-wrapper">
    <div class="row">
        <div class="col-lg-12">

            <div class="clearfix">
                <h1 class="pull-left"><%=session.getAttribute("username")%></h1>

                <div class="pull-right top-page-ui">
                    <a href="/add" class="btn btn-primary pull-right">
                        <i class="fa fa-plus-circle fa-lg"></i> Add user
                    </a>
                </div>
                <div class="pull-right top-page-ui">
                    <a href="/logout" class="btn btn-primary pull-right">
                        <i class="fa fa-sign-out fa-lg"></i> Logout
                    </a>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="main-box clearfix">
                        <div class="table-responsive">
                            <table class="table user-list">
                                <%--<c:forEach var="row" items="${qryProvider.rows}">--%>
                                <thead>


                                <tr>
                                    <th><span>User</span></th>
                                    <th><span>Firstname</span></th>
                                    <th><span>Lastname</span></th>
                                    <th>&nbsp;</th>
                                </tr>


                                    <%ReadSQL readSQL = new ReadSQL();%>
                                    <% for(List<String> i : readSQL.getUsers()){%>
                                    <tr><td><%=i.get(0)%></td><td><%=i.get(1)%></td><td><%=i.get(2)%></td>
                                        <%--<%--%>
                                            <%--try {--%>
                                                <%--if(session.getAttribute("username").toString().equals(session.getAttribute("username").toString()))--%>
<%--//                                                    System.out.println(request.getAttribute("session"));--%>
<%--//                                                System.out.println(request.getSession().getId());--%>
                                                <%--System.out.println(response.getStatus());--%>
                                                <%--%>--%>
                                                <td style="width: 10%;">
                                                    <a href="/delete" class="table-link danger">
                                                            <span class="fa-stack">
                                                            <i class="fa fa-square fa-stack-2x"></i>
                                                            <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                                                            </span>
                                                    </a>
                                                </td>
                                                <td style="width: 10%;">
                                                    <a href="/edit" >
                                                            <span class="fa-stack">
                                                            <i class="fa fa-square fa-stack-2x"></i>
                                                            <i class="fa fa-pencil-square-o fa-stack-1x fa-inverse"></i>
                                                            </span>
                                                    </a>
                                                </td>
                                        <%--<%} catch (Exception ex) {%>--%>
                                        <%--<%}%>--%>
                                    <%}%>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
</div>
</div>

<!-- global scripts -->
<script src="js/userList/jquery.js"></script>
<script src="js/userList/bootstrap.js"></script>

<!-- this page specific scripts -->


<!-- theme scripts -->
<script src="js/userList/scripts.js"></script>

<!-- disable backbutton -->
<%--<script type="text/javascript">--%>
    <%--window.history.forward();--%>
    <%--function noBack() { window.history.forward(); }--%>
<%--</script>--%>

<!-- this page specific inline scripts -->

</body>

</html>
