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
### TarsosDSP
For audio processing, we use [TarsosDSP](https://github.com/JorenSix/TarsosDSP). Because it is not a maven project, you need to install it locally. Outside of this project, execute:
```
git clone https://JorenSix@github.com/JorenSix/TarsosDSP.git
cd TarsosDSP/build
ant tarsos_dsp_library
```
If the last command fails because of some unmappable ASCII characters, run ```export JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8``` and retry. This should result in a file called *TarsosDSP-2.2.jar*. Install this file into your local maven repository, by going to *contextproject-ms3/contextproject-ms3* and running:
```
mvn install:install-file -DgroupId=be.tarsos.dsp -DartifactId=TarsosDSP -Dpackaging=jar -Dversion=2.2 -Dfile=/path-to/TarsosDSP-2.2.jar -DgeneratePom=true
```

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
