var trsPacientes = document.getElementsByClassName("cliente");

	for(var posicaoaltual = 0; posicaoaltual <= trsPacientes.length - 1; posicaoaltual++){

	var pacientetr = trsPacientes[posicaoaltual];

	var tdNome   = pacientetr.getElementsByClassName("info-nome")[0];
	var tdCpf    = pacientetr.getElementsByClassName("info-cpf")[0];
	var tdPeso   = pacientetr.getElementsByClassName("info-peso")[0];
	var tdAltura = pacientetr.getElementsByClassName("info-altura")[0];
	var tdImc    = pacientetr.getElementsByClassName("info-imc")[0];

	var paciente = {nome: tdNome.textContent, peso: tdPeso.textContent, altura: tdAltura.textContent}



	if(paciente.altura != 0)
	{
		var imc = paciente.peso / (paciente.altura * paciente.altura); // 42
		
		var tdImc = pacientetr.getElementsByClassName("info-imc")[0];
		tdImc.textContent = imc;

		console.log(imc);

	}
	else
	{
		console.log("não executei pois altira eh igual a zero."); 

	}
}
