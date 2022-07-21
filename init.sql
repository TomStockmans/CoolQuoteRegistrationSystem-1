CREATE TABLE conversation
(
    id BIGINT NOT NULL,
    created_on DATETIME,
    conversation_date DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE line
(
    id BIGINT NOT NULL,
    text VARCHAR,
    punch_line NUMERIC,
    line_type VARCHAR,
    fk_conversation_id BIGINT,
    CONSTRAINT fk_conversation_line FOREIGN KEY (fk_conversation_id) REFERENCES conversation (ID) DEFERRABLE INITIALLY DEFERRED
);

CREATE TABLE participant
(
    id BIGINT NOT NULL,
    name VARCHAR,
    victim NUMERIC,
    fk_participant_id BIGINT,
    CONSTRAINT fk_line_participant FOREIGN KEY (fk_participant_id) REFERENCES line (ID) DEFERRABLE INITIALLY DEFERRED
);

CREATE TABLE
    hibernate_sequence
(
    next_val BIGINT
);