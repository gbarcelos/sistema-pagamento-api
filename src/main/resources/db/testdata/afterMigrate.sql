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
(1, 'VISA'), (1, 'MASTER'), (1, 'ELO'), (2, 'ELO');

insert into usuario (id, email) values
(1, 'joao@test.com'),
(2, 'carlos@test.com'),
(3, 'gustavo@test.com');

insert into usuario_formas_pagamento (usuario_id, formas_pagamento) values
(1, 'VISA'), (1, 'MASTER'), (1, 'ELO'), (2, 'ELO'), (3, 'VISA'), (3, 'MASTER'), (3, 'ELO'), (3, 'DINHEIRO');

unlock tables;