# Playground to create an extensible Data Access Object

## Technologies used:
 - Hibernate
 - CDI
 - JUnit5

## How to run the project
Build the project with `mvn clean install`

# Generate a site

The Site Plugin is used to generate a site for the project. The generated site also includes the project's reports that
were configured in the POM

See [Apache Maven Site Plugin](https://maven.apache.org/plugins/maven-site-plugin/index.html) for more details.

Generate a site of the project with `mvn site site:stage`

## Publish the site to github pages

See [Apache Maven SCM Publish Plugin](https://maven.apache.org/plugins/maven-scm-publish-plugin/index.html)

Initial creation of the branch has to be done manually: see https://maven.apache.org/plugins/maven-scm-publish-plugin/various-tips.html#git-branch

Publish the site to github pages with `mvn scm-publish:publish-scm`

NOTE: global credentials need to be defined. You can define them with the following commands:

```shell
git config --global user.name "yourUsername"
git config --global user.email "yourEmail@yourEmail.com"
```

See https://maven.apache.org/scm/maven-scm-plugin/examples/bootstrapping-with-pom.html

# References
See Generic Repository:
https://lestard.eu/2015/generic_repository_part1/
https://lestard.eu/2015/generic_repository_part2/

See DDD identity:
https://enterprisecraftsmanship.com/posts/dont-use-ids-domain-entities/
https://medium.com/unil-ci-software-engineering/clean-ddd-lessons-modeling-identity-ff8bc17e0ae6

See Generic Dao:
https://rpestano.wordpress.com/2013/07/15/cdi-generic-dao/

# TODO:
- see https://maven.apache.org/guides/mini/guide-site.html
- https://danielflower.github.io/2015/01/29/Generating-a-Maven-plugin-site-and-publishing-to-Github-Pages.html
- https://www.lorenzobettini.it/2020/01/publishing-a-maven-site-to-github-pages/
- create coverage profile for running with jacoco support




