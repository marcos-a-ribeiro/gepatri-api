--------------------------------------------------------------------
<br>API REST - Sistema Gerenciamento de Patrimônios
<br>--------------------------------------------------------------------
<br>Endpoints (Patrimonios):
<br>
<br>• GET patrimonios - Obter todos os patrimônios.
<br>

<br>• GET patrimonios/page - Retorna uma lista paginada.
<br>	Parametros:
<br>		page		: número da página solicitada (inicia em zero).
<br>		linesPerPage: quantidade de linhas por página (default = 12).
<br>		orderBy		: campo utilizado para ordenação (default = nome).
<br>		direction	: ascendente(ASC) ou descendente(DESC) (default = ASC).
<br>		nome		: retorna os patrímonios cujos nomes contenham o respectivo parâmetro (default *).
<br>

<br>• GET patrimonios/{id} - Obter um patrimônio por ID.
<br>	Parametros:
<br>		id			: Id do patrimônio (path variable).
<br>

<br>• POST patrimonios - Inserir um novo patrimônio.
<br>	Parametros:
<br>		nome 		: Nome do patrimonio. (não pode ser vazio, deve ter entre 2 e 5 caracteres)
<br>		descricao	: Descrição.
<br>		marcaId		: Id da marca.
<br>

<br>• PUT patrimonios/{id} - Alterar os dados de um patrimônio.
<br>	Parametros:
<br>		id			: Id do patrimônio (path variable).
<br>		nome 		: Nome do patrimônio. (não pode ser vazio, deve ter entre 2 e 5 caracteres)
<br>		descricao	: Descrição.
<br>		marcaId		: Id da marca.
<br>

<br>• DELETE patrimonios/{id} - Excluir um patrimônio.
<br>	Parametros:
<br>		id			: Id do patrimônio (path variable).
<br>

<br>• UPLOAD patrimonios/upload/{id} - Fazer o upload de um documento para um determinado patrimônio.
<br>	Parametros:
<br>		id			: Id do patrimônio (path variable).
<br>    multipart/form-data
<br>

<br>Endpoints (Marcas):
<br>
<br>• GET marcas - Obter todas as marcas
<br>

<br>• GET marcas/page - Retorna uma lista paginada das marcas que atendam ao critério de pesquisa (nome).
<br>	Parametros:
<br>		page		: número da página solicitada (inicia em zero).
<br>		linesPerPage: quantidade de linhas por página (default = 12).
<br>		orderBy		: campo utilizado para ordenação (default = nome).
<br>		direction	: ascendente(ASC) ou descendente(DESC) (default = ASC).
<br>		nome		: retorna as marcas cujos nomes contenham o respectivo parâmetro (default *).
<br>

<br>• GET marcas/{id} - Obter uma marca por ID.
<br>	Parametros:
<br>		id			: Id da marca (path variable).
<br>

<br>• GET marcas/{id}/patrimonios – Obter todos os patrimônios de uma marca.
<br>	Parametros:
<br>		id			: Id da marca (path variable).
<br>

<br>• POST marcas - Inserir uma nova marca.
<br>	Parametros:
<br>		nome 		: Nome da marca. (não pode ser vazio, deve ter entre 2 e 5 caracteres, não permite duplicidade)
<br>

<br>• PUT marca/{id} - Alterar os dados de uma marca.
<br>	Parametros:
<br>		id			: Id da marca (path variable).
<br>		nome 		: Nome da marca. (não pode ser vazio, deve ter entre 2 e 5 caracteres, não permite duplicidade)
<br>

<br>• DELETE marca/{id} - Excluir uma marca.
<br>	Parametros:
<br>		id			: Id da marca (path variable).
<br>
