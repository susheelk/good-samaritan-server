CREATE TABLE app.users(
  user_id SERIAL PRIMARY KEY,
  email VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(50) UNIQUE NOT NULL,
  points INTEGER
);

CREATE TABLE app.charities(
  charity_id SERIAL PRIMARY KEY,
  charity_name VARCHAR(50) NOT NULL,
  category VARCHAR(50) NOT NULL,
  address VARCHAR(200) NOT NULL,
  description text
);

CREATE TABLE app.donations_needed(
  id SERIAL PRIMARY KEY,
  charity_id INTEGER REFERENCES app.charities (charity_id),
  item_needed VARCHAR(50) NOT NULL,
  points INTEGER NOT NULL
);

CREATE TABLE app.user_monetary_donations(
  id SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES app.users (user_id),
  charity_id INTEGER REFERENCES app.charities (charity_id),
  amount INTEGER NOT NULL,
  points_awarded INTEGER NOT NULL
);

CREATE TABLE app.user_physical_donations(
  id SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES app.users (user_id),
  charity_id INTEGER REFERENCES app.charities (charity_id),
  description text,
  points_awarded INTEGER NOT NULL
);