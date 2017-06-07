<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" style="padding: 5px" href='${createLink(controller: 'login', action: 'index')}'><g:img dir="images"
                                                                                                      file="logo.png"
                                                                                                      width="110"/></a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>

                     <g:form class="navbar-form navbar-left" controller="Search" action="show" role="search"
                            name="search-form">
                        <div class="input-group add-on">
                            <g:textField name="q" type="text" class="form-control search-main" placeholder="Search"
                                         id="search"/>
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit">
                                    <i class="fa fa-search"></i></button>
                            </div>
                        </div>
                    </g:form>
                </li>
                <ls:isLoggedIn>
                    <li>
                        <a href="javascript:void(0)" data-toggle="modal" data-target="#topicCreate">
                            <i class="fa fa-comment" aria-hidden="true" title="Create Topic"></i>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-toggle="modal" data-target="#send-invite">
                            <i class="fa fa-envelope" aria-hidden="true" title="Send Invitation"></i>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-toggle="modal" data-target="#link-create">
                            <i class="fa fa-link" aria-hidden="true" title="Share Link Resource"></i>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void(0)" data-toggle="modal" data-target="#doc-create">
                            <i class="fa fa-file-o" aria-hidden="true" title="Share Document Resource"></i>
                        </a>
                    </li>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${session.user.userName} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href='${createLink(controller: 'user', action: 'profile', params: [userId: session.user.id])}'>Profile</a>
                            </li>
                            <li>
                                <a href='${createLink(controller: 'user', action: 'editProfile')}'>Edit Profile</a>
                            </li>
                            <ls:isAdminLoggedIn>
                                <li>
                                    <a href='${createLink(controller: 'user', action: 'usersList')}'>Users List</a>
                                </li>
                                <li>
                                    <a href='${createLink(controller: 'topic', action: 'topicList')}'>Topics List</a>
                                </li>
                            </ls:isAdminLoggedIn>
                            <li role="separator" class="divider"></li>
                            <li><g:link controller="login" action="logout">Logout</g:link></li>
                        </ul>
                    </li>
                </ls:isLoggedIn>

            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>