# cache_timers schema

# --- !Ups

CREATE TABLE cache_timers(
	charID INT,
	requestType VARCHAR(20),
	cachedUntil DATETIME NOT NULL,
	PRIMARY KEY (charID,requestType)
);

# --- !Downs

DROP TABLE cache_timers;