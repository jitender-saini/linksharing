<!doctype html>
<html lang="en" class="no-js" xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="shortcut icon" href="${createLinkTo(dir: 'images', file: 'favicon.ico')}" type="image/x-icon"/>
    <title>
        <g:layoutTitle default="LinkSharing"/>
    </title>
    <asset:stylesheet src="application.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <g:layoutHead/>
</head>

<body>
<g:render template="/header/header"/>
<div class="main-page">
    <g:render template="/topic/template/create"/>
    <g:render template="/topic/template/invite"/>
    <g:render template="/resource/template/create-link"/>
    <g:render template="/resource/template/create-doc"/>
</div>

<g:if test="${flash.message}">
    <div class="alert alert-success">${flash.message}</div>
</g:if>
<g:if test="${flash.error}">
    <div class="alert alert-danger">${flash.error}</div>
</g:if>

<div id="flash"></div>

<div class="main-page">
    <g:layoutBody/>
</div>
<asset:javascript src="application.js"/>
%{--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--}%
%{--<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.js"></script>--}%
%{--<asset:javascript src="jquery.validate.js"/>--}%
%{--<asset:javascript src="jquery.js"/>--}%
<asset:deferredScripts/>
</body>
</html>
