--Nome do banco: Web2

-- Criação da tabela Cliente

Create table Cliente(
    idcliente serial,
    nome varchar(50),
    sexo char(1),
    cpf varchar(15),
    nascimento date,
    telefone varchar(16),
    email varchar(50),
    senha varchar(30),
    cep varchar(10),
    endereco varchar(60),
    endnumero varchar(10),
    endcomplemento varchar(20),
    bairro varchar(30),
    cidade varchar(30),
    estado varchar(30),
    inativo boolean,
    perfil int
);

Create table Produto(
    idProduto serial,
    titulo varchar(100),
    autor varchar(50),
    fkEditora int,
    fkgenero int,
    preco real,
    idImg serial,
    inativo boolean
);


Create table Editora(
    idEditora serial,
    nome varchar(100),
    inativo boolean
);

Create table Genero(
    idGenero serial,
    nome varchar(100),
    inativo boolean
);

create table pedido(
    idPedido serial,
    idCliente int,
    valorTotal real,
    data Timestamp,
    idStatusPedido int
);

create table statusPedido(
    idStatusPedido int,
    descricao varchar(100)
);

create table itemPedido(
idItemPedido serial,
idPedido int,
idProduto int,
quantidade int,
valorUnitario real
);
