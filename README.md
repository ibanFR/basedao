# Playground to create an extensible Data Access Object
Technologies used:
Hibernate, CDI, JUnit5

Build the project with `mvn clean install`

See Generic Repository:
https://lestard.eu/2015/generic_repository_part1/
https://lestard.eu/2015/generic_repository_part2/

See DDD identity:
https://enterprisecraftsmanship.com/posts/dont-use-ids-domain-entities/
https://medium.com/unil-ci-software-engineering/clean-ddd-lessons-modeling-identity-ff8bc17e0ae6

See Generic Dao:
https://rpestano.wordpress.com/2013/07/15/cdi-generic-dao/

Generate a site of the project with `mvn site`

Publish the site to github pages with `mvn clean site site:stage scm-publish:publish-scm`

TODO:
- Use github actions to build the project on every commit
- Use github actions to publish a site of the project to github pages
- create coverage profile for running with jacoco support




