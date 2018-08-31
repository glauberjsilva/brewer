$(function() {
	
	var modal = $('#modalCadastroRapidoEstilo');
	var botaoSalvar = modal.find('.js-modal-cadastro-estilo-salvar-btn');
	var form = modal.find('form');
	form.on('submit', function(event) { event.preventDefault() }); //Não permite submeter ao teclar o Enter
	var url = form.attr('action');
	var inputNomeEstilo = $('#nomeEstilo');
	var containerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
	
	modal.on('shown.bs.modal', onModalShow);
	modal.on('hide.bs.modal', onModalClose);
	botaoSalvar.on('click', onBotaoSalvar)
	
	function onModalShow() {
		inputNomeEstilo.focus();
	}
	
	function onModalClose() {
		inputNomeEstilo.val('');
		containerMensagemErro.addClass('hidden');
		form.find('.form-group').removeClass('has-error')
	}
	
	function onBotaoSalvar() {
		var nomeEstilo = inputNomeEstilo.val().trim();
		$.ajax({
			url:url,
			method: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({ nome: nomeEstilo }),
			error: onErrorSalvandoEstilo,
			success: onEstiloSalvo
		})
	}
	
	function onErrorSalvandoEstilo(obj) {
		var mensagemErro = obj.responseText;
		containerMensagemErro.removeClass('hidden');
		containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		form.find('.form-group').addClass('has-error');
	}
	
	function onEstiloSalvo(estilo) {
		console.log(arguments);
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome +'</option>');
		comboEstilo.val(estilo.codigo);
		modal.modal('hide');
	}
});