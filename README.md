--------------------------------------------------------------------
API REST - Sistema Gerenciamento de Patrimônios
--------------------------------------------------------------------
Endpoints (Patrimonios):

• GET patrimonios - Obter todos os patrimônios.

• GET patrimonios/page - Retorna uma lista paginada.
	Parametros:
		page		: número da página solicitada (inicia em zero).
		linesPerPage: quantidade de linhas por página (default = 12).
		orderBy		: campo utilizado para ordenação (default = nome).
		direction	: ascendente(ASC) ou descendente(DESC) (default = ASC).
		nome		: retorna os patrímonios cujos nomes contenham o respectivo parâmetro (default *).

• GET patrimonios/{id} - Obter um patrimônio por ID.
	Parametros:
		id			: Id do patrimônio (path variable).

• POST patrimonios - Inserir um novo patrimônio.
	Parametros:
		nome 		: Nome do patrimonio.
		descricao	: Descrição.
		marcaId		: Id da marca.

• PUT patrimonios/{id} - Alterar os dados de um patrimônio.
	Parametros:
		id			: Id do patrimônio (path variable).
		nome 		: Nome do patrimônio.
		descricao	: Descrição.
		marcaId		: Id da marca.

• DELETE patrimonios/{id} - Excluir um patrimônio.
	Parametros:
		id			: Id do patrimônio (path variable).

Endpoints (Marcas):

• GET marcas - Obter todas as marcas

• GET marcas/page - Retorna uma lista paginada das marcas que atendam ao critério de pesquisa (nome).
	Parametros:
		page		: número da página solicitada (inicia em zero).
		linesPerPage: quantidade de linhas por página (default = 12).
		orderBy		: campo utilizado para ordenação (default = nome).
		direction	: ascendente(ASC) ou descendente(DESC) (default = ASC).
		nome		: retorna as marcas cujos nomes contenham o respectivo parâmetro (default *).
		
• GET marcas/{id} - Obter uma marca por ID.
	Parametros:
		id			: Id da marca (path variable).

• GET marcas/{id}/patrimonios – Obter todos os patrimônios de uma marca.
	Parametros:
		id			: Id da marca (path variable).

• POST marcas - Inserir uma nova marca.
	Parametros:
		nome 		: Nome da marca.

• PUT marca/{id} - Alterar os dados de uma marca.
	Parametros:
		id			: Id da marca (path variable).
		nome 		: Nome da marca.
		
• DELETE marca/{id} - Excluir uma marca.
	Parametros:
		id			: Id da marca (path variable).
