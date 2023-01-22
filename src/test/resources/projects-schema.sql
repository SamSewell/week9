DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS project;


CREATE TABLE project (
project_id INT auto_increment NOT NULL,
project_name VARCHAR(128) NOT NULL,
estimated_hours decimal(7,2) NOT NULL,
actual_hours decimal(7,2),
difficulty int,
notes text,
primary key(project_id),
);

CREATE TABLE category (
category_id int auto_increment NOT NULL,
category_name VARCHAR(128),
primary key(category_id)
);

CREATE TABLE project_category (
project_id INT NOT NULL,
category_id INT NOT null
);

CREATE TABLE step (
step_id INT auto_increment NOT NULL,
project_id INT NOT NULL,
step_text TEXT,
step_order int,
primary key(step_id)
);

CREATE TABLE material (
material_id INT auto_increment NOT NULL,
project_id INT NOT NULL,
material_name VARCHAR(128) NOT NULL,
num_required INT,
primary key(material_id)
);