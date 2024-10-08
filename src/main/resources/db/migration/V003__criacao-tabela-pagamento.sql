create table pagamento (
	id bigint not null auto_increment,
	codigo varchar(255) not null,
	pedido_id bigint not null,
	valor decimal(38,2) not null,
	cliente_id bigint not null,
	restaurante_id bigint not null,
	forma_pagamento varchar(255) not null,
	data_hora_pagamento datetime not null,
	primary key (id)
) engine=InnoDB default charset=UTF8MB4;

alter table pagamento add constraint fk_pagamento_usuario
foreign key (cliente_id) references usuario (id);

alter table pagamento add constraint fk_pagamento_restaurante
foreign key (restaurante_id) references restaurante (id);

create table pagamento_transacoes (
	pagamento_id bigint not null,
	codigo varchar(255) not null,
	data_hora_transacao datetime(6) not null,
	status_transacao varchar(255) not null
) engine=InnoDB default charset=UTF8MB4;

alter table pagamento_transacoes add constraint fk_pagamento_transacoes_pagamento
foreign key (pagamento_id) references  pagamento (id);

