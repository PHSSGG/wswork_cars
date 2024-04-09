## Informações sobre os endpoints

### Carros
#### GET
* ``/cars.json`` <br>
Retorna todos os carros (com o formato requerido pelo teste)
<br><br>
* ``/carros`` <br>
Retorna todos os carros (formato padrão)
  <br><br>
* ``/getCarro/{id}`` <br>
Faz uma query do carro pelo id e retorna o objeto em Json
<br><br>
* ``/getCarroByTimestampCadastro`` <br>
Faz uma query do carro pelo timestamp do cadastro e retorna o objeto em Json
<br><br>
* ``/getCarrosByCor/{cor}`` <br>
Faz uma query dos carros pela cor e retorna o Json da lista de carros com a cor informada
<br><br>
* ``/getCarrosByCombustivel/{combustivel}`` <br>
Faz uma query dos carros pelo combustível e retorna o Json da lista de carros que usa o combustível informado
<br><br>

* ``/getCarrosByNumPortas/{numPortas}`` <br>
Faz uma query dos carros pela quantidade de portas e retorna o Json da lista de carros
<br><br>

* ``/getCarrosByAno/{ano}`` <br>
Faz uma query dos carros pelo ano e retorna o Json da lista de carros
<br><br>

* ``/getCarrosByModeloId/{modeloId}`` <br>
  Faz uma query dos carros pelo id do modelo e retorna o Json da lista de carros
<br><br>

#### POST
* ``/addCarro`` <br>
  Cria um novo carro e retorna o objeto em json na requisição. <br>
Parâmetros: <br>
**modeloId** - O id do modelo do carro <br>
**ano** - Ano do carro <br>
**combustivel** - O tipo de combustível do carro <br>
**numPortas** - A quantidade de portas que o carro tem <br>
**cor** - A cor do carro <br><br>

* ``/addCarroJson`` <br>
Cria um novo carro e retorna o objeto em json na requisição. <br>
Inclua o Json do carro no body. O formato do json é:
````
{
  "timeStampcadastro":0,
  "modelo":
     {
       "id":0,
       "marca":
          {
            "id":0,
            "nome":"marca_name"
          },
       "nome":"modelo_name",
       "valor_fipe":0.0
     },
  "ano":"ano",
  "combustivel":"combustivel",
  "numPortas":4,
  "cor":"cor"
}
````
* ``/addCarros`` <br>
Recebe um json de uma lista de carros no body, cria os carros da lista e retorna um json da lista de carros criados
<br><br>

#### PUT
* ``/updateCarro`` <br>
Recebe o json do objeto do carro no body e atualiza o existente na database com as novas informações
<br><br>

#### DELETE
* ``/deleteCarro/{id}`` <br>
Deleta o carro com o id informado
<br><br>
### Marca
#### GET
* ``/marcas`` <br>
Retorna todas as marcas
<br><br>
* ``/getMarca/{id}`` <br>
Faz uma query da marca pelo id e retorna o objeto em Json
<br><br>
* ``/getMarcaByName/{name}`` <br>
Faz uma query da marca pelo nome e retorna o objeto em Json
<br><br>
#### POST
* ``/addMarca`` <br>
Cria uma nova marca e retorna o objeto criado em Json. Você deve fornecer o paramêtro do "**nome**" da marca.
<br><br>
* ``/addMarcaJson`` <br>
Cria uma nova marca e retorna o objeto em json na requisição. <br>
Inclua o Json da marca no body. O formato do json é:
````
{
  "id":0,
  "nome":"marca_name"
}
````
* ``/addMarcas`` <br>
Recebe o json da lista de marcas e cria todas as marcas informadas
<br><br>
#### PUT
* ``/updateMarca`` <br>
Atualiza a marca com as informações passadas
<br><br>
#### DELETE
* ``/deleteMarca/{id}`` <br>
Deleta a marca com o id informado
<br><br>
### Modelos
#### GET
* ``/modelos`` <br>
Retorna a lista de modelos
<br><br>
* ``/getModelo/{id}`` <br>
Faz uma query do modelo pelo id e retorna o Json do objeto
<br><br>
* ``/getModeloByName/{name}`` <br>
Faz uma query do modelo pelo nome e retorna o Json do objeto
<br><br>
* ``/getModelosByMarca/{marcaId}`` <br>
Faz uma query dos modelos pela marca e retorna o Json de uma lista com os objetos
<br><br>
#### POST
* ``/addModelo`` <br>
  Cria uma nova marca e retorna o objeto criado em Json. Você deve fornecer o paramêtros: <br>
**marcaId** - Id da marca <br>
**nome** - O nome do modelo <br>
**valor_fipe)** - O valor fipe do modelo
<br><br>
* ``/addModeloJson`` <br>
Cria um novo modelo e retorna o objeto em json na requisição. <br>
Inclua o Json do modelo no body. O formato do json é:
````
{
 "id":0,
 "marca":
   {
     "id":0,
     "nome":"marca_name"
   },
 "nome":"modelo_name",
 "valor_fipe":0.0
}
````
* ``/addModelos`` <br>
Recebe o json da lista de modelos e cria todos os modelos informados
<br><br>
#### PUT
* ``/updateModelo`` <br>
Atualiza o modelo com as informações passadas
<br><br>
#### DELETE
* ``/deleteModelo/{id}`` <br>
Deleta o modelo pelo id