create database documentsRepo;
Use documentsrepo;
CREATE TABLE users(
	id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE documents(
	id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL UNIQUE,
    created_by INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    lastVersionNum INT, 
    lastVersionId INT,
    lastUpdated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(id)
    
);

CREATE TABLE versions(
	id INT PRIMARY KEY AUTO_INCREMENT,
    doc_id INT,
    version_num INT,
    content TEXT,
    status VARCHAR(50) NOT NULL,
    created_by INT,      
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY(doc_id) REFERENCES documents(id),
    FOREIGN KEY(created_by) REFERENCES users(id)
);

CREATE TABLE comments(
	id INT PRIMARY KEY AUTO_INCREMENT,
    version_id INT NOT NULL,
    user_id INT NOT NULL,
    comment TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    FOREIGN KEY(version_id) REFERENCES versions(id),
    FOREIGN KEY(user_id) REFERENCES users(id)
);

ALTER TABLE documents
ADD CONSTRAINT fk_last_version
FOREIGN KEY (lastVersionId)
REFERENCES versions(id);

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS versions;
DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS documents;
DROP TABLE IF EXISTS comments;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO document (id, name, document_content) 
VALUES (1,'Test Document','This is some content'),
       (2,'Login Document','Again some content') ;
       
Select * from document_version;
SELECT * FROM users;


	

UPDATE document_version
SET status="STILL"
WHERE id=6;

-- 1. Спираме проверките за чужди ключове
SET FOREIGN_KEY_CHECKS = 0;

-- 2. Променяме колоната на AUTO_INCREMENT
	
ALTER TABLE document_version DROP constraint FKnvpdtplqabenasvgs0q5e3db4;

-- 3. Пускаме проверките обратно
SET FOREIGN_KEY_CHECKS = 1;

Update document_version
SET status="STILL"
WHERE id=6;


INSERT INTO users
VALUES("AUTHOR",4,"author0345","Arthur Boyle")

INSERT INTO documents (
    id,
    name,
    document_content
    ) VALUES (
    1,
    'Employee Handbook',
    'Internal company rules and policies',
    NOW(),
    3
);

UPDATE document_version
SET status="STILL"
WHERE id=4;


UPDATE users
SET role="REVIEWER"
WHERE id=1;


