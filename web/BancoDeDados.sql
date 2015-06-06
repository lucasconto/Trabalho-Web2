--Nome da tabela 

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
    endereco varchar(50),
    endnumero varchar(10),
    endcomplemento varchar(20),
    bairro varchar(30),
    cidade varchar(30),
    estado varchar(30),
    inativo int
);

Create table Produto(
    idProduto serial,
    titulo varchar(100),
    autor varchar(50),
    idEditora int,
    categoria varchar(30),
    preco real,
    idImg int
);


Create table Editora(
    idEditora serial,
    nome varchar(100)
);

Create table Genero(
    idGenero serial,
    nome varchar(100)
);
