-- Delete duplicates keeping only the one with minimum ID for each name
DELETE FROM destinations 
WHERE id NOT IN (
    SELECT MIN(id)
    FROM destinations
    GROUP BY name
);
