<%-- 
    Document   : CustomerAppointments
    Created on : Oct 7, 2021, 11:35:27 PM
    Author     : ashto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Curl Me Crazy</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/site.css" />
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/site.js" asp-append-version="true"></script> 
    <script>
        $(document).ready(function(){
           
           $('#Header').load("Header.html");
           $("#Footer").load("Footer.html");
        
        });
        </script>
    </head>
    <body>
    <div id="Header"></div>
    <div>
        <p1>Appointments</p1>
    </div>
    <a href="/Controllers/test">appointment test</a>      
</body>
<footer id="Footer"></footer>
</html>
