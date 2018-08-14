-- select all cars
SELECT
  car.name          AS car,
  engine.name       AS engine,
  body.name         AS body,
  transmission.name AS transmission
FROM car
  LEFT JOIN engine
    ON car.engine = engine.id
  LEFT JOIN body
    ON car.body = body.id
  LEFT JOIN transmission
    ON car.transmission = transmission.id;

-- select excess body
SELECT body.name AS body
FROM body
  LEFT JOIN car
    ON car.body = body.id
WHERE car.name IS NULL;

-- select excess engine
SELECT engine.name AS engine
FROM engine
  LEFT JOIN car
    ON car.engine = engine.id
WHERE car.name IS NULL;

-- select excess transmission
SELECT transmission.name AS transmission
FROM transmission
  LEFT JOIN car
    ON car.transmission = transmission.id
WHERE car.name IS NULL;