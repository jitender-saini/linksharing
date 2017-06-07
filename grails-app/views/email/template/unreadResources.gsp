<%@ page contentType="text/html" %>
<body>
<h1>Hi ${user.fullName}, you have few unRead items in your Inbox</h1>
<g:each in="${resourceList}" var="resource">

    <h2>Topic: ${resource.topic.name}</h2>

    <h3>Resource: ${resource.discription}</h3>
    <g:link url="http://${serverUrl}/user/index/">Open your inbox</g:link>

</g:each>
</body>