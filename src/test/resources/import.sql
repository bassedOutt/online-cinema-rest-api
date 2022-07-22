INSERT INTO movie (id, duration, en_description, en_title, image_url, price, ua_description, ua_title)
VALUES (1, 139,
        'An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.',
        'Fight Club',
        'https://m.media-amazon.com/images/M/MV5BMmEzNTkxYjQtZTc0MC00YTVjLTg5ZTEtZWMwOWVlYzY0NWIwXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg',
        132,
        'Офісний працівник, який страждає від безсоння, і миловар, який робить мило, утворюють підпільний бойовий клуб, який переростає в набагато більше.',
        'Бійцівський клуб');
INSERT INTO movie (id, duration, en_description, en_title, image_url, price, ua_description, ua_title)
VALUES (2, 116,
        'Marty McFly, a 17-year-old high school student, is accidentally sent thirty years into the past in a time-traveling DeLorean invented by his close friend, the eccentric scientist Doc Brown.',
        'Back to the Future',
        'https://m.media-amazon.com/images/M/MV5BZmU0M2Y1OGUtZjIxNi00ZjBkLTg1MjgtOWIyNThiZWIwYjRiXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_.jpg',
        145,
        'Марті Макфлай, 17-річний старшокласник, випадково потрапляє на тридцять років у минуле на подорожі в часі DeLorean, яку винайшов його близький друг, ексцентричний учений Док Браун.',
        'Назад у майбутнє');
INSERT INTO movie (id, duration, en_description, en_title, image_url, price, ua_description, ua_title)
VALUES (3, 119,
        'When an unconfident young woman is cursed with an old body by a spiteful witch, her only chance of breaking the spell lies with a self-indulgent yet insecure young wizard and his companions in his legged, walking castle.',
        'Howl''s Castle',
        'https://m.media-amazon.com/images/M/MV5BNmM4YTFmMmItMGE3Yy00MmRkLTlmZGEtMzZlOTQzYjk3MzA2XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg',
        145,
        'Коли злопам’ятна відьма проклинає невпевнену в собі молоду жінку, її єдиний шанс розірвати чари — це поблажливий, але невпевнений молодий чарівник і його супутники в його ходячому замку на ногах.',
        'Ходячий замок');
INSERT INTO movie (id, duration, en_description, en_title, image_url, price, ua_description, ua_title)
VALUES (4, 151,
        'Jack Sparrow races to recover the heart of Davy Jones to avoid enslaving his soul to Jones'' service, as other friends and foes seek the heart for their own agenda as well.',
        'Pirates of the Caribbean: Dead Man''s Chest',
        'https://images.moviesanywhere.com/e2bacb1fa5ab3348037e0ab59218df7a/d54b1be9-5f0f-406c-bbce-6986bb8cf3ba.jpg',
        130,
        'Джек Горобець мчить, щоб повернути серце Дейві Джонса, щоб уникнути поневолення його душі на службу Джонсу, оскільки інші друзі та вороги також шукають серце для власного плану денного.',
        'Пірати Карибського моря: Скриня мерця');
INSERT INTO movie (id, duration, en_description, en_title, image_url, price, ua_description, ua_title)
VALUES (5, 155,
        'Feature adaptation of Frank Herbert''s science fiction novel, about the son of a noble family entrusted with the protection of the most valuable asset and most vital element in the galaxy.',
        'Dune',
        'https://m.media-amazon.com/images/M/MV5BN2FjNmEyNWMtYzM0ZS00NjIyLTg5YzYtYThlMGVjNzE1OGViXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_FMjpg_UX1000_.jpg',
        '158',
        'Художня екранізація науково-фантастичного роману Френка Герберта про сина знатної родини, якій доручено охороняти найцінніший актив і найважливіший елемент у галактиці.',
        'Дюна');

INSERT INTO pricing (id, name, price)
VALUES (1, 'prime time', 15);
INSERT INTO pricing (id, name, price)
VALUES (2, 'weekends', 25);
INSERT INTO pricing (id, name, price)
VALUES (3, 'premiere', 20);

INSERT INTO session (id, start_time, movie_id, pricing_id)
VALUES (1, '2022-04-06 10:00:00', 1, 1);
INSERT INTO session (id, start_time, movie_id, pricing_id)
VALUES (2, '2022-04-06 14:00:00', 1, 1);
INSERT INTO session (id, start_time, movie_id, pricing_id)
VALUES (3, '2022-04-06 18:00:00', 1, 1);
INSERT INTO session (id, start_time, movie_id, pricing_id)
VALUES (4, '2022-04-06 20:00:00', 1, 1);


