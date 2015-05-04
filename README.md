# Contextproject Multimedia Services

## Build Status
#### Master:
![Master](https://travis-ci.org/daveystruijk/contextproject-ms3.svg?branch=master)
#### Develop:
![Master](https://travis-ci.org/daveystruijk/contextproject-ms3.svg?branch=develop)

## Planning
Development will be planned in sprints. [Issues, upcoming features, and the current sprint plan can be found on Trello](https://trello.com/b/zRkZ4BgD/contextproject).

**All non-technical deliverables can be found [here](https://drive.google.com/folderview?id=0Bz11IfzFqxcWflhYalNCZlJzM1g4WExrS3AyNVVvNGJfeDA4OW9NZ19jck50dmxXUm5USUk).**

## Group Members (MS3)
- R.S. Graafmans
- Emiel Rietdijk
- D.R. Struijk
- F.A. van Doorn
- M.J.E. van Osch

## Branching
Branching will be done somewhat like: [A successful Git branching model](http://nvie.com/posts/a-successful-git-branching-model/) (except omitting release branches). This means we will have **feature branches** that merge into **develop** using pull requests. Once we've reached a new milestone, it will be tagged and pushed to **master**.

Master must *always have a running version* and pass all tests. If something critical needs to be fixed immediately, we create a **hotfix branch** and make a pull request into master. Feature branches are prefixed "feature-", and hotfix branches are prefixed "hotfix-".

## Dependencies
### JavaFX
It helps to have JavaFX integrated into Eclipse itself, by going to ```Help -> Install New Software``` with URL: ```http://download.eclipse.org/efxclipse/updates-released/1.2.0/site```

## Integration
### Travis CI
Maven tests are ran each time someone pushes to github, using [Travis CI](https://travis-ci.org/). (configuration can be found in the *.travis.yml* file)

### Static Code Analysis
You can run ```mvn site``` to create reports for:

- Checkstyle (coding standards)
- Cobertura (code coverage)
- FindBugs (bug discovery)
- CPD (duplicate code detection)
- PMD (verification of coding rules)

Results can be found under *target/site/index.html* -> Project Reports.

### Octopull
The Travis configuration is compatible with [Octopull](http://www.rmhartog.me/octopull/), which will add all mentioned code style warnings to a pull request.
