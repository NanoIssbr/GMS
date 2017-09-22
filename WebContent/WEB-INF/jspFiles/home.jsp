<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="iso-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/ico" href="image/favicon.ico">
<title>IT ACQUISITION SYSTEM</title>
<meta charset="utf-8" />
<script src="././js/jquery.js"></script>
<script src="././js/bootstrap.min.js"></script>
<script src="././js/index.js"></script>
<link rel="stylesheet" href="././style/bootstrap.min.css" />
<link href="././style/simple-sidebar.css" rel="stylesheet">
<link rel="stylesheet" href="././style/animate.css" />
<link rel="stylesheet" href="././style/index.css" />
<link rel="stylesheet" href="././style/style.css" />
<style>
.modal-header, h4, .close {
	background-color: #5cb85c;
	color: white !important;
	text-align: center;
	font-size: 30px;
}

.modal-footer {
	background-color: #f9f9f9;
}
</style>
</head>
<body>
	<div id="fullscreen_bg" class="fullscreen_bg"></div>
	<div class="header col-md-12">
		<p class="deptitle col-md-11"
			style="text-align: center; color: #FFFFFF">
			<br /> <strong>GOLDEN MEDIA SAT<br />Gestion de stock<br />
			<c:out value="${sessionScope._isSessionU.getUserName()}"></c:out>
			</strong>
		</p>
	</div>
	<!-- sideBarre begin -->
	<div id="sidebar-wrapper">
		<ul class="sidebar-nav">
			<li id="nr" style="color: white; cursor: pointer"
				class="sidebar-brand">Produit</li>
			<li id="lel"><a href="#"> Ajouter Produit </a></li>
			<li id="dtel"><a href="#"> Modifier Produit </a></li>
			<li id="h" style="color: white; cursor: pointer"
				class="sidebar-brand">Visualisation du Stock
				<p class="enum"></p>
			</li>
			<!-- show this link only for the admin xor the it team -->
			<li id="adm" style="color: white; cursor: pointer"
				class="sidebar-brand">Serveurs</li>
			<li id="adm1"><a href="#"> &nbsp;&nbsp;&nbsp;Ajout
					Abonnement </a></li>
			<li class="sidebar-brand"><a href="logOut" style="color: red;">
					Logout </a></li>
		</ul>
	</div>
	<br />
	<br />
	<!-- sideBarre end -->
	<br />
	<br />
	<div class="forms col-md-10">
		<kbd class="">
			<label class=""> Derniers abonnements </label>
		</kbd>
		<div id="dAbbon"></div>

		<!-- ********************************************************** Dernier abonnement ********************************************* -->

		<br />
		<c:choose>
			<c:when test="${ requestScope.abonnements != null }">
				<table class="table table-hover table-bordered"
					style="border-collapse: collapse; color: white;">
					<thead>
						<tr>
							<th>Client</th>
							<th>Date Abonnement</th>
							<th>Etat</th>
						</tr>
					</thead>
					<c:forEach items="${ requestScope.abonnements }" var="abon">

						<tr>
							<td><c:out value="${ abon.getClient() }"></c:out></td>
							<td><c:out value="${ abon.getDateAbonnement() }"></c:out></td>
							<c:choose>
								<c:when test="${ abon.isEtat() }">
									<td><span class="open-blink"
										style="background-color: red;"></span></td>
								</c:when>
								<c:otherwise>
									<td><span class="open-blink"
										style="background-color: green;"></span></td>
								</c:otherwise>
							</c:choose>
						</tr>

					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<c:out value="Aucun Abonnement !" />
			</c:otherwise>
		</c:choose>
		<br />
		<br />


		<kbd class="">
			<label class=""> Produits Manquants </label>
		</kbd>
		<div id="pManq">
			<c:choose>
				<c:when test="${ requestScope.prods != null }">
					<table class="table table-hover table-bordered"
						style="border-collapse: collapse; color: white;">
						<thead>
							<tr>
								<th>Libelle</th>
								<th>Prix</th>
								<th>Reference</th>
								<th>Etat</th>
							</tr>
						</thead>
						<c:forEach items="${ requestScope.prods }" var="prod">

							<tr>
								<td><c:out value="${ prod.getLibelleProduct() }"></c:out></td>
								<td><c:out value="${ prod.getPrize() }"></c:out></td>
								<td><c:out value="${ prod.getReferences() }"></c:out></td>
								<td><span class="open-blink" style="background-color: red;"></span></td>
							</tr>

						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<c:out value="Aucun produit manquant !" />
				</c:otherwise>
			</c:choose>

		</div>


		<!-- ********************************************************** Produits Manquant ********************************************* -->

	</div>
	<br />





	<!-- modal of new Product -->
	<div class="container">
		<div class="modal fade" id="myModal" role="dialog">
			<div style="width: 600px;" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header"
						style="padding: 35px 50px; background-color: black;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<p id="elem" style="color: white; font-size: 18px;">Ajout
							produit</p>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">
						<form action="newProduct" id="newPrd" method="post">
							<label for="libelleProduct">Libelle produit</label> <input
								type="text" name="libelleProduct" class="form-control"
								id="libelleProduct" required="required" autofocus /> <label
								for="qProduct">Quantité</label> <input type="number"
								name="qProduct" class="form-control" id="qProduct" min="0"
								required="required" /> <label for="qProduct">Réference</label> <input
								type="text" name="rProduct" class="form-control" id="rProduct"
								required="required" /> <label for="pProduct">Prix
								Produit</label> <input type="number" name="pProduct"
								class="form-control" min="0" id="pProduct" required="required" />
							<br /> <input type="submit" name="submitNewProd"
								class="btn btn-danger" value="Submit" /> <br />
							<br />
							<div class="alert alert-success alert-dismissible" style="display: none;" role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span style="color: black;" aria-hidden="true">&times;</span>
								</button>
								<strong>Info!</strong> <span id="infoMessage"></span>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- modal of new Abonnement -->
	<div class="container">
		<div class="modal fade" id="modalAbonnement" role="dialog">
			<div style="width: 600px;" class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header"
						style="padding: 35px 50px; background-color: black;">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<p id="elem" style="color: white; font-size: 18px;">Ajout
							Abonnement</p>
					</div>
					<div class="modal-body" style="padding: 40px 50px;">
						<form action="newAbonnement" id="newAbn" method="post">
							<label for="libelleProduct">Nom Client</label> 
							<input type="text" name="nClient" class="form-control" id="nClient" required="required" autofocus /> 
							<label for="qProduct">Date Abonnement</label>
							<input type="date" name="dAbonnement" class="form-control" id="dAbonnement" required="required" /> 
							<br /> 
							<input type="submit" name="submitNewAbo" class="btn btn-danger" value="Submit" /> 
							<br />
							<br />
							<div class="alert alert-success alert-dismissible" style="display: none;" role="alert">
								<button type="button" class="close" data-dismiss="alert" aria-label="Close">
									<span style="color: black;" aria-hidden="true">&times;</span>
								</button>
								<strong>Info!</strong> <span id="infoMessageABo"></span>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>