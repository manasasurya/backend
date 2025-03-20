-- Delete duplicates keeping only the one with minimum ID for each name
DELETE d1 FROM destinations d1
INNER JOIN destinations d2 
WHERE d1.name = d2.name AND d1.id > d2.id;

-- Add unique constraint
ALTER TABLE destinations ADD UNIQUE (name);
