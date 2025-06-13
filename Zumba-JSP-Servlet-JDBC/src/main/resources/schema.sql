-- Create batches table
CREATE TABLE IF NOT EXISTS batches (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT
);
SELECT * from batches

-- Create participants table
CREATE TABLE IF NOT EXISTS participants (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    phone TEXT NOT NULL,
    email VARCHAR,
    batch_id INTEGER,
    FOREIGN KEY (batch_id) REFERENCES batches(id)
);

-- Insert sample batches
INSERT INTO batches (name, description) VALUES
    ('Bollywood Zumba','All body movement workout in the form of dance'),
    ('Pilates', 'Focuses on strengthening and balancing'),
    ('BollyWorkout', 'All body movement workout in the form of exercise'),
    ('BollyWeights','Focuses on core muscles and flexibility');

-- Insert sample participants
INSERT INTO participants (name, phone, email, batch_id) VALUES
    ('Lacota Finley', '678-890-9871','eu.accumsan@google.net', (SELECT id FROM batches WHERE batch_id = 1)),
    ('sia','789-098-7890','sia@yahoo.org',(SELECT id FROM batches WHERE batch_id = 2)),
    ('Tasha Hodges', '890-654-7654','eget.nisi@yahoo.net',(SELECT id FROM batches WHERE batch_id = 3)),
    ('Gauri Shiva', '897-504-9054','gauri.shiva@yahoo.net',(SELECT id FROM batches WHERE batch_id = 4));
