create table usuario (
	id bigint not null auto_increment,
	email varchar(255) not null,
	primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table usuario_formas_pagamento (
	usuario_id bigint not null,
	formas_pagamento varchar(255)
) engine=InnoDB default charset=UTF8MB4;

alter table usuario_formas_pagamento add constraint fk_usu_forma_pagto_usu
foreign key (usuario_id) references usuario (id);

create table restaurante (
	id bigint not null auto_increment,
	nome varchar(255) not null,
	primary key (id)
) engine=InnoDB default charset=UTF8MB4;

create table restaurante_formas_pagamento (
	restaurante_id bigint not null,
	formas_pagamento varchar(255)
) engine=InnoDB default charset=UTF8MB4;

alter table restaurante_formas_pagamento add constraint fk_rest_forma_pagto_restaurante
foreign key (restaurante_id) references restaurante (id);

