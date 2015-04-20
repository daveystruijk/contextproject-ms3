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
Branching will be done somewhat like: [A successful Git branching model](http://nvie.com/posts/a-successful-git-branching-model/) (except omitting release branches). This means we will have **feature branches** that merge into **develop** using pull requests. Once we've reached a new milestone, it will be tagged and pushed to **master**. If something critical needs to be fixed immediately, we create a **hotfix branch** and make a pull request into master. Feature branches are prefixed "feature-", and hotfix branches are prefixed "hotfix-".


## Integration
### Travis CI
Maven tests (```mvn test```) are ran each time someone pushes to github using [Travis CI](https://travis-ci.org/).

### Cobertura
To create a code coverage report, you can run ```mvn cobertura:cobertura```. The results can be found at target/site/cobertura/index.html

