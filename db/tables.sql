begin;

create table usuario (
	codigo SERIAL PRIMARY KEY,
	nome VARCHAR,
	email VARCHAR UNIQUE,
	senha VARCHAR,
	sobre VARCHAR,
	img_url VARCHAR
);

CREATE TABLE imagem (

	codigo SERIAL PRIMARY KEY,
	titulo VARCHAR,
	descricao VARCHAR,
	numeroacessos INTEGER,
	usuario_codigo INTEGER,
	url VARCHAR,
	FOREIGN KEY (usuario_codigo) REFERENCES usuario(codigo)

);

select * from imagem;

commit;