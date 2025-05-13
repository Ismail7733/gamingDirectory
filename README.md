## **Data Model Design**

At the beginning of this case study, I focused on designing the data model.

Each gamer has a unique ID and a country of origin. Each game also has a unique ID and a name. To represent the relationship between a gamer and the games they play, along with their skill level (`INVINCIBLE`, `PRO`, `N00B`).

While one could technically place all this information into a single table, doing so would violate normalization principles. For clarity, flexibility, and maintainability, I normalized the data into three distinct tables:

CREATE TABLE gamers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(255) NOT NULL
);

CREATE TABLE games (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE gamer_game_links (
    gamer_id BIGINT NOT NULL,
    game_id BIGINT NOT NULL,
    level VARCHAR(50) NOT NULL,
    PRIMARY KEY (gamer_id, game_id),
    FOREIGN KEY (gamer_id) REFERENCES gamers(id) ON DELETE CASCADE,
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE
);

## **Decisions made**

Since the linking table requires a composite key (gamer_id, game_id), I researched how to model composite keys in Spring Boot. I initially chose to use the @Embeddable and @EmbeddedId annotations. 

Later, I learned about the alternative @IdClass strategy. In hindsight, that may have been a better fit for this use case, especially since @IdClass allows easier queries when you often need access to the individual components of the composite key (as with gamer/game filtering for auto-matching).

I used MockMvc for integration testing due to its simplicity and support for full request/response validation. I chose to use an in-memory H2 database because it is easy to set up and automatically reset between test runs.

I intentionally left out certain CRUD operations like delete, as their implementations would not differ significantly from the existing endpoints and I prioritized demonstrating more complex logic such as auto-matching.

For API documentation, I used Swagger. 

## **Development Process**

I started by building the basic CRUD flow for GamerEntity — including the entity, repository, service, controller, and corresponding tests.
For time reasons, testing was somewhat limited, but ideally, the following test types would be implemented:

Repository integration tests for custom queries like gamer auto-matching.

Unit tests for controllers, using a spy service to verify the indirect input

Unit tests for services, using a spy repository to isolate business logic.

Full integration tests with MockMv.

## **Issues Encountered**

You may notice the following line in my GamerRepositoryIntegrationTest class: @EntityScan(basePackages = "com.ismailo.gamingDirectory.entities")

This was necessary due to a strange issue: the test passed when run individually, but failed during a full test suite run with the error that the gamer entity could not be found.
This suggests that Spring Boot was not consistently detecting entities in some test contexts. As a temporary solution, I used this notation. In a real project, this should be investigated further

Also, in a real environment, you would not want to expose your entity structure in your service layer. In this case, it would make sense to use DTOs

