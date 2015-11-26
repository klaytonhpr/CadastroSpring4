<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
	<title>Novo cliente</title>
	<!-- Bootstrap -->
    <link href="${ pageContext.request.contextPath }/resources/css/bootstrap.min.css" rel="stylesheet">


</head>
<body class="container">
	
	
	<hr>
	<h1>Cadastro de novo cliente</h1>
	<hr>
	<form action="addCliente" method="post" class="form-inline">
	  

	  <input type="hidden" name="id" value="${cliente.id}">
	  
	  <div class="form-group">
	      <label for="exampleInputEmail1">Nome.:</label> 
	      <input required="required" type="text" name="nome" class="form-control" placeholder="Nome" value="${cliente.nome}">
	  </div>
	  
	  <div class="form-group">
	      <label for="exampleInputEmail1">Cpf.:</label> 
	      <input required="required" type="number" name="cpf" class="form-control" placeholder="Cpf" value="${cliente.cpf}">
	  </div>
	  
	  <div class="form-group">
	      <label for="exampleInputEmail1">Peso.:</label> 
	      <input required="required" type="text" name="peso" class="form-control" placeholder="peso" value="${cliente.peso}">
	  </div>
	  
	  <div class="form-group">
	      <label for="exampleInputEmail1">Altura.:</label> 
	      <input required="required" type="text" name="altura" class="form-control" placeholder="altura" value="${cliente.altura}">
	  </div>
	 
	  <br>
 	  <div class="checkbox">
		 	<label> 
		 	 <input type="checkbox"> Marcar data de cadastro
		 	</label>
	  </div>

	  <br>
	  <br>
	  <label>Data Cadastro.:</label>
	  <INPUT type="date" name="date">
	  <br>
	  <br>
	  <button type="submit" class="btn btn-success">Salvar Cliente</button>
	  <a class="btn btn-default" href="${pageContext.request.contextPath}/">Cancelar</a>
	</form>
	<hr>
	<br>
	
	 <h1>${msg}</h1>

	<br>	
	<hr>	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${ pageContext.request.contextPath }/resources/js/bootstrap.min.js"></script>	
    <script src="${ pageContext.request.contextPath }/resources/js/calcula-imc.js"></script>

</body>
</html>
