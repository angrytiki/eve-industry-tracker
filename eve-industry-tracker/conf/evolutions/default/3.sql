# cache_timer schema

# --- !Ups

CREATE TABLE cache_timer(
	id INT NOT NULL AUTO_INCREMENT,
	charID INT,
	requestType VARCHAR(20) CONSTRAINT reqTypeChk CHECK (requestType IN ('character','wallet')),
	cachedUntil DATETIME NOT NULL,
	PRIMARY KEY (id),
	UNIQUE KEY (charID, requestType)
);

# --- !Downs

DROP TABLE cache_timer;