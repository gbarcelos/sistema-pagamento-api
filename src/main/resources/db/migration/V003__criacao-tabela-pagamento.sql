create table pagamento (
	id bigint not null auto_increment,
	usuario_id bigint not null,
	restaurante_id bigint not null,
  valor decimal(10,2) not null,
	status varchar(255) not null,
	forma_pagamento varchar(255) not null,
	data_hora_criacao datetime not null,
	primary key (id)
) engine=InnoDB default charset=UTF8MB4;

alter table pagamento add constraint fk_pagamento_usuario
foreign key (usuario_id) references usuario (id);

alter table pagamento add constraint fk_pagamento_restaurante
foreign key (restaurante_id) references restaurante (id);

