<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add User</title>

    <!-- favicon -->
    <link rel="icon" href="images/transformer.ico">

    <!-- Bootstrap -->
    <script src="js/pace.js"></script>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/theme.css" rel="stylesheet">
    <link href="css/font-awesome.css" rel="stylesheet">
    <link href="css/mystyle.css" rel="stylesheet">


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

<div class="container" id="container" style="display:none;">
    <section id="form">
        <div class="container">
            <div id="loginbox" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
                <div class="panel white-alpha-90">
                    <div class="panel-heading">
                        <div class="panel-title text-center">
                            <h2>Add User <span class="text-primary">OOC</span></h2></div>
                    </div>
                    <div class="panel-body">
                        <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                        <form id="loginform" class="form-horizontal" role="form" action="/add" method="post">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input id="login-username" type="text" class="form-control" name="firstname" value="" placeholder="firstname">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input id="login-username" type="text" class="form-control" name="lastname" value="" placeholder="lastname">
                            </div>
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username">
                            </div>
                            <div class="input-group col-xs-6">
                                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                                <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                            </div>

                            <div class="input-group center">
                                <span id="btn-login" style="border-left: 0px"><input type="submit" class="btn btn-success"></span>
                                <p><%if (request.getAttribute("error")!=null){%>
                                    <%=request.getAttribute("error")%>
                                    <%}%></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.backstretch.min.js"></script>
<script>
    Pace.on('hide', function() {
        $("#container").fadeIn('0');
        $.backstretch([
            "images/buildings.jpg",
            "images/paris_night.jpg"
        ], {
            duration: 5000,
            fade: 1000
        });
    });
</script>

<!-- disable back button -->
<%--<script type="text/javascript">--%>
    <%--window.history.forward();--%>
    <%--function noBack() { window.history.forward(); }--%>
<%--</script>--%>
</body>

</html>
