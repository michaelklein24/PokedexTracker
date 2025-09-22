INSERT INTO pkm_type (name) VALUES
('NORMAL'),
('FIRE'),
('WATER'),
('ELECTRIC'),
('GRASS'),
('ICE'),
('FIGHTING'),
('POISON'),
('GROUND'),
('FLYING'),
('PSYCHIC'),
('BUG'),
('ROCK'),
('GHOST'),
('DRAGON'),
('DARK'),
('STEEL'),
('FAIRY');

INSERT INTO pkm_pokemon (sequence_number, name) VALUES
(1, 'Bulbasaur'),
(2, 'Ivysaur'),
(3, 'Venusaur'),
(4, 'Charmander'),
(5, 'Charmeleon'),
(6, 'Charizard'),
(7, 'Squirtle'),
(8, 'Wartortle'),
(9, 'Blastoise'),
(10, 'Pikachu');

INSERT INTO pkm_pokemon_type (pokemon_sequence_number, type) VALUES
(1, 'GRASS'),    -- Bulbasaur
(1, 'POISON'),
(2, 'GRASS'),    -- Ivysaur
(2, 'POISON'),
(3, 'GRASS'),    -- Venusaur
(3, 'POISON'),
(4, 'FIRE'),     -- Charmander
(5, 'FIRE'),     -- Charmeleon
(6, 'FIRE'),     -- Charizard
(6, 'FLYING'),
(7, 'WATER'),    -- Squirtle
(8, 'WATER'),    -- Wartortle
(9, 'WATER'),    -- Blastoise
(10, 'ELECTRIC'); -- Pikachu
