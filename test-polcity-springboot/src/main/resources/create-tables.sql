create table documenti(
	id_protocollo SERIAL PRIMARY KEY,
	anno_protocollo INT NOT NULL, 
	testo TEXT NOT NULL CHECK (char_length(testo) > 0)
);

create table persone(
	id_persona SERIAL PRIMARY KEY,
	nome VARCHAR (50) NOT NULL CHECK (char_length(nome) > 0),
	cognome VARCHAR (50) NOT NULL CHECK (char_length(cognome) > 0),
	data_nascita DATE NOT NULL,
	sesso CHAR(1) NOT NULL CHECK (sesso = 'M' OR sesso = 'F'),
	luogo_nascita VARCHAR (50) NOT NULL CHECK (char_length(luogo_nascita) > 0),
	CONSTRAINT check_persone_unique UNIQUE(nome, cognome, data_nascita, sesso, luogo_nascita)
);

create table documenti_persone(
	id_protocollo INT NOT NULL REFERENCES documenti(id_protocollo),
	id_persona INT NOT NULL REFERENCES persone(id_persona),
	CONSTRAINT documenti_persone_pkey PRIMARY KEY (id_protocollo, id_persona)
);
