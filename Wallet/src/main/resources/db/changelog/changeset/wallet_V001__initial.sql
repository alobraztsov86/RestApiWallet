CREATE TABLE wallet (
	id uuid DEFAULT gen_random_uuid(),
	amount VARCHAR NOT NULL,
	created_at timestamptz DEFAULT current_timestamp,
    updated_at timestamptz DEFAULT current_timestamp,
	PRIMARY KEY (id)
);