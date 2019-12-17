ALTER TABLE IF EXISTS ONLY product DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY product DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS supplier;

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(400) NOT NULL,
    department VARCHAR(400) NOT NULL,
    description VARCHAR(400) NOT NULL
);

CREATE TABLE supplier (
    id SERIAL PRIMARY KEY,
    name VARCHAR(400) NOT NULL,
    description VARCHAR(400) NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(400) NOT NULL,
    default_price FLOAT NOT NULL,
    currency VARCHAR(30) NOT NULL,
    description VARCHAR(400) NOT NULL,
    category_id INTEGER REFERENCES category(id),
    supplier_id INTEGER REFERENCES supplier(id)
);

INSERT INTO category VALUES
    (1, 'Potion', 'Liquid', 'Bottles containing fluids of magical property'),
    (2, 'Scroll', 'Spellware', 'Spells scribed to scrolls'),
    (3, 'Clothes', 'Clothware', 'Cloths weaved from magical materials, but in reality they were simple clothes and the magic was added later'),
    (4, 'Cutlery', 'Cutlery', 'Cutlery of all kinds'),
    (5, 'Other', 'Other', 'Items of eldritch origin'),
    (6, 'Nick-nack', 'Whatever... Am I supposed to know? Like, you run the shop, don''t you. Jeez man...', 'Our finest ornamental objects'),
    (7, 'Food', 'Magical Edible', 'Finest quality protein and vitamins included')
;
SELECT pg_catalog.setval('category_id_seq', 7, true);

INSERT INTO supplier VALUES
    (1, 'Doctor Jonathan Tronley', 'Miracle products'),
    (2, 'Mr. Wizardly', 'Lazily put together magic items'),
    (3, 'Blibdoolpoolp', 'The Sea Mother, The Drowning Goddess, Whip of Whips')
;
SELECT pg_catalog.setval('supplier_id_seq', 3, true);

INSERT INTO product VALUES
        (1, 'Sock of Water Walking', 62.5, 'NOK', 'you can walk on water but gets terribly wet and uncomfortable', 3, 2),
        (2, 'Boots of Reverse Flying', 124.5, 'NOK', 'You will feel upside down floating on the surface', 3, 2),
        (3, 'Not water', 0, 'SOS','Look, smells and tastes like water, but you know it''s not water, neither us nor the cosmos know where it comes from', 5, 3),
        (4, 'Common fruit', 1, 'SOS', 'common', 5, 3),
        (5, 'Tie of Protection +1', 32, 'NOK', 'Protects your tie from weapon damage', 3, 2),
        (6, 'Endless vacuum', 340, 'HUF', 'You can''t deny the fact that nothing is either none or infinite', 6, 1),
        (7, 'Linked List', 12005, 'HUF', 'A pair of shopping lists tied together, you always know where the next one is when you hold either one', 2, 1),
        (8, 'Actually Instant Noodles', 1200, 'HUF', 'Just a can of baked noodles, but I''m sure you''ll love it!', 7, 1),
        (9, 'Rubber-duck of Silence', 44, 'NOK', 'If you''d speak to it, your talk won''t leave a noise', 6, 2),
        (10, 'Suit of Mirage', 26515, 'HUF', 'The ones looking at you will experience a mirage of miracles', 3, 1),
        (11, 'Chalice of Suffering', 469, 'HUF', 'It seriously hurts, don''t touch it. What else do you have friends for?', 4, 1),
        (12, 'Helm of Sight', 11999.99, 'HUF', 'Perfect vision no matter where are your eyes, if your eyes work properly, that is', 3, 1),
        (13, 'Helm of Insight', 2, 'SOS', 'Grants us eyes, Grant us eyes! By Kosm or some say Cos, You shall experience the cosmic truth', 3, 3),
        (14, 'Instant Skeletal Buzz', 590, 'HUF', 'You will feel your whole skeleton while you drink this miracle elixir. At least for a good second... also tastes like beer', 1, 1),
        (15, 'Void Box', 1, 'SOS', 'It is not Void because it is empty, It is, because we do not know what it is full of', 5, 3),
        (16, 'Bag of Bag of Holding Holding', 112, 'NOK', 'A bag of holding that holds bag of holdings', 3, 2)
;
SELECT pg_catalog.setval('product_id_seq', 16, true);