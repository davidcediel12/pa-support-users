INSERT INTO users.user_role(role_name) values('FOUNDATION')  ON CONFLICT DO NOTHING ;
INSERT INTO users.user_role(role_name) values('USER')   ON CONFLICT DO NOTHING ;
