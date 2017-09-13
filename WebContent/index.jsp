<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<c:if test="${sessionScope._isSessionU != null }">
<c:redirect url="/logIn"></c:redirect>
</c:if>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Page d'Authentification</title>
<meta charset="utf-8" />
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="style/bootstrap.min.css" />
<link rel="stylesheet" href="style/style.css" />
<!-- link rel="icon" type="image/ico" href="image/favicon.ico" -->

</head>
<body onload="$('#myModal').modal();">
	<div id="fullscreen_bg" class="fullscreen_bg"></div>
	<div class="container">
		<div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="false">
			<div style="width: 600px;" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" style="padding: 35px 50px; background-color: #111111;">
						<p style="color: black; font-size: 80px;">
							<div style='text-align: center; font-size: 18px; font-weight: bold'>GOLDEN MEDIA SAT</div>
					</div>

					<div class="modal-body" style="padding: 40px 50px;">


						<div
							style="padding: 30px; padding-top: 0px; font-weight: bold; text-align: center">Tapez votre UserName et votre mot de passe</div>


						<form style="display: inline;"  action="logIn" method="post">
							<select name="userName" id="userName">
							 <option>Ali</option>
							 <option>Ismail</option>
							</select>
							<br /> <br />
								<input type="submit" class="btn btn-danger" value="Submit" />
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>