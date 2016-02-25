<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<title>Quantidade de Acessos | Curso Web</title>

		<!-- Font Awesome 4.3.0 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css" type="text/css" />

		<!-- Materialize v0.97.5 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/materialize/css/materialize.min.css" type="text/css" />
	</head>

	<body class="teal lighten-2">
		<div class="container">
			<div class="row">
				<div class="col l12">
					<div class="card">
						<div class="card-content center">
							<table>
								<thead>
									<tr>
										<th>Página</th>
										<th>Quantidade de Acessos</th>
									</tr>
								</thead>
								<tbody>
									<jsp:useBean class="br.com.fa7.cursoweb.dao.PageDao" id="pageDao" scope="application" />
									<c:forEach var="page" items="${pageDao.pages}">
										<tr>
											<td>${page.key}</td>
											<td>${page.value}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>