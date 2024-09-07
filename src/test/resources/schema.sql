CREATE TABLE USER_ROLE
(
    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name   varchar(50) UNIQUE,
    parent_role int,
    CONSTRAINT fk_user_role FOREIGN KEY (parent_role)
        REFERENCES USER_ROLE (id) ON DELETE CASCADE
);

CREATE TABLE USERS
(
    id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id     UUID        NOT NULL UNIQUE,
    identity_id uuid        NOT NULL UNIQUE,
    created_at  TIMESTAMPTZ NOT NULL,
    updated_at  TIMESTAMPTZ NOT NULL,
    user_name   varchar(50) UNIQUE,
    email       varchar(90) UNIQUE,
    postal_code varchar(10),
    country     varchar(90),
    user_role   int,
    CONSTRAINT fk_user_role FOREIGN KEY (user_role)
        REFERENCES user_role (id) ON DELETE SET NULL
);