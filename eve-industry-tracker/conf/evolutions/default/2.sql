# wallet_total schema

# --- !Ups

CREATE TABLE wallet_total(
	userID INT,
	date DATE NOT NULL,
	balance DECIMAL NOT NULL,
	PRIMARY KEY (userID, balance, date)
);

# --- !Downs

DROP TABLE wallet_total;