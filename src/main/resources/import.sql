INSERT INTO album.images (title, visible, description, url)VALUES('spiaggia', 1, 'Una spiaggia nella parte sud-ovest della Grecia', 'https://picsum.photos/id/12/1920/1080');
INSERT INTO album.images (title, visible, description, url)VALUES('cascata', 0, 'Una cascata nella parte nord-ovest dell\'Italia', 'https://picsum.photos/id/15/1920/1080');
INSERT INTO album.images (title, visible, description, url)VALUES('mare', 1, 'Un mare nella parte nord-est dell\'Italia', 'https://picsum.photos/id/16/1920/1080');

INSERT INTO album.images (title, visible, description, url)VALUES('bosco', 1, 'Un bosco nella parte nord-ovest della Germania', 'https://picsum.photos/id/11/1920/1080');
INSERT INTO album.images (title, visible, description, url)VALUES('prato', 0, 'Un prato nella parte sud-est della Francia', 'https://picsum.photos/id/18/1920/1080');
INSERT INTO album.images (title, visible, description, url)VALUES('sentiero', 1, 'Un sentiero nella parte nord-est della Spagna', 'https://picsum.photos/id/17/1920/1080');

INSERT INTO album.images (title, visible, description, url)VALUES('strada', 1, 'Una strada nella parte sud-est dell\'Austria', 'https://picsum.photos/id/22/1920/1080');
INSERT INTO album.images (title, visible, description, url)VALUES('panchina', 0, 'Una panchina nella parte sud-ovest della Svizzera', 'https://picsum.photos/id/32/1920/1080');
INSERT INTO album.images (title, visible, description, url)VALUES('ponte', 1, 'Un ponte nella parte est dell\'America', 'https://picsum.photos/id/43/1920/1080');

INSERT INTO album.images (title, visible, description, url)VALUES('città bianca', 1, 'Una città bianca nella parte sud della Grecia', 'https://picsum.photos/id/49/1920/1080');
INSERT INTO album.images (title, visible, description, url)VALUES('città urbana', 1, 'Una città urbana nella parte sud-est dell\'America', 'https://picsum.photos/id/57/1920/1080');
INSERT INTO album.images (title, visible, description, url)VALUES('città dall\'alto', 0, '', 'https://picsum.photos/id/61/1920/1080');

INSERT INTO album.categories(name)VALUES('natura');
INSERT INTO album.categories(name)VALUES('mare');
INSERT INTO album.categories(name)VALUES('foresta');
INSERT INTO album.categories(name)VALUES('montagna');
INSERT INTO album.categories(name)VALUES('città');

INSERT INTO album.category_image(image_id, category_id)VALUES(1, 1);
INSERT INTO album.category_image(image_id, category_id)VALUES(1, 2);

INSERT INTO album.category_image(image_id, category_id)VALUES(2, 1);
INSERT INTO album.category_image(image_id, category_id)VALUES(2, 2);

INSERT INTO album.category_image(image_id, category_id)VALUES(3, 1);
INSERT INTO album.category_image(image_id, category_id)VALUES(3, 2);

INSERT INTO album.category_image(image_id, category_id)VALUES(4, 1);
INSERT INTO album.category_image(image_id, category_id)VALUES(4, 3);

INSERT INTO album.category_image(image_id, category_id)VALUES(5, 1);
INSERT INTO album.category_image(image_id, category_id)VALUES(5, 3);

INSERT INTO album.category_image(image_id, category_id)VALUES(6, 1);
INSERT INTO album.category_image(image_id, category_id)VALUES(6, 3);
INSERT INTO album.category_image(image_id, category_id)VALUES(6, 4);

INSERT INTO album.category_image(image_id, category_id)VALUES(7, 5);
INSERT INTO album.category_image(image_id, category_id)VALUES(8, 5);
INSERT INTO album.category_image(image_id, category_id)VALUES(9, 5);
INSERT INTO album.category_image(image_id, category_id)VALUES(10, 5);
INSERT INTO album.category_image(image_id, category_id)VALUES(11, 5);
INSERT INTO album.category_image(image_id, category_id)VALUES(12, 5);

INSERT INTO users (email, first_name, last_name, password) VALUES('admin@gmail.com', 'admin', 'admin', '{noop}admin');

INSERT INTO roles (id, name) VALUES(1, 'ADMIN');

INSERT into users_roles(user_id, roles_id) VALUES(1, 1);


INSERT INTO album.messages(email, `text`)VALUES('leo@gmail.com', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore eum beatae dicta, explicabo molestiae quod.');
INSERT INTO album.messages(email, `text`)VALUES('leo@gmail.com', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore eum beatae dicta, explicabo molestiae quod.');
INSERT INTO album.messages(email, `text`)VALUES('leo@gmail.com', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore eum beatae dicta, explicabo molestiae quod.');
INSERT INTO album.messages(email, `text`)VALUES('leo@gmail.com', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore eum beatae dicta, explicabo molestiae quod.');
INSERT INTO album.messages(email, `text`)VALUES('leo@gmail.com', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore eum beatae dicta, explicabo molestiae quod.');
