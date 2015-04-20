# Contextproject Multimedia Services

## Build Status
#### Master:
![Master](https://travis-ci.org/daveystruijk/contextproject-ms3.svg?branch=master)
#### Develop:
![Master](https://travis-ci.org/daveystruijk/contextproject-ms3.svg?branch=develop)

## Group Members (MS3)
- R.S. Graafmans
- Emiel Rietdijk
- D.R. Struijk
- F.A. van Doorn
- M.J.E. van Osch

## Branching
Branching will be done somewhat like: [A successful Git branching model](http://nvie.com/posts/a-successful-git-branching-model/) (except omitting release branches). This means we will have **feature branches** that merge into **develop** using pull requests. Once we've reached a new milestone, it will be tagged and pushed to **master**.

Master must *always have a running version* and pass all tests. If something critical needs to be fixed immediately, we create a **hotfix branch** and make a pull request into master. Feature branches are prefixed "feature-", and hotfix branches are prefixed "hotfix-".

## Integration
### Travis CI
Maven tests (```mvn test```) are ran each time someone pushes to github, using [Travis CI](https://travis-ci.org/).

### Static Code Analysis
You can run ```mvn site``` to create reports for:

- Cobertura (code coverage)
- FindBugs (bug discovery)
- CPD (duplicate code detection)
- PMD (verification of coding rules)

Results can be found under *target/site/index.html* -> Project Reports.