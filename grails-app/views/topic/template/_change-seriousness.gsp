<g:select name="seriousness" id="seriousness-${subscription.id}"
          from="${seriousness}"
          data-url="${g.createLink(controller: 'subscription', action: 'update')}"
          data-id="${subscription.id}"
          value="${subscription.seriousness}" class="form-control"/>