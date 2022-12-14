<header id="header" class="d-flex align-items-center">
    <div class="container d-flex align-items-center justify-content-between">
        <h1 class="logo">
            <a class="nav-link scrollto" href="/">whereIsMyHome<span>.</span></a>
        </h1>

        <nav id="navbar" class="navbar">
            <ul>
                <li class="nav-item">
                    <a class="nav-link" href="#"
                       onclick="window.open('${root}/webcam/cameraWeb.html', '_blank', 'toolbar=yes,scrollbars=yes,resizable=yes,top=50,left=500,width=750,height=400');">#Selfie</a>
                </li>
                <li><a class="nav-link scrollto" href="${root}/house/deal">Deal Info</a></li>

                <li><a class="nav-link scrollto" href="#portfolio">Company</a></li>
                <c:if test="${member eq null}">
                    <li><a class="nav-link scrollto" href="${root}/user/login"
                           onclick="window.open(this.href, '_blank', 'width=600 height=800'); return false">Login</a>
                    <li>
                        <a class="nav-link scrollto" href="${root}/user/register"
                           onclick="window.open(this.href, '_blank', 'width=600 height=800'); return false">Register
                        </a>
                    </li>
                </c:if>
                <c:if test="${member ne null  }">
                <li><a class="nav-link scrollto" id="logoutBtn" href="#">Logout</a></li>
                <li><a class="nav-link scrollto" href="/board/selectall">Board</a></li>
                <li><a class="nav-link scrollto" href="${root}/user/info"
                       onclick="window.open(this.href, '_blank', 'width=600 height=800'); return false">info</a>
                <li>
                    </c:if>
            </ul>
        </nav>
    </div>
</header>
<script>
    document.querySelector("#logoutBtn").addEventListener("click", async () => {
        await fetch("${root}/user/logout", {method: "GET"});
        window.location.reload();
    })
</script>