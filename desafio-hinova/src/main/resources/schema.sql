CREATE TABLE IF NOT EXISTS USUARIOS (
        id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        email VARCHAR(50) NOT NULL,
        phone VARCHAR (20) NOT NULL,
        cpf VARCHAR (20) NOT NULL,
        zip_code VARCHAR,
        address VARCHAR,
        number VARCHAR,
        complement VARCHAR,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        status boolean
);

CREATE TABLE IF NOT EXISTS VEICULOS (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    plate VARCHAR(10) UNIQUE NOT NULL,
    advertised_price INT NOT NULL,
    brand_id INT,
    model_id INT,
    fipe_price INT,
    ano INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIOS(id)
);