# wallet_journal schema

# --- !Ups

CREATE TABLE wallet_journal(
  	id INT NOT NULL AUTO_INCREMENT,
	charID INT,
	refID BIGINT,
	refTypeID INT NOT NULL,
	timestamp DATETIME NOT NULL,
	amount DECIMAL NOT NULL,
	balance DECIMAL NOT NULL,
	reason VARCHAR(256),
	PRIMARY KEY (id),
	UNIQUE KEY (charID, refID)
);

# --- !Downs

DROP TABLE wallet_journal;