<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Cadastro de Cliente TDV</title>
	<!-- Bootstrap -->
    <link href="${ pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet">


</head>
<body class="container">
	
	
	<hr>
	<h1>Cadastro de Clientes TDV</h1>

	<a class="btn btn-success" href="novoCliente">Cadastrar Novo</a>
	<a class="btn btn-info" href="listarCliente">Listar Clientes</a>
	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#myModal">Busca por filtros</button>
	
	<br><br>
	<div style="width:100%; height:300px; overflow: auto;" class="table-responsive">
	
	<table class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Cpf</th>
				<th>Peso</th>
				<th>Altura</th>
				<th>Imc</th>
				<th>Ação</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="cliente" items="${listaCliente}">
				<tr class="cliente">
					<td class="info-id">${cliente.id}</td>
					<td class="info-nome">${cliente.nome}</td>
					<td class="info-cpf">${cliente.cpf}</td>
					<td class="info-peso">${cliente.peso}</td>
					<td class="info-altura">${cliente.altura}</td>
					<td class="info-imc"></td>
					<td>
					 <a href="editarCliente?id=${cliente.id}">Editar</a>
					 <a href="deletarCliente?id=${cliente.id}">Deletar</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	</div>
	
	<hr>
	<br>
	
	 <h1>${msg}</h1>

	<br>	
	<hr>


	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Busca Parametrizada</h4>
				</div>
				<div class="modal-body">

					<form action="listarCliFiltrado" method="get" class="form-inline">
						
						<div class="form-group">
						
						<label for="exampleInputEmail1">Tipo de filtro.:</label>
						
						<select required="required" class="form-control" name="filtro">
						<c:forEach var="tipo" items="${listatipobusca}">
						
						 	<option value="${tipo.codigo}">${tipo.descricao}</option>
						 
						</c:forEach>
						</select>
						</div>
						<br>
						<br>


						<div class="form-group">
							<label for="exampleInputEmail1">Filtro.:</label> 
							<input required="required" type="text" name="busca" class="form-control" placeholder="filtro">
						</div>
						
						<br>
						<br>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
							<button type="submit" class="btn btn-primary">Executar busca</button>
						</div>

					</form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${ pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>	
    <script src="${ pageContext.request.contextPath }/resources/js/calcula-imc.js"></script>

</body>
</html>
