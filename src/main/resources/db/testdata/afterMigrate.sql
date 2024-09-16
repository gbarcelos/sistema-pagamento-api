set foreign_key_checks = 0;

lock tables usuario write, usuario_formas_pagamento write, restaurante write,
restaurante_formas_pagamento write;

delete from usuario;
delete from usuario_formas_pagamento;
delete from restaurante;
delete from restaurante_formas_pagamento;

set foreign_key_checks = 1;

alter table usuario auto_increment = 1;
alter table usuario_formas_pagamento auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table restaurante_formas_pagamento auto_increment = 1;

insert into restaurante (id, nome) values
(1, 'Java Steakhouse'), (2, 'Lanchonete do Tio Sam');

insert into restaurante_formas_pagamento (restaurante_id, formas_pagamento) values
(1, 'VISA'), (1, 'MASTER'), (1, 'ELO'), (1, 'MAQUINA'), (1, 'DINHEIRO'),
(2, 'ELO'), (2, 'MAQUINA'), (2, 'DINHEIRO');

insert into usuario (id, email , possivel_fraudador) values
(1, 'joao@test.com', 0),
(2, 'carlos@test.com', 0),
(3, 'gustavo@test.com', 0),
(4, 'jose@test.com', 1),
(5, 'geraldo@test.com', 1),
(6, 'fabio@test.com', 1);

insert into usuario_formas_pagamento (usuario_id, formas_pagamento) values
(1, 'VISA'), (1, 'MASTER'), (1, 'ELO'),
(2, 'ELO'),
(3, 'VISA'), (3, 'MASTER'), (3, 'ELO'), (3, 'DINHEIRO'),
(4, 'VISA'), (4, 'MASTER'), (4, 'ELO'), (4, 'MAQUINA'),
(5, 'VISA'), (5, 'MASTER'), (5, 'ELO'), (5, 'DINHEIRO'), (5, 'MAQUINA'),
(6, 'VISA'), (6, 'MASTER'), (6, 'ELO'), (5, 'DINHEIRO');

unlock tables;