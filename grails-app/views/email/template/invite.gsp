<%@ page contentType="text/html" %>
<body>
<h1>Hi,checkout this topic "${topic.name}". Click on join to subscribe!!</h1>
<g:link url="http://${serverUrl}/topic/join/${topic.id}">Join</g:link>
</body>