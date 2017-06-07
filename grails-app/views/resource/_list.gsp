<ul>
    <g:each in="${resources}" var="resource" status="index">
        <li>
            ${index})
            <g:link controller="resource" action="showResource" id="${resource.id}">
                ${resource.description}
            </g:link>
            <ls:isRead resource="${resource}"/>
        </li>
    </g:each>

</ul>
